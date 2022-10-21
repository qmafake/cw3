package com.example.data.services;

import com.example.data.models.Transactions;
import com.example.data.repositories.Tr_typesRepo;
import com.example.data.repositories.TransactionsRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

@Component
public class TransactionServise implements TransactionsSer{
    @Autowired
    private TransactionsRepo transactionsRepo;

    @Autowired
    private Tr_typesRepo typesRepo;

    @Override
    public ArrayList<int[]> freq(){
        Iterable<Integer> typesID = typesRepo.getAllIds();



        List<Transactions> transactionsList = transactionsRepo.findAllById(typesID);

        transactionsList.size();

        //int[][] freq = new int[(int) typesRepo.count()][3];

        ArrayList<int[]> freqn = new ArrayList<int[]>();

        for(Integer item : typesID)
        {
            Integer qant = transactionsRepo.countByTr_type(item);
            Integer frek = (100*qant)/(int)transactionsRepo.count();
            freqn.add(new int[]{item, qant, frek});
        }

        return freqn;
    }

    @Override
    public List<int[]> mostFreq(ArrayList<int[]> freqn){
        Collections.sort(freqn, new Comparator<int[]>() {
            public int compare(int[] a, int[] b) {
                return b[a.length-1] -a[b.length-1];
            }
        });

        return freqn;
    }
}
