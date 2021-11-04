package com.gama.projeto.bluebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.gamastore.bluebank.model.BankAccount;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {

}