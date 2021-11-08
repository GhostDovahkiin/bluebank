package com.gama.projeto.bluebank.repositories;

import com.gama.projeto.bluebank.entities.BankAccount;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AccountRepository extends JpaRepository<BankAccount, Long> {

}