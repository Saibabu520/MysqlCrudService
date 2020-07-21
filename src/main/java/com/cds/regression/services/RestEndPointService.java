package com.cds.regression.services;

import com.cds.regression.commons.Response;
import com.cds.regression.model.RestEndPoints;
import org.springframework.stereotype.Controller;

@Controller
public interface RestEndPointService {

  Response saveRestEndPoints(RestEndPoints restEndPoints);

  Response updateRestEndPoints(RestEndPoints restEndPoints);

  Response deleteById(String id);

  Response deleteByEnvId(String envId);

  Response deleteByModuleId(String moduleId);

  Response findAll();

  Response findById(String id);

  Response findByEnvId(String envId);

  Response findByModuleId(String moduleId);

  Response findByModuleIdEnvId(String moduleId, String envId);

} // End of RestEndPointService.