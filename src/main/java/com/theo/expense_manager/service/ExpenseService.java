package com.theo.expense_manager.service;

import com.theo.expense_manager.entity.Expense;
import com.theo.expense_manager.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ExpenseService {
    Expense saveExpense(Expense expense);
    Page<Expense> getExpensesByUser(User user, Pageable pageable);
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, Expense expense);
    boolean deleteExpense(Long id);
    List<Expense> getAllExpensesByUser(String username);

}
