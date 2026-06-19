package eu.glutfree.glutfree.model.view;

import eu.glutfree.glutfree.model.entities.enums.RegionEnums;

import java.util.List;

public class UserViewModel {

    private Long id;
    private String username;
    private String email;
    private String firstName;
    private String secondName;
    private RegionEnums region;
    private List<String> roles;

    public UserViewModel() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

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

    public List<String> getRoles() {
        return roles;
    }

    public void setRoles(List<String> roles) {
        this.roles = roles;
    }
}
