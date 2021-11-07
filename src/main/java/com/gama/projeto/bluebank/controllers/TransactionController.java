package com.gama.projeto.bluebank.controllers;
import com.gama.projeto.bluebank.entities.Transaction;
import com.gama.projeto.bluebank.entities.dtos.TransactionDTO;
import com.gama.projeto.bluebank.entities.dtos.UserDTO;
import com.gama.projeto.bluebank.entities.models.TransactionRequest;
import com.gama.projeto.bluebank.repositories.TransactionRepository;
import com.gama.projeto.bluebank.services.TransactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/transactions")
@RequiredArgsConstructor
public class TransactionController {
    private final TransactionService transactionService;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<TransactionDTO> findAll() { return TransactionDTO.fromAll(transactionService.findAll()); }

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Page<TransactionDTO> listPage(Pageable pageable) { return TransactionDTO.fromPage(transactionService.findAll(pageable)); }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public TransactionDTO findByID(@PathVariable Long id) { return TransactionDTO.from(transactionService.findById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@RequestBody @Valid TransactionRequest transaction){
        transactionService.save(transaction);
    }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ transactionService.delete(id); }
}
