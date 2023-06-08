package com.pam.PAM.repo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

@Repository
@EnableMongoRepositories
public interface MachineRepoMongo extends MongoRepository<MachineRepoMongo,String> {
}
