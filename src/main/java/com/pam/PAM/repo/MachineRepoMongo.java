package com.pam.PAM.repo;


import com.pam.PAM.model.Machinemongo;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableMongoRepositories
public interface MachineRepoMongo extends MongoRepository<Machinemongo,String> {
    @Query(value = "{ 'machineName' : '?0' }" )
    List<Machinemongo> findByMachinename(String machineName);

}
