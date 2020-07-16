package com.cds.regression.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "environments")
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({"project","databases"})
public class Environments implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column
  private String id;

  @Column
  private String projectName;

  @Column
  private String environment;

  @Column
  private String moduleName;

  @Column
  private String ip;

  @Column
  private String port;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "projectId")
  private Projects project;
  
  @OneToMany(mappedBy= "envs", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<Databases> databases = new ArrayList<>();
} // End of Environments.