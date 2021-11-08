package com.gama.projeto.bluebank.controllers;

import com.gama.projeto.bluebank.entities.User;
import com.gama.projeto.bluebank.entities.dtos.UserDTO;
import com.gama.projeto.bluebank.repositories.UserRepository;
import com.gama.projeto.bluebank.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/v1/users")
@RequiredArgsConstructor
public class UserController {

    private final UserRepository userRepository;
    private final UserService userService;

    @GetMapping(path = "/all")
    @ResponseStatus(HttpStatus.OK)
    public List<UserDTO> findAll() { return UserDTO.fromAll(userService.findAll()); }

    @GetMapping(path = "")
    @ResponseStatus(HttpStatus.OK)
    public Page<UserDTO> listPageUser(Pageable pageable) { return UserDTO.fromPage(userService.findAll(pageable)); }

    @GetMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.OK)
    public UserDTO findByID(@PathVariable Long id) { return UserDTO.from(userService.findById(id)); }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public void save(@Valid @RequestBody UserDTO userDTO){ userRepository.save(User.to(userDTO)); }

    @DeleteMapping(value = "/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id){ userService.delete(id); }
}