package com.gama.projeto.bluebank.services;

import com.gama.projeto.bluebank.entities.User;
import com.gama.projeto.bluebank.exceptions.UserNotFoundException;
import com.gama.projeto.bluebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService {
    private final UserRepository userRepository;

    public List<User> findAll() {
        return userRepository.findAll();
    }

    public Page<User> findAll(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    public User findById(Long id) {
        return userRepository.findById(id).orElseThrow(UserNotFoundException::new);
    }

    public void delete(Long id) {
        if(!userRepository.existsById(id)){
            throw new UserNotFoundException();
        }
        userRepository.deleteById(id);
    }
}
