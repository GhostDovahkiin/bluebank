package com.gama.projeto.bluebank.entities.dtos;

import com.gama.projeto.bluebank.entities.BankAccount;
import com.gama.projeto.bluebank.entities.User;
import lombok.*;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.validation.constraints.Email;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.stream.Collectors;

@Data
@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class UserDTO implements Serializable {

    @NotNull(message = "id cannot be null.")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull(message = "Name cannot be null.")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters.")
    private String name;

    @NotNull(message = "Age cannot be null.")
    @Min(value = 13, message = "Your age must be higher or equal to 13 to use our services.")
    private int age;

    @NotNull
    @Size(min = 8, max = 15, message = "Number must be between 8 and 15 characters.")
    private String phone;

    @NotNull(message = "Email cannot be null.")
    @Email(message = "This email is not valid, please enter a valid email.")
    private String email;

    @NotNull(message = "account cannot be null")
    private BankAccount account;

    public static UserDTO from(User entity) {
        return UserDTO
                .builder()
                .id(entity.getId())
                .name(entity.getName())
                .age(entity.getAge())
                .phone(entity.getPhone())
                .email(entity.getEmail())
                .account(entity.getAccount())
                .build();
    }

    public static List<UserDTO> fromAll(List<User> user) {
        return user.stream().map(UserDTO::from).collect(Collectors.toList());
    }

    public static Page<UserDTO> fromPage(Page<User> pages) {
        return pages.map(UserDTO::from);
    }

}
