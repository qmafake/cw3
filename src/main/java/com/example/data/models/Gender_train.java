package com.example.data.models;

import lombok.*;
import net.bytebuddy.utility.nullability.MaybeNull;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
public class Gender_train {
    @Id
    private Integer customer_id;
    @MaybeNull
    private Boolean gender;
}
