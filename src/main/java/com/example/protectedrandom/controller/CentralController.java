package com.example.protectedrandom.controller;

import com.example.protectedrandom.form.LoginForm;
import com.example.protectedrandom.jwt.JwtProvider;
import com.example.protectedrandom.service.NumberService;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Random;

@RestController
@RequestMapping("/api")
public class CentralController {

    private final JwtProvider provider;
    private final AuthenticationManager authManager;
    private final NumberService service;

    public CentralController(JwtProvider provider, AuthenticationManager authManager, NumberService service) {
        this.provider = provider;
        this.authManager = authManager;
        this.service = service;
    }

    @PostMapping("/login")
    public String login(@Valid @RequestBody LoginForm form){
        Authentication auth = authManager.authenticate( new UsernamePasswordAuthenticationToken(form.getUsername(),form.getPassword()));
        return provider.createToken( auth );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/random")
    public long getRandom(Authentication auth){
        return service.generateFor( auth.getName() );
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/generated")
    public List<Long> getMyGenerated(Authentication auth){
        return service.getGeneratedBy( auth.getName() );
    }

}
