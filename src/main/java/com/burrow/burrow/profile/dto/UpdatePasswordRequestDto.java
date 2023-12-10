package com.burrow.burrow.profile.dto;

import jakarta.persistence.Column;
import lombok.Getter;

@Getter
public class UpdatePasswordRequestDto {
    String updatePassword;
}
