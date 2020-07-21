package com.cds.regression.services;

import com.cds.regression.commons.Response;
import com.cds.regression.model.Modules;
import org.springframework.stereotype.Controller;

@Controller
public interface ModuleService {

  Response saveModules(Modules modules);

  Response updateModule(Modules module);

  Response deleteModule(String id);

  Response deleteByProjectId(String projectId);

  Response findAll();

  Response findById(String id);

  Response findByProjectId(String projectId);

} // End of ModuleService.