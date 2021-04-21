package eu.glutfree.glutfree.model.bindings;

import eu.glutfree.glutfree.model.entities.enums.RegionEnums;
import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserUpdateBindingModel {
    private String firstName;
    private String secondName;
    private RegionEnums region;
//    private Boolean isProfileUpdated;


    public UserUpdateBindingModel() {
    }
    @Length(min = 1, max = 50, message = "The first name must contains between 1 and 50 characters.")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }
    @Length(min = 1, max = 50, message = "The second name must contains between 1 and 50 characters.")
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

//    public Boolean getProfileUpdated() {
//        return isProfileUpdated;
//    }
//
//    public void setProfileUpdated(Boolean profileUpdated) {
//        isProfileUpdated = profileUpdated;
//    }
}
