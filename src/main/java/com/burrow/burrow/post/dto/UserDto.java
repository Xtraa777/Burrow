package com.burrow.burrow.post.dto;

import com.burrow.burrow.user.entity.User;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserDto {
    private String username;

    public UserDto(User user) {
        this.username = user.getUid();
    }
}
