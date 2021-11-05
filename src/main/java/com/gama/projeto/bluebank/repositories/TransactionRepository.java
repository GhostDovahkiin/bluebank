package com.gama.projeto.bluebank.repositories;

import com.gama.projeto.bluebank.model.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;



public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
