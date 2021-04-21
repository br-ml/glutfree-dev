package eu.glutfree.glutfree.model.bindings;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;

public class UserRegistrationBindingModel {
    public String username;
    public String password;
    public String confirmPassword;
    public String email;

    public UserRegistrationBindingModel() {
    }

    @NotBlank(message = "The username cannot be empty!")
    @Length(min = 3, max = 20, message = "The username must contains between 3 and 20 characters.")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    @NotBlank(message = "The password cannot be empty!")
    @Length(min =5, max = 60, message = "The password must contains between 2 and 60 characters.")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
    @NotBlank(message = "The password cannot be empty!")
    @Length(min = 5, max = 60, message = "The password must contains between 2 and 60 characters.")
    public String getConfirmPassword() {
        return confirmPassword;
    }

    public void setConfirmPassword(String confirmPassword) {
        this.confirmPassword = confirmPassword;
    }

    @Email(message = "Enter valid email address")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


}
