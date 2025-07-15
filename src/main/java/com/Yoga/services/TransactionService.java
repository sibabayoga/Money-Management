package com.Yoga.services;

import com.Yoga.model.Transaction;
import com.Yoga.repo.TransactionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.math.BigDecimal;
import java.util.List;

@Service
public class TransactionService {
    @Autowired
    private TransactionRepo transactionRepository;
    
    public List<Transaction> getAllTransactions() {
        return transactionRepository.findByOrderByCreatedAtDesc();
    }
    
    public Transaction saveTransaction(Transaction transaction) {
        return transactionRepository.save(transaction);
    }
    
    public void deleteTransaction(Long id) {
        transactionRepository.deleteById(id);
    }
    
    public BigDecimal getBalance() {
        BigDecimal income = transactionRepository.getTotalIncome();
        BigDecimal expense = transactionRepository.getTotalExpense();
        
        if (income == null) income = BigDecimal.ZERO;
        if (expense == null) expense = BigDecimal.ZERO;
        
        return income.subtract(expense);
    }
    
    public BigDecimal getTotalIncome() {
        BigDecimal income = transactionRepository.getTotalIncome();
        return income != null ? income : BigDecimal.ZERO;
    }
    
    public BigDecimal getTotalExpense() {
        BigDecimal expense = transactionRepository.getTotalExpense();
        return expense != null ? expense : BigDecimal.ZERO;
    }
}