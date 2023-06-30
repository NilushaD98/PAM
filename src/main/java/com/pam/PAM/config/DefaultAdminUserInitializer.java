package com.pam.PAM.config;

import com.pam.PAM.model.ApplicatioUserMongo;
import com.pam.PAM.repo.UserRepoMongo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.UUID;

@Component
public class DefaultAdminUserInitializer implements ApplicationRunner {

    @Autowired
    private UserRepoMongo userRepoMongo;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private BCryptPasswordEncoder cryptPasswordEncoder;
    @Override
    public void run(ApplicationArguments args) throws Exception {

        List<ApplicatioUserMongo> adminMalith123 = userRepoMongo.findByUserName("adminMalith123");
        if(adminMalith123.isEmpty()){
            ApplicatioUserMongo applicationUser = new ApplicatioUserMongo(
                    UUID.randomUUID().toString().split("-")[0],
                    "adminMalith123",
                    cryptPasswordEncoder.encode("admin1234"),
                    "adminMalith@gmail.com",
                    "ADMIN",
                    cryptPasswordEncoder.encode("123456789")
            );
            userRepoMongo.insert(applicationUser);
        }
    }
}