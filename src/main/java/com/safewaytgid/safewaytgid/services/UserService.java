package com.safewaytgid.safewaytgid.services;

import com.safewaytgid.safewaytgid.domain.user.User;
import com.safewaytgid.safewaytgid.domain.user.UserType;
import com.safewaytgid.safewaytgid.dtos.UserDTO;
import com.safewaytgid.safewaytgid.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.sound.midi.Receiver;
import java.math.BigDecimal;
import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository repository;

    public void validateTransaction(User sender, BigDecimal amount) throws Exception {
        if(sender.getBalance().compareTo(amount) < 0){
            throw new Exception("Saldo insuficiente");
        }
    }

    public BigDecimal validateType(User sender, BigDecimal amount){
        if(sender.getUserType() == UserType.COMPANY){
            BigDecimal taxa = new BigDecimal("10.0");
            return taxa;
        }
        return new BigDecimal("0.0");
    }

    public User findUserById(Long id) throws Exception {
        return this.repository.findUserById(id).orElseThrow(() -> new Exception("Usuarios não encontrado"));
    }

    public User createUser(UserDTO data){
        User newUser = new User(data);
        this.saveUser(newUser);
        return newUser;
    }

    public List<User> getAllUsers(){
        return this.repository.findAll();
    }
    public void saveUser(User user){
        this.repository.save(user);
    }
}
