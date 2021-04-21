package eu.glutfree.glutfree.model.service;

import eu.glutfree.glutfree.model.entities.enums.RegionEnums;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;

public class UserRegistrationServiceModel {

    private Long id;
    public String username;
    public String password;

    public String email;

    private String firstName;
    private String secondName;
    private RegionEnums region;


    public UserRegistrationServiceModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Length(min =2 , message = "Username length must be min two chars")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @Length(min = 3 , message = "Password length must be min three chars")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Email(message = "Enter valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getSecondName() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName = secondName;
    }

    public RegionEnums getRegion() {
        return region;
    }

    public void setRegion(RegionEnums region) {
        this.region = region;
    }
}
