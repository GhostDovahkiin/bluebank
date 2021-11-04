package com.gama.projeto.bluebank.Controller;

import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.repositories.AccountRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/bankaccount")
public class BankAccontController {

    @Autowired
    private AccountRepository accountRepository;

    @GetMapping
    public List<BankAccount> ListBanckAccount(){
        return accountRepository.findAll();
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public BankAccount createBanckAccount(@RequestBody @Valid BankAccount bankAccount){
        return  accountRepository.save(bankAccount);
    }
}

