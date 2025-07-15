package com.Yoga.repo;

import com.Yoga.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface TransactionRepo extends JpaRepository<Transaction, Long> {
    List<Transaction> findByOrderByCreatedAtDesc();
    
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'INCOME'")
    BigDecimal getTotalIncome();
    
    @Query("SELECT SUM(t.amount) FROM Transaction t WHERE t.type = 'EXPENSE'")
    BigDecimal getTotalExpense();
}