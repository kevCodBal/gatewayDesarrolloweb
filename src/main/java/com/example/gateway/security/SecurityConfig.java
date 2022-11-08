package com.example.gateway.security;


import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.reactive.EnableWebFluxSecurity;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.oauth2.client.oidc.authentication.OidcIdTokenDecoderFactory;
import org.springframework.security.oauth2.client.oidc.web.server.logout.OidcClientInitiatedServerLogoutSuccessHandler;
import org.springframework.security.oauth2.client.registration.ReactiveClientRegistrationRepository;
import org.springframework.security.web.server.SecurityWebFilterChain;
import org.springframework.security.web.server.authentication.logout.ServerLogoutHandler;
import org.springframework.security.web.server.authentication.logout.ServerLogoutSuccessHandler;

@Configuration
@EnableWebFluxSecurity
public class SecurityConfig {

        @Bean
        public SecurityWebFilterChain filterChain(ServerHttpSecurity http, ServerLogoutSuccessHandler handler){
            http.
                    authorizeExchange()
                    .pathMatchers("/actuator/**", "/", "/logout.html")
                    .permitAll()
                    .and()
            .authorizeExchange()
                    .anyExchange()
                    .authenticated()
                    .and()
                    .oauth2Login()
                    .and()
                    .logout()
                    .logoutSuccessHandler(handler);

            return http.build();

        }

        @Bean
        public ServerLogoutSuccessHandler keycloakLogoutSuccessHandler(ReactiveClientRegistrationRepository repository){
            OidcClientInitiatedServerLogoutSuccessHandler iodcLogoutSuccessHandler =
                    new OidcClientInitiatedServerLogoutSuccessHandler(repository);

            iodcLogoutSuccessHandler.setPostLogoutRedirectUri("{baseUrl}/logout.html");

            return  iodcLogoutSuccessHandler;
        }

}
