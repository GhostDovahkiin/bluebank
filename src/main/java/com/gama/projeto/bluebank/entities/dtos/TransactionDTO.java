package com.gama.projeto.bluebank.entities.dtos;

import com.gama.projeto.bluebank.entities.Transaction;
import com.gama.projeto.bluebank.entities.User;
import com.gama.projeto.bluebank.entities.enums.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder(builderClassName = "Builder")
public class TransactionDTO implements Serializable {

    @NotNull(message = "id cannot be null.")
    private long id;

    @NotNull(message = "specificId cannot be null.")
    private User sender;

    @NotNull(message = "specificId cannot be null.")
    private User receiver;

    @NotNull(message = "specificId cannot be null.")
    @FutureOrPresent
    private LocalDateTime date;

    @Positive
    private double amount;

    @NotNull
    private TransactionType type;

    public static TransactionDTO from(Transaction entity) {
        return TransactionDTO
                .builder()
                .id(entity.getId())
                .sender(entity.getSender())
                .receiver(entity.getReceiver())
                .date(entity.getDate())
                .amount(entity.getAmount())
                .type(entity.getType())
                .build();
    }

    public static List<TransactionDTO> fromAll(List<Transaction> user) {
        return user.stream().map(TransactionDTO::from).collect(Collectors.toList());
    }

    public static Page<TransactionDTO> fromPage(Page<Transaction> pages) {
        return pages.map(TransactionDTO::from);
    }
}
