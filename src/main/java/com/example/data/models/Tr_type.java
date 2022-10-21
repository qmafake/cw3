package com.example.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Tr_type {
    @Id
    private Integer tr_type;

    private String tr_description;
}
