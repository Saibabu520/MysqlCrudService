package com.cds.regression.commons;

import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Projects;
import com.cds.regression.services.ProjectsService;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class UtilFunctions {

  @Autowired
  private ProjectsService projectsService;
  
  /**
   * Method which fetch project data and convert that generic Object to 
   * Projects.class object.
   * 
   * @param- projName
   * @return- Projects pojo object.
   * @throws- IOException.
  */
  public Projects fetchProjectData(String projId) throws IOException {
    
    String jsonObj = null;
    ObjectMapper objectMapper = new ObjectMapper();
    Projects project = null;
    try {
      jsonObj = new Gson().toJson(projectsService.findById(projId).getData());
      project = objectMapper.readValue(jsonObj, Projects.class);
    } catch (Exception e) {
      throw new DataException(e.getMessage());
    }
    
    return project;
  } // End of fetchProjectData.
 
  /** 
   * The Method will return current date in specified format.
  */
  public static String getCurrentDate() {

    final SimpleDateFormat sdf = new SimpleDateFormat(ConstantsUtil.ISO_DATE,Locale.ENGLISH);
    return sdf.format(new Date());
  } // End of getCurrentDate.
} // End of UtilFunctions.