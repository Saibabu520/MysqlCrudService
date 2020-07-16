package com.cds.regression.services;

import java.io.IOException;

import org.springframework.stereotype.Component;
import com.cds.regression.commons.Response;
import com.cds.regression.model.Databases;

@Component
public interface DatabasesService {

  Response saveDatabase(Databases database);

  Response updateDatabase(Databases database);
  
  Response deleteDatabase(String id);
  
  Response findAll();

  Response findById(String id);

  Response findByProjectEnv(String projectName, String env) throws IOException;

  Response findByProjectId(String projectId);
} // End of DatabasesService.