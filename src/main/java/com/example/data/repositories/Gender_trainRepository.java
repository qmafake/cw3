package com.example.data.repositories;


import com.example.data.models.Gender_train;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Gender_trainRepository extends JpaRepository<Gender_train, Integer> {
}
