package com.pam.PAM.service.serviceIMPL;

import com.auth0.jwt.JWT;
import com.auth0.jwt.algorithms.Algorithm;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.pam.PAM.dto.request.RequestAuthenticateDTO;
import com.pam.PAM.dto.response.ResponseAuthenticateSucessDTO;
import com.pam.PAM.exceptions.BadAttributeCredential;
import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.model.ApplicationUser;
import com.pam.PAM.model.Machinemongo;
import com.pam.PAM.model.Machines;
import com.pam.PAM.repo.MachineRepo;
import com.pam.PAM.repo.MachineRepoMongo;
import com.pam.PAM.repo.UserRepo;
import com.pam.PAM.repo.UserRepoMongo;
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

import java.util.Date;
import java.util.List;


@Service
@RequiredArgsConstructor
public class AuthenticateService {
    private final UserRepo repository;
    private final BCryptPasswordEncoder passwordEncoder;
    private final AuthenticationManager authenticationManager;
    private final UserRepoMongo userRepoMongo;
    private  final MachineRepoMongo machineRepoMongo;

    public ResponseAuthenticateSucessDTO authenticate(RequestAuthenticateDTO request) {
        List<ApplicatioUserMongo> byUserName = userRepoMongo.findByUserName(request.getUsername());

        List<Machinemongo> byMachinename = machineRepoMongo.findByMachinename(request.getMachineName());
        System.out.println(byMachinename);

        if(
                passwordEncoder.matches(request.getNic(),userRepoMongo.findByUserName(request.getUsername()).get(0).getNic())
        ){
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
                    .withClaim("roles", applicationUser.getRole())
                    .sign(algorithm);
            if(byUserName.get(0).getRole().equals("USER")){
                return new ResponseAuthenticateSucessDTO(
                        "0",
                        access_token,
                        byMachinename.get(0).getMachineIP(),
                        byMachinename.get(0).getUsername(),
                        byMachinename.get(0).getPassword(),
                        byMachinename.get(0).getMachineOS()
                );
            }else {
                return new ResponseAuthenticateSucessDTO(
                        "1",
                        access_token,
                        null,
                        null,
                        null,
                        null
                );
            }
        }else {
            throw  new BadAttributeCredential();
        }

    }

}
