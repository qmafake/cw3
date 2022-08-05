package com.example.cw3.data.models;

import lombok.Data;
import lombok.Getter;
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
