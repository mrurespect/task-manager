package fsts.mrurespect.backendspring.security;


import fsts.mrurespect.backendspring.service.UserService;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;


@Configuration
public class SecurityConfig{

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception{
        http
                .csrf(c-> c.disable())
                .authorizeHttpRequests((requests)-> requests
                        .requestMatchers(HttpMethod.GET,"/tasks","/user-info").authenticated()
                        .requestMatchers(HttpMethod.POST,"/task").authenticated()
                        .requestMatchers(HttpMethod.DELETE,"/task").authenticated()
                        .requestMatchers("/login","/register").permitAll()
                );
        http.formLogin(withDefaults());
        http.httpBasic(withDefaults());
        return http.build();
    }
    @Bean
    public BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public DaoAuthenticationProvider authenticationProvider(UserService userService) {
        DaoAuthenticationProvider auth = new DaoAuthenticationProvider();
        auth.setUserDetailsService(userService); //set the custom user details service
        auth.setPasswordEncoder(passwordEncoder());
        return auth;
    }
}
