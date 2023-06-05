package com.pam.PAM.repo;

import com.pam.PAM.model.ApplicationUser;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface UserRepo extends MongoRepository<ApplicationUser,String> {

    @Query(value = "{username:'?0'}")
    List<ApplicationUser> findByUsername(String username);

    ApplicationUser findByEmailEquals(String email);
    String deleteByUsernameEquals(String username);
//    @Query(value = "{role:'?0'}",fields = "{'username': 1,'password': 1}")
//    List<ApplicationUser> findAll(String admin);

    @Query(value = "{}", fields = "{ 'username' : 1 }")
    List<ApplicationUser> findAllUsernames();
}
