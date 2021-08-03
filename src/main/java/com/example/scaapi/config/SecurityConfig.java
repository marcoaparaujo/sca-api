package com.example.scaapi.config;

import com.example.scaapi.service.UsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    @Autowired
    private UsuarioService usuarioService;

    @Bean
    PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(usuarioService)
            .passwordEncoder(passwordEncoder());
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .csrf().disable()
            .authorizeRequests()
                .antMatchers("/api/v1/alunos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/cursos/**")
                    .hasAnyRole("USER", "ADMIN")
                .antMatchers("/api/v1/disciplinas/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/v1/professores/**")
                    .hasRole("ADMIN")
                .antMatchers("/api/v1/turmas/**")
                    .hasRole("ADMIN")
                .antMatchers(HttpMethod.POST, "/api/v1/usuarios/**")
                    .permitAll()
                .anyRequest().authenticated()
            .and()
                .httpBasic();
        ;
    }
}
