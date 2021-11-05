package com.gama.projeto.bluebank.model.dto;

import com.gama.projeto.bluebank.model.BankAccount;
import com.gama.projeto.bluebank.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserDTO implements Serializable {

    @NotNull(message = "id cannot be null.")
    private long id;

    @NotNull(message = "specificID cannot be null.")
    private String specificID;

    @NotNull(message = "Name cannot be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Age cannot be null.")
    @Min(value = 13, message = "Your age must be higher or equal to 13 to use our services.")
    @Size(min = 2, max = 2, message = "Age must be between 2 and 2 characters.")
    private int age;

    @NotNull
    @Size(min = 8, max = 15, message = "Number must be between 8 and 15 characters.")
    private String phone;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "This email is not valid, please enter a valid email.")
    private String email;

    @NotNull(message = "account cannot be null, the values are MALE, FEMALE or UNDEFINED")
    private BankAccount account;

    public static UserDTO from(User entity) {
        return UserDTO
                .builder()
                .id(entity.getId())
                .specificID(UUID.randomUUID().toString())
                .name(entity.getName())
                .age(entity.getAge())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .account(entity.getAccount())
                .build();
    }

    public static User toEntity(UserDTO userDTO){
        return User.builder()
                .id(userDTO.getId())
                .specificID(UUID.randomUUID().toString())
                .name(userDTO.getName())
                .age(userDTO.getAge())
                .phone(userDTO.getPhone())
                .email(userDTO.getEmail())
                .account(userDTO.getAccount()).build();
    }
    public static List<UserDTO> fromAll(List<User> user) {
        return user.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public static Page<UserDTO> fromPage(Page<User> pages) {
        return pages.map(UserDTO::from);
    }
}
