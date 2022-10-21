package com.example.data.repositories;

import com.example.data.models.Tr_type;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

public interface Tr_typesRepo extends JpaRepository<Tr_type, Integer> {
    @Query("select p.tr_type from Tr_type p")
    Iterable<Integer> getAllIds();
}
