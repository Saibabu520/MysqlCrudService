package com.cds.regression.model;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.fasterxml.jackson.annotation.JsonBackReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "rest_endpoints")
@AllArgsConstructor
@NoArgsConstructor
public class RestEndPoints implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column
  private String id;

  @Column
  private String restUrl;

  @Column
  private String httpMethod;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "environmentId")
  @JsonBackReference(value = "environment")
  private Environments envrnts;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "moduleId")
  @JsonBackReference(value = "module_endpoints")
  private Modules modules;
} // End of RestEndPoints.