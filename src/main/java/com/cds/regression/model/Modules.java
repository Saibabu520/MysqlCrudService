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

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Entity
@Table(name = "modules")
@AllArgsConstructor
@NoArgsConstructor
// @JsonIgnoreProperties({ "endPoints" })
public class Modules implements Serializable {

  private static final long serialVersionUID = 1L;

  @Id
  @Column
  private String id;

  @Column
  private String moduleName;

  @ManyToOne(fetch = FetchType.LAZY)
  @JoinColumn(name = "projectId")
  @JsonBackReference(value = "modules")
  private Projects projects;
  
  @JsonManagedReference(value = "module_endpoints")
  @OneToMany(mappedBy = "modules", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
  private List<RestEndPoints> endPoints = new ArrayList<>();
} // End of Modules.