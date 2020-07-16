package com.cds.regression.services;

import com.cds.regression.commons.Response;
import com.cds.regression.model.Projects;

import org.springframework.stereotype.Controller;

@Controller
public interface ProjectsService {
  
  Response saveProject(Projects project);

  Response updateProject(Projects project);
  
  Response deleteProject(String id);
  
  Response findAll();

  Response findById(String id);
  
  Response findByProjectName(String projectName);
} // End of ProjectsService.