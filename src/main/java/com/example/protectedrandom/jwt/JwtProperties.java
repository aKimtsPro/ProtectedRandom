package com.example.protectedrandom.jwt;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@Getter
@Setter
@ConfigurationProperties("application.jwt")
public class JwtProperties {

    private String secret;
    private long expiresAt;
    private String prefix;
    private String header;

}
