package pl.pb.ogloszeniadrobne.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

@Configuration
class SecurityConfig   {
    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
        http.authorizeHttpRequests(requests -> {
                    try {
                        requests
                                .mvcMatchers("/").permitAll()
                                .mvcMatchers("/img/**", "/styles/**").permitAll()
                                .mvcMatchers("/register", "/confirmation").permitAll()
                                .antMatchers("/h2-console/**").permitAll()
                                .mvcMatchers("/secured", "/change-password").hasAnyRole("USER", "ADMIN")
                                .mvcMatchers("/admin/**").hasRole("ADMIN")
                                .anyRequest().authenticated()
                                .and().csrf().ignoringAntMatchers("/h2-console/**")
                                .and().headers().frameOptions().sameOrigin();
                    } catch (Exception e) {
                        throw new RuntimeException(e);
                    }
                }
        );

        http.formLogin(login -> login.loginPage("/login").permitAll());
        http.logout(logout -> logout
                .logoutRequestMatcher(new AntPathRequestMatcher("/logout/**", HttpMethod.GET.name()))
                .logoutSuccessUrl("/")
        );
        return http.build();
    }
    @Bean
    PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
}