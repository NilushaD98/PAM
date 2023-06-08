package com.pam.PAM.API;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pam.PAM.dto.request.RequestAuthenticateDTO;
import com.pam.PAM.dto.response.ResponseAuthenticateSucessDTO;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.repo.UserRepo;
import lombok.RequiredArgsConstructor;
import lombok.var;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final UserRepo repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;

    public ResponseAuthenticateSucessDTO authenticate(RequestAuthenticateDTO request) {
        System.out.println(1);
        authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        request.getUsername(),
                        request.getPassword()
                )
        );
        System.out.println(2);

        Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
        ApplicationUser applicationUser = repository.findApplicationUserByUsernameEquals(request.getUsername());

        String access_token = JWT.create()
                .withSubject(applicationUser.getUsername())
                .withExpiresAt(new Date(System.currentTimeMillis() + 10 * 60 * 1000))
                .withIssuer("api/v1/auth")
                .withClaim("roles", applicationUser.getRole())
                .sign(algorithm);

        return new ResponseAuthenticateSucessDTO(
                "200",
                access_token,
                null,
                null,
                null,
                null
        );
    }

}
