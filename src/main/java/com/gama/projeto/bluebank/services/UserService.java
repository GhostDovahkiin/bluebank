package com.gama.projeto.bluebank.services;

import com.gama.projeto.bluebank.model.User;
import com.gama.projeto.bluebank.model.dto.UserDTO;
import com.gama.projeto.bluebank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class UserService {

    @Autowired
    UserRepository userRepository;

    public Page<UserDTO> findAll(Pageable pageable){

        int size = pageable.getPageSize();

        Pageable p = PageRequest.of(pageable.getPageNumber(), size,pageable.getSort());

        return UserDTO.fromPage(userRepository.findAll(p));
        //return repository.findAll().stream().map(CarFactory::Create).collect(Collectors.toList());
    }

    public UserDTO findById(long id) {
        Optional<User> result = userRepository.findById(id);
        return result.isPresent() ? UserDTO.from(result.get()) : null;
    }

    public UserDTO add(UserDTO form) {
        User user = UserDTO.toEntity(form);
        userRepository.save(user);
        return UserDTO.from(user);
    }

    public UserDTO update(UserDTO userDTO, long id) {
        Optional<User> result = userRepository.findById(id);

        if(!result.isPresent()) return null;

        User user = result.get();

        user.setAge(userDTO.getAge());
        user.setName(userDTO.getName());
        user.setPhone(userDTO.getPhone());
        user.setEmail(userDTO.getEmail());
        user.setAccount(userDTO.getAccount());
        user.setSpecificID(userDTO.getSpecificID());

        userRepository.save(user);

        return UserDTO.from(user);
    }

    public void remove(long id) {
        userRepository.deleteById(id);
    }

}
