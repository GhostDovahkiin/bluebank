package com.gama.projeto.bluebank.services;

import com.gama.projeto.bluebank.entities.BankAccount;
import com.gama.projeto.bluebank.entities.Transaction;
import com.gama.projeto.bluebank.entities.User;
import com.gama.projeto.bluebank.entities.dtos.TransactionDTO;
import com.gama.projeto.bluebank.entities.enums.TransactionType;
import com.gama.projeto.bluebank.entities.models.TransactionRequest;
import com.gama.projeto.bluebank.exceptions.NotEnoughMoneyException;
import com.gama.projeto.bluebank.exceptions.TransactionNotFoundException;
import com.gama.projeto.bluebank.exceptions.UserNotFoundException;
import com.gama.projeto.bluebank.repositories.AccountRepository;
import com.gama.projeto.bluebank.repositories.TransactionRepository;
import com.gama.projeto.bluebank.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TransactionService {
    private final TransactionRepository transactionRepository;
    private final UserRepository userRepository;
    private final AccountRepository accountRepository;

    // Verifica se o dinheiro a se o usuário que está enviando tem saldo disponível
    private boolean canDoTransaction(double amountSender, double transactionAmount) {
        return amountSender >= transactionAmount;
    }

    // Método para remover e adicionar saldo
    private void removeAndAddAmount(BankAccount sender, BankAccount receiver, double amount) {
        var actualSenderAmount = sender.getAmount();
        var actualReceiverAmount = receiver.getAmount();

        sender.setAmount(actualSenderAmount - amount);
        receiver.setAmount(actualReceiverAmount + amount);
    }

    public void save(TransactionRequest transaction) {
        // Se o objeto da transação não for nulo, procuro o user sender/receiver no banco.
        if(transaction != null){
            var userSender = userRepository.findById(transaction.getSender());
            var userReceiver = userRepository.findById(transaction.getReceiver());

            // Se os users estiverem presentes, verifico se o valor em conta é maior ou igual ao valor da transação
            if (userSender.isPresent() && userReceiver.isPresent()) {
                var senderAccount = userSender.get().getAccount();
                var receiverAccount = userReceiver.get().getAccount();

                double accountAmount = senderAccount.getAmount();

                // Retiro e adiciono o valor nas contas
                // Salvo novamente no repository os usuários e a transação
                if (canDoTransaction(accountAmount, transaction.getAmount())) {
                    removeAndAddAmount(senderAccount, receiverAccount, transaction.getAmount());

                    // Salvo os usuários com saldo atualizado
                    userRepository.save(userSender.get());
                    userRepository.save(userReceiver.get());

                    // Crio a transação de retirada/recebimento e salvo
                    Transaction transactionSent = new Transaction(userSender.get(), userReceiver.get(), transaction.getAmount(), TransactionType.SEND);
                    Transaction transactionReceive = new Transaction(userSender.get(), userReceiver.get(), transaction.getAmount(), TransactionType.RECEIVE);
                    transactionRepository.save(transactionSent);
                    transactionRepository.save(transactionReceive);



            // Lanço exceções para cada IF
                } else throw new NotEnoughMoneyException();
            } else throw new UserNotFoundException();
        } else throw new TransactionNotFoundException();
    }

    public List<Transaction> findAll() {
        return transactionRepository.findAll();
    }

    public Page<Transaction> findAll(Pageable pageable) {
        return transactionRepository.findAll(pageable);
    }

    public Transaction findById(Long id) {
        return transactionRepository.findById(id).orElseThrow(TransactionNotFoundException::new);
    }

    public void delete(Long id) {
        if(!transactionRepository.existsById(id)){
            throw new TransactionNotFoundException();
        }
        transactionRepository.deleteById(id);
    }
}
