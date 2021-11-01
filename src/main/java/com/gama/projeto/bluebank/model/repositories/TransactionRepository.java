package br.com.gamastore.bluebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamastore.bluebank.model.Transaction;

public interface TransactionRepository extends JpaRepository<Transaction, Long>{

}
