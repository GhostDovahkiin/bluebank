package com.gama.projeto.bluebank.entities;

import com.gama.projeto.bluebank.entities.enums.AccountType;
import com.gama.projeto.bluebank.entities.enums.HolderType;
import com.gama.projeto.bluebank.entities.dtos.BankAccountDTO;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.PositiveOrZero;
import java.util.Set;

@Entity
@Table(name = "account")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class BankAccount {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(unique=true)
    private Integer number;

    private Integer agency;

    @Enumerated(EnumType.STRING)
    private AccountType type;

    @Enumerated(EnumType.STRING)
    private HolderType holderType;

    @PositiveOrZero
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private Set<Transaction> transactions;

    public static BankAccount to(BankAccountDTO dto) {
        return BankAccount
                .builder()
                .number(dto.getNumber())
                .agency(dto.getAgency())
                .type(dto.getType())
                .holderType(dto.getHolderType())
                .amount(dto.getAmount())
                .build();
    }
}