package com.pam.PAM.repo;

import com.pam.PAM.model.ApplicatioUserMongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface UserRepoMongo extends MongoRepository<ApplicatioUserMongo,String> {
    @Query(value = "{ 'username' : '?0' }" )
    List<ApplicatioUserMongo> findByUserName(String username);

    @Query(value = "{'username': '?0'}")
    String deleteApplicationUserByUsernameEquals(String username);
}
