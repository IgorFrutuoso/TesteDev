package com.safewaytgid.safewaytgid.domain.user;

import com.safewaytgid.safewaytgid.dtos.UserDTO;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

import java.math.BigDecimal;

@Entity(name ="users")
@Table(name = "users")
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@EqualsAndHashCode(of = "id")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotBlank(message = "Nome é obrigatio")
    private String name;

    @NotBlank(message = "Cpf ou Cnpj é obrigatorio")
    @Column(unique = true)
    private String document;

    @NotBlank(message = "Email é obrigatorio")
    @Email
    @Column(unique = true)
    private String email;

    @NotNull(message = "saldo é obrigatorio")
    private BigDecimal balance;

    @NotNull(message = "Tipo de usuario é obrigatorio")
    @Enumerated(EnumType.STRING)
    private UserType userType;

    public User(UserDTO data){
        this.name = data.name();
        this.document = data.document();
        this.email = data.email();
        this.balance = data.balance();
        this.userType= data.userType();
    }

}
