package com.example.data.services;

import com.example.data.models.Transactions;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public interface TransactionsSer {
    List<int[]> mostFreq(ArrayList<int[]> freq);

    ArrayList<int[]> freq();
}
