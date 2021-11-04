package com.gama.projeto.bluebank.model.dto;

import com.gama.projeto.bluebank.model.Transaction;
import com.gama.projeto.bluebank.model.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.domain.Page;
import javax.validation.constraints.FutureOrPresent;
import javax.validation.constraints.NotNull;
import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;
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
    private String specificID;

    @NotNull(message = "specificId cannot be null.")
    private User sender;

    @NotNull(message = "specificId cannot be null.")
    private User receiver;

    @NotNull(message = "specificId cannot be null.")
    @FutureOrPresent
    private LocalDateTime date;

    public static TransactionDTO from(Transaction entity) {
        return TransactionDTO
                .builder()
                .id(entity.getId())
                .specificID(UUID.randomUUID().toString())
                .sender(entity.getSender())
                .sender(entity.getSender())
                .receiver(entity.getReceiver())
                .date(LocalDateTime.now())
                .build();
    }

    public static List<TransactionDTO> fromAll(List<Transaction> user) {
        return user.stream().map(TransactionDTO::from).collect(Collectors.toList());
    }

    public static Page<TransactionDTO> fromPage(Page<Transaction> pages) {
        return pages.map(TransactionDTO::from);
    }
}
