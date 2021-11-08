package com.gama.projeto.bluebank.entities;

import com.gama.projeto.bluebank.entities.enums.HolderType;
import com.gama.projeto.bluebank.entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.PastOrPresent;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "transaction")
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Builder(builderClassName = "Builder")
public class Transaction implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;

    @PastOrPresent
    private LocalDateTime date;

    private double amount;

    @Enumerated(EnumType.STRING)
    private TransactionType type;

    public Transaction(User sender, User receiver, double amount, TransactionType type) {
        this.date = LocalDateTime.now();
        this.sender = sender;
        this.receiver = receiver;
        this.amount = amount;
        this.type = type;
    }
}
