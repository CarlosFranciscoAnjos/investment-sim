package org.investmentsimspring.infrastructure.authentication;

import org.investmentsimspring.domain.security.HashHelper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
public class PasswordConfiguration {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return HashHelper.getPasswordEncoder();
    }
}
