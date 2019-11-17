package com.telerikacademy.beertag.models;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@Entity
@Table(name = "status_values")
public class StatusValue {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column(name = "id")
  private Integer id;

  @Column(name = "status_value")
  @NotEmpty
  @NotNull
  private String statusValue;

  @OneToMany(mappedBy = "statusValue")
  @JsonIgnore
  private List<Status> statuses;

  public StatusValue() {
  }

  public StatusValue(String statusValue) {
    this();
    this.statusValue = statusValue;
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getStatusValue() {
    return statusValue;
  }

  public void setStatusValue(String statusValue) {
    this.statusValue = statusValue;
  }
}
