package com.pam.PAM.repo;

import com.pam.PAM.model.Machines;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@EnableJpaRepositories
public interface MachineRepo extends JpaRepository<Machines,String> {

//
//    List<Machines> findAllMachines();

    Machines findByMachineNameEquals(String machineName);
}
