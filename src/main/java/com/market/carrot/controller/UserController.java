package com.market.carrot.controller;

import com.market.carrot.service.UserService;
import com.market.carrot.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;

@RequiredArgsConstructor
@Controller
public class UserController {
    private final UserService userService;

    // 회원가입 임시 주소
    @PostMapping("/signUp")
    public String signUp(UserRequestDto userRequestDto) {
        userService.save(userRequestDto);

        // 회원 가입 후 리다이렉트 임시 주소
        return "redirect:/login";
    }
}
