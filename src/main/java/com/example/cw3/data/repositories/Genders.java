package com.example.cw3.data.repositories;


import com.example.cw3.data.models.Gender_train;
import org.springframework.data.jpa.repository.JpaRepository;


public interface Genders extends JpaRepository<Gender_train, Integer> {

}
