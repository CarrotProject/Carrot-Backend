package com.market.carrot.config;

import com.market.carrot.service.UserService;
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
    // 07.29 UserService 생성 전
    private final UserService userService;

    // 인증을 무시할 경로 설정
    @Override
    public void configure(WebSecurity webSecurity) {
        webSecurity.ignoring().antMatchers("/css/**", "/js/**", "/img/**", "/h2-console/**");
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .authorizeRequests()
                .antMatchers("/login", "/signup", "/user").permitAll() // 누구나 접근 허용
                .antMatchers("/").hasRole("USER") // USER, ADMIN만 접근 가능
                .antMatchers("/admin").hasRole("ADMIN") // ADMIN만 접근 가능
                .anyRequest().authenticated() // 나머지 요청들은 권한의 종류에 상관 없이 권한이 있어야 접근 가능
                .and()
                .formLogin()
                .loginPage("/login") // 로그인 페이지 링크
                .defaultSuccessUrl("/") // 로그인 성공 후 리다이렉트 주소
                .and()
                .logout()
                .logoutSuccessUrl("/login") // 로그아웃 성공시 리다이렉트 주소
                .invalidateHttpSession(true) // 세션 날리기
        ;
    }

    @Override
    public void configure(AuthenticationManagerBuilder authenticationManagerBuilder) throws Exception {
        // 패스워드 암호화
        authenticationManagerBuilder.userDetailsService(userService)
                .passwordEncoder(new BCryptPasswordEncoder());
    }
}
