package com.gama.projeto.bluebank.repositories;
import com.gama.projeto.bluebank.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
