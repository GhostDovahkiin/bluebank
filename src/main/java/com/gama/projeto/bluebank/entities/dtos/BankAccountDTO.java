package com.gama.projeto.bluebank.entities.dtos;

import com.gama.projeto.bluebank.entities.enums.AccountType;
import com.gama.projeto.bluebank.entities.BankAccount;
import com.gama.projeto.bluebank.entities.enums.HolderType;
import com.gama.projeto.bluebank.entities.Transaction;
import com.gama.projeto.bluebank.entities.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;

import javax.persistence.Column;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class BankAccountDTO implements Serializable {

    @NotNull(message = "id cannot be null.")
    private long id;

    @NotNull(message = "Account number cannot be null.")
    @Size(min = 6, max = 12, message = "Account number must be between 2 and 50 digits.")
    private Integer number;

    @NotNull(message = "Agency number cannot be null.")
    @Size(min = 5, max = 10, message = "Agency number must be between 2 and 50 digits.")
    private Integer agency;

    @NotNull(message = "Holder cannot be null.")
    private User holder;

    @NotNull(message = "Account type cannot be null.") @NotBlank @NotEmpty
    private AccountType type;

    @NotNull(message = "Account holder type cannot be null.") @NotBlank @NotEmpty
    private HolderType holderType;

    @PositiveOrZero(message = "Account amount cannot be negative, zero is the minimum")
    private double amount;

    public static BankAccountDTO from(BankAccount entity) {
        return BankAccountDTO
                .builder()
                .id(entity.getId())
                .number(entity.getNumber())
                .agency(entity.getAgency())
                .type(entity.getType())
                .holderType(entity.getHolderType())
                .amount(entity.getAmount())
                .build();
    }

    public static List<BankAccountDTO> fromAll(List<BankAccount> user) {
        return user.stream().map(BankAccountDTO::from).collect(Collectors.toList());
    }

    public static Page<BankAccountDTO> fromPage(Page<BankAccount> pages) {
        return pages.map(BankAccountDTO::from);
    }
}
