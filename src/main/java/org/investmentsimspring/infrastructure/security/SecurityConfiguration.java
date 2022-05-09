package org.investmentsimspring.infrastructure.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;

import static org.investmentsimspring.domain.security.UserRole.ADMIN;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration extends WebSecurityConfigurerAdapter {

    private final UserDetailsService usersService;
    private final PasswordEncoder passwordEncoder;

    @Autowired
    public SecurityConfiguration(@Qualifier("UsersService") UserDetailsService usersService,
                                 PasswordEncoder passwordEncoder) {
        this.usersService = usersService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // super.configure(http);
        getBasicAuth(http);
    }

    private void getBasicAuth(HttpSecurity http) throws Exception {
        http.csrf().disable() // cross site request forgery
                .authorizeRequests()
                .antMatchers("/", "/api/status", "index", "/css/*", "/js/*").permitAll()
                .antMatchers("/admin/api/**").hasRole(ADMIN.name())
                .anyRequest()
                .authenticated()
                .and()
                .httpBasic();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
//        super.configure(auth);
        auth.authenticationProvider(daoAuthenticationProvider());
    }

    @Bean
    public AuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();
        provider.setPasswordEncoder(passwordEncoder);
        provider.setUserDetailsService(usersService);
        return provider;
    }

//    @Override
//    @Bean
//    protected UserDetailsService userDetailsService() {
//        // return super.userDetailsService();
//        return getInMemoryUserDetailsManager();
//    }

    private InMemoryUserDetailsManager getInMemoryUserDetailsManager() {
        UserDetails user = User.builder()
                .username("admin")
                .password(passwordEncoder.encode("admin"))
                // .roles(ADMIN.name())
                .authorities(ADMIN.getGrantedAuthorities())
                .build();
        return new InMemoryUserDetailsManager(user);
    }

}
