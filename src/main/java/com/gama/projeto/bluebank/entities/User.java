package com.gama.projeto.bluebank.entities;

import com.gama.projeto.bluebank.entities.dtos.UserDTO;
import lombok.*;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import java.io.Serializable;

@Entity
@Table(name = "bb.user")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private String name;

    private int age;

    @Column(unique=true)
    private String phone;

    @Column(unique=true)
    private String email;

    @OneToOne(cascade=CascadeType.PERSIST)
    private BankAccount account;

    public static User to(UserDTO dto) {
        return User
                .builder()
                .name(dto.getName())
                .age(dto.getAge())
                .phone(dto.getPhone())
                .email(dto.getEmail())
                .account(dto.getAccount())
                .build();
    }
}
