package com.example.data.repositories;

import com.example.data.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {
}
