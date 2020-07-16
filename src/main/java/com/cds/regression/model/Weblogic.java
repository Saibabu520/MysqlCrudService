package com.cds.regression.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Data
@Entity
@Table(name = "weblogic_details")
@NoArgsConstructor
@AllArgsConstructor
public class Weblogic {

  @Id
  @Column
  private String id;

  @Column
  private String projectName;
  
  @Column
  private String projectId;

  @Column
  private String environment;

  @Column
  private String moduleName;
  
  @Column
  private String messageBroker;

  @Column
  private String ip;
  
  @Column
  private String port;
  
  @Column
  private String connectionFactory;

  @Column
  private String serverName;
  
  @Column
  private String serverModule;
  
  @Column
  private String clientId;
  
  @Column
  private String subscriber;
} // End of Weblogic.