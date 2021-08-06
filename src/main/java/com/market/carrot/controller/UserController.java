package com.market.carrot.controller;

import com.market.carrot.service.UserService;
import com.market.carrot.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // 회원가입 임시 주소
    @PostMapping("/user")
    public String signUp(UserRequestDto userRequestDto) {
        userService.save(userRequestDto);

        // 회원 가입 후 리다이렉트 임시 주소
        return "redirect:/login";
    }

    @GetMapping(value = "/logout")
    public String logoutPage(HttpServletRequest request, HttpServletResponse response) {
        new SecurityContextLogoutHandler().logout(request, response, SecurityContextHolder.getContext().getAuthentication());
        return "redirect:/login";
    }
}
