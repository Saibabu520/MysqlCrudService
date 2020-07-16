package com.cds.regression.services;

import com.cds.regression.commons.Response;
import com.cds.regression.model.Weblogic;

import org.springframework.stereotype.Controller;

@Controller
public interface WebLogicService {
  
  Response saveWebLogic(Weblogic weblogic);

  Response updateWebLogic(Weblogic weblogic);
  
  Response deleteWeblogicById(String id);
 
  Response findAll();

  Response findById(String id);
  
  Response findByProjectId(String projectId);
  
  Response findByProjectName(String projectName);
  
  Response findByModuleName(String moduleName);

  Response findByProjModule(String projName, String moduleName);
} // End of WebLogicService.