package com.cds.regression.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@Data
@Entity
@Table(name = "database_details")
@NoArgsConstructor
@AllArgsConstructor
public class Databases {

  @Id
  @Column
  private String id;

  @Column
  private String projectName;
  
  @Column
  private String projectId;
  
  @Column
  private String ip;
  
  @Column
  private String dbPort;
  
  @Column
  private String serviceName;

  @Column
  private String environment;

  @Column
  private String moduleName;

  @Column
  private String dbUserName;
  
  @Column
  private String dbPassword;
  
  @Column
  private String sslKey;

  @Column
  private String serverName;
  
  @Column
  private String createdDate;
  
  @Column
  private String memory;
  
  @Column
  private String hardDisk;
  
  @Column
  private String cpuCores;
  
  @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"}) 
  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "environmentid", referencedColumnName = "id")
  private Environments envs;
} // End of Databases.