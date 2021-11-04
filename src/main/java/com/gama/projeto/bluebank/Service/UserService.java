package com.gama.projeto.bluebank.Service;

import com.gama.projeto.bluebank.model.dto.UserDTO;
import com.gama.projeto.bluebank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

}
