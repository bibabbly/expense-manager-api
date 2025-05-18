package com.theo.expense_manager.repository;

import com.theo.expense_manager.entity.Expense;
import com.theo.expense_manager.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense,Long> {

    List<Expense> findByUser(User user);
    List<Expense> findByUserUsername(String username);
}
