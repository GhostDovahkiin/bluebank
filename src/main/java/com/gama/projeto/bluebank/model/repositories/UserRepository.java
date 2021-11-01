package br.com.gamastore.bluebank.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gamastore.bluebank.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
