package eu.glutfree.glutfree.model.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotBlank;

public class UserLoginBindingModel {

    private String username;
    private String password;

    public UserLoginBindingModel() {
    }

    @NotBlank(message = "The username cannot be empty!")
    @Length(min = 3, max = 50, message = "The username must contains between 3 and 50 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message = "The password cannot be empty!")
    @Length(min = 5, max = 50, message = "The password must contains between 5 and 50 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
