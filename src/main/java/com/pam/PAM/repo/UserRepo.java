package com.pam.PAM.repo;

import com.pam.PAM.model.ApplicationUser;
import org.springframework.boot.autoconfigure.data.jpa.JpaRepositoriesAutoConfiguration;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;


import java.util.List;
@Repository
@EnableJpaRepositories
public interface UserRepo extends JpaRepository<ApplicationUser,String> {


    ApplicationUser findApplicationUserByUsernameEquals(String username);

    ApplicationUser findApplicationUserByEmailEquals(String email);
    String deleteApplicationUserByUsernameEquals(String username);


    ApplicationUser findApplicationUserByNicEquals(String nic);
}
