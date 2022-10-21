package com.example.data.models;

import com.opencsv.bean.CsvDate;
import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import java.time.LocalDateTime;

@Entity
@Data
public class Transactions {
    @Id
    private Integer customer_id;

    //@CsvDate("dd HH:mm:ss")
    private String tr_datetime;

    private Integer mcc_code;

    private Integer tr_type;

    private Integer amount;

    private Integer term_id;
}