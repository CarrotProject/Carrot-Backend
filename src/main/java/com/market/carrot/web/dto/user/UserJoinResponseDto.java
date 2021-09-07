package com.market.carrot.web.dto.user;

import lombok.Getter;

@Getter
public class UserJoinResponseDto {
    private final String successString;

    public UserJoinResponseDto(String successString) {
        this.successString = successString;
    }
}