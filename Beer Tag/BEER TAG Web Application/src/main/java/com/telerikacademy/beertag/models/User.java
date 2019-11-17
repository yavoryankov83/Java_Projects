package com.telerikacademy.beertag.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;

@Entity
@Table(name = "users")
public class User {

  private static final String NAME_LENGTH_MESSAGE =
          "Username should be between 3 and 50 symbols.";
  private static final String EMAIL_LENGTH_MESSAGE =
          "User email should be between 10 and 50 symbols.";
  private static final String PASSWORD_LENGTH_MESSAGE =
          "User password should be more than 3 symbols.";

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "username", nullable = false, unique = true)
  @Size(min = 3, max = 50, message = NAME_LENGTH_MESSAGE)
  private String username;

  @Column(name = "email", nullable = false, unique = true)
  @Email
  @Size(min = 10, max = 50, message = EMAIL_LENGTH_MESSAGE)
  private String email;

  @Column(name = "password")
  @Size(min = 4, message = PASSWORD_LENGTH_MESSAGE)
  private String password;

  @Column(name = "photo")
  private String photo;

  @Column(name = "enabled")
  private Boolean enabled = true;

  @Column(name = "passwordConfirmation")
  private String passwordConfirmation;

  @OneToMany(mappedBy = "user")
  @JsonIgnore
  private List<Rating> ratings;

  public User() {
  }

  public User(String username,
              String email,
              String password, String photo) {
    this();
    this.username = username;
    this.email = email;
    this.password = password;
    this.photo = photo;
  }

  public Integer getId() {
    return id;
  }

  public void setId(Integer id) {
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

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoto() {
    return photo;
  }

  public void setPhoto(String photo) {
    this.photo = photo;
  }

  public List<Rating> getRatings() {
    return ratings;
  }

  public void setRatings(List<Rating> ratings) {
    this.ratings = ratings;
  }

  public void addRating(Rating rating) {
    ratings.add(rating);
  }

  public void deleteRating(Rating rating) {
    ratings.remove(rating);
  }

  public Boolean getEnabled() {
    return enabled;
  }

  public void setEnabled(Boolean enabled) {
    this.enabled = enabled;
  }

  public String getPasswordConfirmation() {
    return passwordConfirmation;
  }

  public void setPasswordConfirmation(String passwordConfirmation) {
    this.passwordConfirmation = passwordConfirmation;
  }
}
