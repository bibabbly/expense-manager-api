package com.theo.expense_manager.controller;

import com.theo.expense_manager.entity.Category;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

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



}
