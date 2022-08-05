package com.example.cw3.data.models;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Tr_mcc_codes {
    @Id
    private Integer mcc_code;

    private String mcc_description;
}
