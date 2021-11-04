package com.gama.projeto.bluebank.Controller;

import com.gama.projeto.bluebank.model.Transaction;
import com.gama.projeto.bluebank.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transaction")
public class TransactionController {

    @Autowired
    private TransactionRepository transactionRepository;

    @GetMapping
    public List<Transaction> ListTransaction(){
        return transactionRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public Transaction CreateTransaction(@RequestBody @Valid Transaction transaction){
        return  transactionRepository.save(transaction);
    }
}
