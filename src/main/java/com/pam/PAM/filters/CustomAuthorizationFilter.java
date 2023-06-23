package com.pam.PAM.filters;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.pam.PAM.exceptions.BadAttributeCredential;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.repo.UserRepoMongo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;

import static com.google.common.net.HttpHeaders.AUTHORIZATION;

import static org.springframework.http.HttpStatus.FORBIDDEN;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@Slf4j
@Component
@RequiredArgsConstructor
public class CustomAuthorizationFilter extends OncePerRequestFilter {

    private final UserRepoMongo userRepoMongo;
    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain filterChain) throws ServletException, IOException {
        if(request.getServletPath().equals("/api/v1/auth/authenticate") || request.getServletPath().equals("/logout")){
            filterChain.doFilter(request,response);
        }else{
            String authorizationHeader = request.getHeader(AUTHORIZATION);

            if(authorizationHeader != null && authorizationHeader.startsWith("Bearer ")){

                try{String token = authorizationHeader.substring("Bearer ".length());
                    Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
                    JWTVerifier jwtVerifier = JWT.require(algorithm).build();
                    DecodedJWT decodedJWT  = jwtVerifier.verify(token);
                    String username = decodedJWT.getSubject();
                    String nic = String.valueOf(decodedJWT.getClaim("nic")).replaceAll("\"","");
                    Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
                    List<ApplicatioUserMongo> byUserName = userRepoMongo.findByUserName(username);
                    ApplicatioUserMongo applicationUser = byUserName.get(0);
                    if(nic.equals(applicationUser.getNic())){
                        simpleGrantedAuthorities.add(new SimpleGrantedAuthority(applicationUser.getRole()));
                        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken =
                                new UsernamePasswordAuthenticationToken(username,null,simpleGrantedAuthorities);
                        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
                        filterChain.doFilter(request,response);
                    }else {
                        throw new BadAttributeCredential();
                    }

                }
                catch (Exception exception) {
                    log.error("Error login : {}", exception.getMessage());
                    response.setHeader("error", exception.getMessage());
                    response.setStatus(FORBIDDEN.value());
                    Map<String, String> errors = new HashMap<>();
                    errors.put("error message : ", exception.getMessage());
                    response.setContentType(APPLICATION_JSON_VALUE);
                    new ObjectMapper().writeValue(response.getOutputStream(), errors);
                }
            }else {
                filterChain.doFilter(request,response);
            }
        }
    }
}
