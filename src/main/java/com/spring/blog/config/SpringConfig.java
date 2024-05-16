package com.spring.blog.config;

import com.spring.blog.security.JwtAuthenticationEntryPoint;
import com.spring.blog.security.JwtAuthenticationFilter;
import io.swagger.v3.oas.annotations.enums.SecuritySchemeType;
import io.swagger.v3.oas.annotations.security.SecurityScheme;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableMethodSecurity
@SecurityScheme(
        type = SecuritySchemeType.HTTP,
        name = "Bearer Authentication",
        bearerFormat = "JWT",
        scheme = "Bearer"
)
public class SpringConfig {



    private JwtAuthenticationEntryPoint authenticationEntryPoint;

    private JwtAuthenticationFilter authenticationFilter;

    public SpringConfig(
                          JwtAuthenticationEntryPoint authenticationEntryPoint,
                          JwtAuthenticationFilter authenticationFilter){

        this.authenticationEntryPoint = authenticationEntryPoint;
        this.authenticationFilter = authenticationFilter;
    }

    @Bean
    public static PasswordEncoder passwordEncoder(){
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

//    @Bean
//    SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//
//        http.csrf().disable()
//                .authorizeHttpRequests((authorize) ->
//                        //authorize.anyRequest().authenticated()
//                        authorize.requestMatchers(HttpMethod.GET, "/api/**").permitAll()
//                                .requestMatchers("/api/auth/**").permitAll()
//                                .anyRequest().authenticated()
//
//                ).exceptionHandling( exception -> exception
//                        .authenticationEntryPoint(authenticationEntryPoint)
//                ).sessionManagement( session -> session
//                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)
//                );
//
//        http.addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
//
//        return http.build();
//    }

    @Bean
    SecurityFilterChain securityFilterChain (HttpSecurity httpSecurity) throws Exception {
//disabling csrf
        httpSecurity.csrf(
                (http)->{
                    try {
                        http.disable().authorizeHttpRequests(
                                        // need auth for these urls pattern
                                        (auth)->auth.requestMatchers(HttpMethod.GET,"/api/**").permitAll()
                                                .requestMatchers(HttpMethod.POST,"/api/auth/**").permitAll()
                                                .requestMatchers(HttpMethod.GET,"/swagger-ui/**").permitAll()
                                                .requestMatchers(HttpMethod.GET,"/api-docs/**").permitAll()
                                                .anyRequest().authenticated())
                                .exceptionHandling(ex-> ex.authenticationEntryPoint(authenticationEntryPoint))
                                .sessionManagement( session -> session
                                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS))
                                .addFilterBefore(authenticationFilter, UsernamePasswordAuthenticationFilter.class);
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }

                }
        );
        return httpSecurity.build();
    }



//    @Bean
//    public UserDetailsService userDetailsService(){
//        UserDetails ramesh = User.builder()
//                .username("ramesh")
//                .password(passwordEncoder().encode("ramesh"))
//                .roles("USER")
//                .build();
//
//        UserDetails admin = User.builder()
//                .username("admin")
//                .password(passwordEncoder().encode("admin"))
//                .roles("ADMIN")
//                .build();
//        return new InMemoryUserDetailsManager(ramesh, admin);
//    }
}
