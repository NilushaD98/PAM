package com.pam.PAM.config;

import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.repo.UserRepoMongo;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Configuration
@RequiredArgsConstructor
public class ApplicationConfig {
    private final UserRepoMongo userRepoMongo;

    private final BCryptPasswordEncoder passwordEncoder;

    @Bean
    public UserDetailsService userDetailsService() {
     return new UserDetailsService() {
         @Override
         public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
             List<ApplicatioUserMongo> applicationUserw = userRepoMongo.findByUserName(username);
             ApplicatioUserMongo applicationUser = applicationUserw.get(0);
//             ApplicationUser applicationUser = repository.findApplicationUserByUsernameEquals(username);
             if(applicationUser ==null){
                 throw new UsernameNotFoundException("username not found");
             }
             Collection<SimpleGrantedAuthority> simpleGrantedAuthorities = new ArrayList<>();
             simpleGrantedAuthorities.add(new SimpleGrantedAuthority(applicationUser.getRole()));
             return new User(
                     applicationUser.getUsername(),
                     applicationUser.getPassword(),
                     simpleGrantedAuthorities
             );
         }
     };
    }

    @Bean
    public AuthenticationProvider authenticationProvider() {
        DaoAuthenticationProvider authProvider = new DaoAuthenticationProvider();
        authProvider.setUserDetailsService(userDetailsService());
        authProvider.setPasswordEncoder(passwordEncoder);
        return authProvider;
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
}
