package eu.glutfree.glutfree.model.service;

import eu.glutfree.glutfree.model.entities.enums.RegionEnums;

public class UserUpdateServiceModel {
    private String firstName;
    private String secondName;
    private RegionEnums region;


    public UserUpdateServiceModel() {
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
