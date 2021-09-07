package com.market.carrot.controller;

import com.market.carrot.service.UserService;
import com.market.carrot.web.dto.user.UserJoinRequestDto;
import com.market.carrot.web.dto.user.UserJoinResponseDto;
import com.market.carrot.web.dto.user.UserLoginRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@RestController
public class UserController {
    private final UserService userService;

    // 회원가입 API
    @PostMapping("/api/user/join")
    public UserJoinResponseDto signUp(UserJoinRequestDto userJoinRequestDto) {
        String successString = "SUCCESS JOIN PHONE : " + userService.save(userJoinRequestDto);
        return new UserJoinResponseDto(successString);
    }

    // 로그인 API (아직 안됨...)
    @PostMapping("/api/user/login")
    public void login(UserLoginRequestDto userLoginRequestDto) {
        System.out.println(userLoginRequestDto.getPhone());
        System.out.println(userLoginRequestDto.getPassword());
    }

    @GetMapping(value = "/api/user/logout")
    public void logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
    }
}
