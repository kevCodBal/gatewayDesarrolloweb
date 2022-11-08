package com.example.gateway.controller;

import org.springframework.security.oauth2.client.OAuth2AuthorizedClient;
import org.springframework.security.oauth2.client.annotation.RegisteredOAuth2AuthorizedClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.WebSession;
import reactor.core.publisher.Mono;

import java.security.Principal;

@RestController
public class GatewayController {

//    @GetMapping("/")
//    public Mono<String> index(WebSession session){
//        return  Mono.just(session.getId());
//    }
//
//    @GetMapping("/token")
//    public Mono<String>  getToken(@RegisteredOAuth2AuthorizedClient OAuth2AuthorizedClient client){
//        return Mono.just(client.getAccessToken().getTokenValue());
//    }

    @GetMapping("/user")
    public String index(Principal principal){
        return principal.getName();
    }




}
