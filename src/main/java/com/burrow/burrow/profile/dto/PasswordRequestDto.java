package com.burrow.burrow.profile.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class PasswordRequestDto {
    String password;
    String updatePassword;
    String checkUpdatePassword;
}
