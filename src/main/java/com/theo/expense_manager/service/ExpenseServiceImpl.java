package com.theo.expense_manager.service;

import com.theo.expense_manager.entity.Expense;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.repository.ExpenseRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class ExpenseServiceImpl implements  ExpenseService{

    private final ExpenseRepository expenseRepository;


    @Override
    public Expense saveExpense(Expense expense) {
        return expenseRepository.save(expense);
    }

    @Override
    public Page<Expense> getExpensesByUser(User user, Pageable pageable) {
        return expenseRepository.findByUser(user, pageable);
    }


    @Override
    public Expense getExpenseById(Long id) {
        return expenseRepository.findById(id).orElseThrow(() -> new RuntimeException("Expense not found"));

    }

    @Override
    public Expense updateExpense(Long id, Expense expenseDetails) {
        Expense expense = getExpenseById(id);
        expense.setAmount(expenseDetails.getAmount());
        expense.setDescription(expenseDetails.getDescription());
        expense.setDateTime(expenseDetails.getDateTime());
        expense.setCategory(expenseDetails.getCategory());
        return expenseRepository.save(expense);
    }

    @Override
    public boolean deleteExpense(Long id) {
        Optional<Expense> optionalExpense = expenseRepository.findById(id);
        if (optionalExpense.isPresent()) {
            expenseRepository.deleteById(id);
            return true;
        } else {
            return false;
        }
    }

}
