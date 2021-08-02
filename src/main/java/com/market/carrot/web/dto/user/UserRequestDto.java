package com.market.carrot.web.dto.user;

import com.market.carrot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@Getter
public class UserRequestDto {
    private String email;
    private String password;
    private String auth;

    public User toEntity() {
        return User.builder()
                .email(email)
                .password(password)
                .auth(auth)
                .build();
    }
}
