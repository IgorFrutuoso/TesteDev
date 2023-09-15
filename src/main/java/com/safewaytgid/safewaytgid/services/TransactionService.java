package com.safewaytgid.safewaytgid.services;

import com.safewaytgid.safewaytgid.domain.transaction.Transaction;
import com.safewaytgid.safewaytgid.domain.user.User;
import com.safewaytgid.safewaytgid.dtos.TransactionDTO;
import com.safewaytgid.safewaytgid.repositories.TransactionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Service
public class TransactionService {
    @Autowired
    private UserService userService;

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private NotificationService notificationService;

    public Transaction createTransaction(TransactionDTO transaction) throws Exception{
        User sender = this.userService.findUserById(transaction.senderId());
        User receiver = this.userService.findUserById(transaction.receiverId());

         userService.validateTransaction(sender, transaction.value());
         BigDecimal taxa = userService.validateType(sender, transaction.value());

        Transaction transaction1 = new Transaction();
        transaction1.setAmount(transaction.value());
        transaction1.setSender(sender);
        transaction1.setReceiver(receiver);
        transaction1.setTimestamp(LocalDateTime.now());

        sender.setBalance(sender.getBalance().subtract(transaction.value()));
        sender.setBalance(sender.getBalance().subtract(taxa));
        receiver.setBalance(receiver.getBalance().add(transaction.value()));

        this.repository.save(transaction1);
        userService.saveUser(sender);
        userService.saveUser(receiver);

        this.notificationService.sendNotification(sender, "Transação enviada com sucesso");
        this.notificationService.sendNotification(receiver, "Transação recebida com sucesso");

        return transaction1;
    }
}
