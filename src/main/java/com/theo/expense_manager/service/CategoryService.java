package com.theo.expense_manager.service;

import com.theo.expense_manager.entity.Category;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CategoryService {

    private  final CategoryRepository categoryRepository;

    public List<Category> getCategoriesForUser(User user) {
        return categoryRepository.findByIsDefaultTrueOrUser(user);
    }

    public Category createCategory(String name, User user) {
        Category category = Category.builder()
                .name(name)
                .isDefault(false)
                .user(user)
                .build();
        return categoryRepository.save(category);
    }

    public void deleteCategory(Long id, User user) {
        Category category = categoryRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        // Only allow deletion if user owns it and it’s not a default category
        if (!category.isDefault() && category.getUser().getId().equals(user.getId())) {
            categoryRepository.deleteById(id);
        } else {
            throw new RuntimeException("Not allowed to delete this category.");
        }
    }




}
