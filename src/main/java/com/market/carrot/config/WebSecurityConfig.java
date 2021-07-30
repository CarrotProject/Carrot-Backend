package com.market.carrot.config;

import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

@RequiredArgsConstructor
@EnableWebSecurity
@Configuration
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    // 07.29 UserService 생성 전.
    private final UserService userService;

    // 인증을 무시할 경로 설정
    @Override
    public void configure (WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**");
    }

    @Override
    protected void configure (HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .authorizeRequests()
                .antMatchers().permitAll() // 누구나 접근 허용
                .antMatchers().hasRole("USER") // USER 접근 가능
                .antMatchers().hasRole("ADMIN") // ADMIN 접근 가능
                .anyRequest().authenticated() // 다른 요청들은 권한에 상관없이 권한이 있어야 한다
                .and()
                    .formLogin()
                        .loginPage("/login")
                        .defaultSuccessUrl("/")
                .and()
                    .logout()
                        .logoutSuccessUrl("/login")
                        .invalidateHttpSession(true);
    }

    @Override
    public void configure (AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
