package com.vaibhav.config;

import com.vaibhav.exceptionhandling.CustomAccessDeniedHandler;
import com.vaibhav.exceptionhandling.CustomBasicAuthenticationEntryPoint;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import static org.springframework.security.config.Customizer.withDefaults;

@Configuration
@Profile("prod")
public class ProjectProdSecurityConfig {

    @Bean
    SecurityFilterChain defaultSecurityFilterChain(HttpSecurity http) throws Exception {
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().denyAll());
//        http.authorizeHttpRequests((requests) -> requests.anyRequest().permitAll());
        http.requiresChannel(rcc->rcc.anyRequest().requiresSecure()) //only Https
                .csrf(csrfConf->csrfConf.disable())
                .authorizeHttpRequests((requests) -> requests
                .requestMatchers("/myAccount", "/myCards", "/myLoans", "/myBalance").authenticated()
                .requestMatchers("/contact", "/notices","/register").permitAll());
        http.formLogin(withDefaults());
        http.httpBasic(hbc->hbc.authenticationEntryPoint(new CustomBasicAuthenticationEntryPoint()));
        http.exceptionHandling(ehc->ehc.accessDeniedHandler(new CustomAccessDeniedHandler()));
        return http.build();
    }

//    @Bean
//    public UserDetailsService userDetailsService(DataSource dataSource){
//        UserDetails user = User.withUsername("user").password("{noop}12345").authorities("read").build();
//        UserDetails admin = User.withUsername("admin").
//                password("{bcrypt}$2a$12$L9TcvHgtTQN/wLZ4yIFz0OyrUcBsDCCe8mHzzz35s47YE4.agE7Lu").
//                 authorities("admin").build();
//        return new InMemoryUserDetailsManager(user, admin);
//        return new JdbcUserDetailsManager(dataSource);
//    }

    @Bean
    public PasswordEncoder passwordEncoder(){
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }

//    @Bean
//    public CompromisedPasswordChecker compromisedPasswordChecker(){
//        return new HaveIBeenPwnedRestApiPasswordChecker();
//    }
}
