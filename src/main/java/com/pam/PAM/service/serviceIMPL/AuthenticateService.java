package com.pam.PAM.service.serviceIMPL;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;

import com.pam.PAM.dto.request.RequestAuthenticateDTO;
import com.pam.PAM.dto.response.ResponseAuthenticateSucessDTO;
import com.pam.PAM.exceptions.BadAttributeCredential;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.repo.MachineRepoMongo;
import com.pam.PAM.repo.UserRepoMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final AuthenticationManager authenticationManager;
    private final UserRepoMongo userRepoMongo;

    public ResponseAuthenticateSucessDTO authenticate(RequestAuthenticateDTO request) {
        List<ApplicatioUserMongo> byUserName = userRepoMongo.findByUserName(request.getUsername());
            authenticationManager.authenticate(
                    new UsernamePasswordAuthenticationToken(
                            request.getUsername(),
                            request.getPassword()
                    )
            );
            Algorithm algorithm = Algorithm.HMAC256("secret".getBytes());
            ApplicatioUserMongo applicationUser = byUserName.get(0);
            String access_token = JWT.create()
                    .withSubject(applicationUser.getUsername())
                    .withExpiresAt(new Date(System.currentTimeMillis() + 24*60*60*1000))
                    .withIssuer("api/v1/auth")
                    .withClaim("nic",byUserName.get(0).getNic())
                    .withClaim("roles", applicationUser.getRole())
                    .sign(algorithm);
            if(byUserName.get(0).getRole().equals("USER")){

                return new ResponseAuthenticateSucessDTO(
                        "0",
                        access_token
                );
            }else {
                return new ResponseAuthenticateSucessDTO(
                        "1",
                        access_token
                );
            }
    }
}
