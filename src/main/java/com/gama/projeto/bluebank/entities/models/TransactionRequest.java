package com.gama.projeto.bluebank.entities.models;

import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.PositiveOrZero;

@Getter
@Setter
public class TransactionRequest {
    @NotNull
    private Long sender;
    @NotNull
    private Long receiver;
    @NotNull
    @Positive
    private Double amount;
}
