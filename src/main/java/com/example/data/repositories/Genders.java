package com.example.data.repositories;


import com.example.data.models.Gender_train;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Genders extends JpaRepository<Gender_train, Integer> {

}
