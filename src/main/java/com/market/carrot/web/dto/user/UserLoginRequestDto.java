package com.market.carrot.web.dto.user;

import com.market.carrot.domain.user.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class UserLoginRequestDto {
    private String phone;
    private String password;

    public User toEntity() {
        return User.builder()
                .phone(phone)
                .password(password)
                .build();
    }
}
