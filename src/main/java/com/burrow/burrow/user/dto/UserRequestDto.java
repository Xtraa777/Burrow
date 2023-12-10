package com.burrow.burrow.user.dto;

import jakarta.validation.constraints.Pattern;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserRequestDto {

    @Pattern(regexp = "^[a-z0-9]{4,10}$")
    private String uid;

    @Pattern(regexp = "^[a-zA-Z0-9]{8,15}$")
    private String password;

    private String nickname;

    private String description;

    private boolean admin = false;

    private String adminToken = "";
}
