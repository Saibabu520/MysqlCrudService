package com.cds.regression.services;

import java.io.IOException;

import org.springframework.stereotype.Controller;

import com.cds.regression.commons.Response;
import com.cds.regression.model.Environments;

@Controller
public interface EnvironmentsService {

  Response saveEnvironment(Environments project);

  Response updateEnvironment(Environments project) throws IOException;
  
  Response deleteEnvironmentById(String id);
  
  Response deleteEnvironmentByIp(String ip);
  
  Response findAll();

  Response findById(String id);

  Response findByProjectName(String projectName);

} // End of EnvironmentsService.