package com.market.carrot.service;

import com.market.carrot.domain.user.User;
import com.market.carrot.domain.user.UserRepository;
import com.market.carrot.web.dto.user.UserRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class UserService implements UserDetailsService {
    private final UserRepository userRepository;

    @Override
    public User loadUserByUsername(String name) throws UsernameNotFoundException {
        return userRepository.findByName(name).orElseThrow(() -> new UsernameNotFoundException(name));
    }

    public Long save(UserRequestDto userRequestDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRequestDto.setPassword(encoder.encode(userRequestDto.getPassword()));

        return userRepository.save(userRequestDto.toEntity()).getCode();
    }
}