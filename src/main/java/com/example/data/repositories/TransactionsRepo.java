package com.example.data.repositories;

import com.example.data.models.Transactions;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface TransactionsRepo extends JpaRepository<Transactions, Integer> {
    @Query("SELECT COUNT(u) FROM Transactions u WHERE u.tr_type=:type")
    Integer countByTr_type(@Param("type")Integer type);
}
