package com.gama.projeto.bluebank.Controller;

import com.gama.projeto.bluebank.forms.UserForm;
import com.gama.projeto.bluebank.model.User;
import com.gama.projeto.bluebank.model.dto.UserDTO;
import com.gama.projeto.bluebank.repositories.UserRepository;
import com.gama.projeto.bluebank.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;
import java.util.List;

import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/v1/api/user")
@RequiredArgsConstructor
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping
    @Cacheable(value = "bb.user")
    public ResponseEntity<Page<UserDTO>> findAll(
         //   @RequestParam(required=false, value="name") String name,
            Pageable pageable ){

        return ResponseEntity.ok(userService.findAll(pageable));
    }

    @CacheEvict(value="bb.user", allEntries = true)
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public ResponseEntity<UserDTO> createUser(@RequestBody @Valid UserForm userForm,
                                              UriComponentsBuilder uriBuilder){
       UserDTO dto =  userService.add(userForm);

        URI uri = uriBuilder.path("/v1/api/user/{id}").buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
     }
}
