package com.theo.expense_manager.controller;

import com.theo.expense_manager.entity.Category;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@RestController
@RequestMapping("/api/categories")
@RequiredArgsConstructor
public class CategoryController {

    private final CategoryService categoryService;

    // ✅ Get categories (default + user-defined)
    @GetMapping
    public ResponseEntity<List<Category>> getUserCategories(@AuthenticationPrincipal User user) {
        return ResponseEntity.ok(categoryService.getCategoriesForUser(user));
    }

    // ✅ Create a new category
    @PostMapping
    public ResponseEntity<Category> createCategory(
            @RequestBody Map<String, String> body,
            @AuthenticationPrincipal User user) {

        String name = body.get("name");
        if (name == null || name.isBlank()) {
            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.ok(categoryService.createCategory(name, user));
    }

    // ✅ Delete user-created category
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategory(@PathVariable Long id, @AuthenticationPrincipal User user) {
        try {
            categoryService.deleteCategory(id, user);
            return ResponseEntity.ok("Category deleted successfully.");
        } catch (RuntimeException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> updateCategory(@PathVariable Long id,
                                            @RequestBody Category updatedCategory,
                                            @AuthenticationPrincipal User user) {
        try {
            Category category = categoryService.getCategoryById(id);

            if (category.isDefault() || !category.getUser().getId().equals(user.getId())) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN)
                        .body("You cannot edit this category.");
            }

            category.setName(updatedCategory.getName());
            categoryService.saveCategory(category);

            return ResponseEntity.ok(category);
        } catch (NoSuchElementException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Category not found.");
        }

    }

}
