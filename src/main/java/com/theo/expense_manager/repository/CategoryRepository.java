package com.theo.expense_manager.repository;

import com.theo.expense_manager.entity.Category;
import com.theo.expense_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category,Long> {

    // Get categories that are either default or belong to the given user
    List<Category> findByIsDefaultTrueOrUser(User user);

    // Get only user-defined categories
    List<Category> findByUser(User user);
}
