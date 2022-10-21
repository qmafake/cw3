package com.example.data.controllers;

import com.example.data.services.TransactionServise;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class TransactionsController {
    private final TransactionServise transactionServise;

    @Autowired
    public TransactionsController(TransactionServise transactionServise) {
        this.transactionServise = transactionServise;
    }

    @RequestMapping("/freq")
    public String freq(Model model) {
        //List<int[]> frequencies = transactionServise.freq();
        model.addAttribute("frequencies", transactionServise.freq());
        return "freq";
    }

    @RequestMapping("/mostfreq")
    public String mostFreq(Model model) {
        //List<int[]> frequencies = transactionServise.freq();
        model.addAttribute("frequ", transactionServise.mostFreq(transactionServise.freq()));
        return "mostfreq";
    }

}
