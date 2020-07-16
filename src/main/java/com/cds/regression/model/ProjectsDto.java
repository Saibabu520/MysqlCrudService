package com.cds.regression.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ProjectsDto {

  private String id;

  private String projectName;

  private String projectCode;

  private String projectType;

  private String clientName;
  
  private String country;
  
  private String description;
  
  private List<Environments> environments = new ArrayList<>(); 
} // End of ProjectsDto.