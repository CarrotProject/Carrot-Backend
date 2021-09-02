package com.market.carrot.service;

import com.market.carrot.domain.user.User;
import com.market.carrot.domain.user.UserRepository;
import com.market.carrot.web.dto.user.UserJoinRequestDto;
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
    public User loadUserByUsername(String phone) throws UsernameNotFoundException {
        return userRepository.findByPhone(phone).orElseThrow(() -> new UsernameNotFoundException(phone));
    }

    public String save(UserJoinRequestDto userRequestDto) {
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        userRequestDto.setPassword(encoder.encode(userRequestDto.getPassword()));

        return userRepository.save(userRequestDto.toEntity()).getPhone();
    }
}