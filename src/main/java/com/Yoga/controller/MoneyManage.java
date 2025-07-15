package com.Yoga.controller;

import com.Yoga.model.Transaction;
import com.Yoga.services.TransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.math.BigDecimal;

@Controller
public class MoneyManage {
    @Autowired
    private TransactionService transactionService;
    
    @GetMapping("/")
    public String index(Model model) {
        model.addAttribute("transactions", transactionService.getAllTransactions());
        model.addAttribute("balance", transactionService.getBalance());
        model.addAttribute("totalIncome", transactionService.getTotalIncome());
        model.addAttribute("totalExpense", transactionService.getTotalExpense());
        model.addAttribute("newTransaction", new Transaction());
        return "index";
    }
    
    @PostMapping("/transaction")
    public String addTransaction(@ModelAttribute Transaction transaction) {
        transactionService.saveTransaction(transaction);
        return "redirect:/";
    }
    
    @GetMapping("/transaction/delete/{id}")
    public String deleteTransaction(@PathVariable Long id) {
        transactionService.deleteTransaction(id);
        return "redirect:/";
    }
}