package eu.glutfree.glutfree.model.entities;

import eu.glutfree.glutfree.model.entities.enums.RegionEnums;
import org.hibernate.validator.constraints.Length;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.Size;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name="users")
public class UserEntity extends BaseEntity {


  private String username;
  private String password;
  public String email;
  private List<UserRoleEntity> roles = new ArrayList<>();
  private String firstName;
  private String secondName;
  private RegionEnums region;
//  private Boolean isProfileUpdated;




  @Column(name = "username", nullable = false, unique = true)
  @Length(min = 3, max = 20)
  public String getUsername() {
    return username;
  }

  public UserEntity setUsername(String name) {
    this.username = name;
    return this;
  }
  @Column(name = "password", nullable = false)
  @Length(min = 5, max = 60)
  public String getPassword() {
    return password;
  }

  public UserEntity setPassword(String password) {
    this.password = password;
    return this;
  }

  @ManyToMany(fetch = FetchType.EAGER)
  public List<UserRoleEntity> getRoles() {
    return roles;
  }

  public void setRoles(List<UserRoleEntity> roles) {
    this.roles = roles;
  }

  public UserEntity addRole(UserRoleEntity userRoleEntity) {
    this.roles.add(userRoleEntity);
    return this;
  }
  @Email
  public String getEmail() {
    return email;
  }

  public UserEntity setEmail(String email) {
    this.email = email;
    return this;
  }

  @Column(name = "first_name")
  public String getFirstName() {
    return firstName;
  }

  public void setFirstName(String firstName) {
    this.firstName = firstName;
  }
  @Column(name = "second_name")
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

//  public Boolean getProfileUpdated() {
//    return isProfileUpdated;
//  }
//
//  public void setProfileUpdated(Boolean profileUpdated) {
//    isProfileUpdated = profileUpdated;
//  }
}
