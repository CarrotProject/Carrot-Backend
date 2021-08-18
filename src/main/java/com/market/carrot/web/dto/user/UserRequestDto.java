package com.market.carrot.web.dto.user;

import com.market.carrot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserRequestDto {
    private String name;
    private String phone;
    private String password;
    private String auth;

    public User toEntity() {
        return User.builder()
                .name(name)
                .phone(phone)
                .password(password)
                .auth(auth)
                .build();
    }
}
