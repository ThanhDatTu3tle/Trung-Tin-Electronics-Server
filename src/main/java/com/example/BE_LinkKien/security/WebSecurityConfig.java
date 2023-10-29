package com.example.BE_LinkKien.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.HashMap;
import java.util.Map;

@Configuration
@EnableWebSecurity
@EnableGlobalMethodSecurity(prePostEnabled = true)
@RequiredArgsConstructor
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    private final JwtTokenProvider jwtTokenProvider;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                .cors()
                .and()
                .csrf().disable()
                .authorizeHttpRequests()
                    .antMatchers("/api/v1/user/signin").permitAll()
                    .antMatchers("/api/v1/user/signup").permitAll()
                    .antMatchers("/api/v1/brand/getAll").permitAll()
                    .antMatchers("/api/v1/brand/getBrand/{id}").permitAll()
                    .antMatchers("/api/v1/category/getAll").permitAll()
                    .antMatchers("/api/v1/category/getCategory/{id}").permitAll()
                    .antMatchers("/api/v1/product/getAll").permitAll()
                    .antMatchers("/api/v1/product/getProduct/{id}").permitAll()
                    .antMatchers("/api/v1/product/getAllByIdCategory").permitAll()
                    .antMatchers("/api/v1/invoice/create").permitAll()
                    .anyRequest().authenticated()
                .and()
                .exceptionHandling().authenticationEntryPoint(((request, response, authException) -> {
                    Map<String, Object> responseMap = new HashMap<>();
                    ObjectMapper mapper = new ObjectMapper();
                    response.setStatus(401);
                    responseMap.put("error", true);
                    responseMap.put("message", "unauthorized");
                    response.setHeader("content-type", "application/json");
                    String responseMsg = mapper.writeValueAsString(responseMap);
                    response.getWriter().write(responseMsg);
                }))
                .and()
                .sessionManagement().sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                .and()
                .apply(new JwtTokenFilterConfigurer(jwtTokenProvider));
    }


    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder(12);
    }

    @Bean
    @Override
    protected AuthenticationManager authenticationManager() throws Exception {
        return super.authenticationManager();
    }

}
