package com.pam.PAM.model;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@AllArgsConstructor@NoArgsConstructor@Data
@Entity
public class Machines {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int machineID;
    private String machineIP;
    private String machineName;
    private String username;
    private String password;
    private String oSystem;

    public Machines(String machineIP, String machineName, String username, String password, String oSystem) {
        this.machineIP = machineIP;
        this.machineName = machineName;
        this.username = username;
        this.password = password;
        this.oSystem = oSystem;
    }
}
