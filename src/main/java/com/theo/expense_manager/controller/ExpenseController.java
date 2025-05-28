package com.theo.expense_manager.controller;

import com.theo.expense_manager.entity.Expense;
import com.theo.expense_manager.entity.User;
import com.theo.expense_manager.service.ExpenseService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/expenses")
@RequiredArgsConstructor
public class ExpenseController {
    private final ExpenseService expenseService;

    // Create a new expense
    @PostMapping
    public ResponseEntity<Expense> createExpense(@RequestBody Expense expense, @AuthenticationPrincipal User user) {
        expense.setUser(user); // Link to authenticated user
        Expense savedExpense = expenseService.saveExpense(expense);
        return new ResponseEntity<>(savedExpense, HttpStatus.CREATED);
    }


    // ✅ Paginated GET endpoint
    @GetMapping
    public ResponseEntity<Page<Expense>> getAllExpenses(
            @AuthenticationPrincipal User user,
            Pageable pageable
    ) {
        Page<Expense> expenses = expenseService.getExpensesByUser(user, pageable);
        return new ResponseEntity<>(expenses, HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<Expense>> getAllExpensesForCharts(@AuthenticationPrincipal User user) {
        List<Expense> allExpenses = expenseService.getAllExpensesByUser(user.getUsername());
        return new ResponseEntity<>(allExpenses, HttpStatus.OK);
    }







    // Get a specific expense by ID
    @GetMapping("/{id}")
    public ResponseEntity<Expense> getExpenseById(@PathVariable Long id) {
        Expense expense = expenseService.getExpenseById(id);
        return new ResponseEntity<>(expense, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Expense> updateExpense(
            @PathVariable Long id,
            @RequestBody Expense expenseDetails,
            @AuthenticationPrincipal User user) {

        if (user == null) {
            return new ResponseEntity<>(HttpStatus.UNAUTHORIZED);
        }

        // Optional: you can also check if the user owns the expense here
        expenseDetails.setUser(user); // Associate updated data with the user

        Expense updated = expenseService.updateExpense(id, expenseDetails);
        return new ResponseEntity<>(updated, HttpStatus.OK);
    }



    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteExpense(@PathVariable Long id) {
        boolean deleted = expenseService.deleteExpense(id);
        if (deleted) {

            return ResponseEntity.ok("✅ Expense with ID " + id + " was deleted successfully.");
        } else {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("❌ Expense with ID " + id + " does not exist.");
        }
    }






}
