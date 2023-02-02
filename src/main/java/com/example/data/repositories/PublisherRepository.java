package com.example.data.repositories;

import com.example.data.models.Publisher;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by jt on 12/23/19.
 */
public interface PublisherRepository extends JpaRepository<Publisher, Integer> {
}