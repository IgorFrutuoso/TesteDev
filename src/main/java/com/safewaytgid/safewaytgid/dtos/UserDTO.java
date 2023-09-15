package com.safewaytgid.safewaytgid.dtos;

import com.safewaytgid.safewaytgid.domain.user.UserType;

import java.math.BigDecimal;

public record UserDTO(String name, String document, String email, BigDecimal balance, UserType userType) {
}
