package com.cds.regression.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "projects")
@AllArgsConstructor
@NoArgsConstructor
public class Projects implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column
  private String id;

  @Column
  private String projectName;

  @Column
  private String projectCode;

  @Column
  private String projectType;

  @Column
  private String clientName;
  
  @Column
  private String country;
  
  @Column
  private String description;
  
  @OneToMany(mappedBy= "project", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Environments> environments = new ArrayList<>();
} // End of Projects.