package com.gama.projeto.bluebank.service;

import com.gama.projeto.bluebank.factories.UserFactory;
import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.User;
import com.gama.projeto.bluebank.model.dto.UserDTO;
import com.gama.projeto.bluebank.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    // Trazer todos os usuários
    public Page<UserDTO> findAll(Pageable pageable){

        int size = pageable.getPageSize();

        if(pageable.getPageSize() > 10) size = 10;
        else
            if(pageable.getPageSize() < 0) size = 0;

        Pageable _p = PageRequest.of(pageable.getPageNumber(), size,pageable.getSort());

        return UserDTO.fromPage(userRepository.findAll(_p));

        //return repository.findAll().stream().map(CarFactory::Create).collect(Collectors.toList());
    }

    // localizar por ID
    public UserDTO findById(long id) {
        var result = userRepository.findById(id);
        return result.isPresent() ? UserDTO.from(result.get()) : null;
    }

    public UserDTO add(UserForm form) {

        var result = userRepository.findByName(form.name);

        if(result.isPresent()) throw new RuntimeException("Usuário já cadastrado! " + form.name);

        User user = UserFactory.Create(form);

        userRepository.save(user);

        return UserFactory.Create(user);

    }

}
