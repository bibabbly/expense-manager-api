package com.theo.expense_manager.service;

import com.theo.expense_manager.entity.Expense;

import java.util.List;

public interface ExpenseService {
    Expense saveExpense(Expense expense);
    List<Expense> getAllExpensesByUser(String username);
    Expense getExpenseById(Long id);
    Expense updateExpense(Long id, Expense expense);
    boolean deleteExpense(Long id);
}
