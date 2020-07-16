package com.cds.regression.controller;

import java.util.concurrent.CompletableFuture;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cds.regression.commons.Response;
import com.cds.regression.exceptions.ExceptionProcessor;
import com.cds.regression.model.Weblogic;
import com.cds.regression.services.WebLogicService;

@Log4j2
@RestController
@RequestMapping("/weblogic")
public class WeblogicController {
  
  @Autowired
  private WebLogicService weblogicService;
  
  @Autowired
  private ExceptionProcessor exceptionProcessor;
  
  /**
   * Method which will fetch all welogic from database by calling 
   * appropriate method of weblogicService.
   * 
   * @return- List of weblogic.
  */
  @Async
  @GetMapping(value = "/all")
  public CompletableFuture<Response> getAllWeblogics() {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.findAll());
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of getAllWeblogic.
  
  /**
   * Method which will save or update weblogic in database by calling 
   * appropriate method of weblogicService.
   * 
   * @param- weblogic data as request.
   * @return- Message
  */
  @Async
  @PostMapping(value = "/save")
  public CompletableFuture<Response> saveWebLogic(@RequestBody Weblogic weblogic) {
   
    try {
      return CompletableFuture.completedFuture(weblogicService.saveWebLogic(weblogic));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of saveWebLogic.
  
  /**
   * Method which will save or update weblogic in database by calling 
   * appropriate method of weblogicService.
   * 
   * @param- weblogic data as request.
   * @return- success or failure message.
  */
  @Async
  @PostMapping(value = "/update")
  public CompletableFuture<Response> updateWebLogic(@RequestBody Weblogic weblogic) {
   
    try {
      return CompletableFuture.completedFuture(weblogicService.updateWebLogic(weblogic));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of updateWebLogic.
  
  /**
   * Method which will delete weblogic data from database based on given project id 
   * by calling appropriate method of weblogicService.
   * 
   * @param- project id.
   * @return- success or failure message.
  */
  @Async
  @DeleteMapping(value = "/delete/{id}")
  public CompletableFuture<Response> deleteWeblogicById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.deleteWeblogicById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of deleteWeblogicById.

  /**
   * Method which will fetch weblogic data from database based on given project id 
   * by calling appropriate method of weblogicService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/id/{id}")
  public CompletableFuture<Response> findById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.findById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of findById.
  
  /**
   * Method which will fetch weblogic data from database based on given project name 
   * by calling appropriate method of weblogic Service.
   * 
   * @param- project id.
   * @return- success or failure message.
  */
  @Async
  @GetMapping(value = "/project/{projectId}")
  public CompletableFuture<Response> findByProjectId(@PathVariable String projectId) {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.findByProjectId(projectId));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of findByProjectId.
  
  /**
   * Method which will fetch weblogic data from database based on given project name 
   * by calling appropriate method of welogicService.
   * 
   * @param- project name.
   * @return- success or failure message.
  */
  @Async
  @GetMapping(value = "/name/{projectName}")
  public CompletableFuture<Response> findByProjectName(@PathVariable String projectName) {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.findByProjectName(projectName));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of findByProjectName.
  
  /**
   * Method which will fetch weblogic data from database based on given module name 
   * by calling appropriate method of welogicService.
   * 
   * @param- module name.
   * @return- success or failure message.
  */
  @Async
  @GetMapping(value = "/module/{module}")
  public CompletableFuture<Response> findByModuleName(@PathVariable String module) {
    
    try {
      return CompletableFuture.completedFuture(weblogicService.findByModuleName(module));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of findByModuleName.
  
  /**
   * Method which will fetch weblogic data from database based on given module name 
   * and project name by calling appropriate method of welogicService.
   * 
   * @param- module name, project name.
   * @return- success or failure message.
  */
  @Async
  @GetMapping(value = "/module-project")
  public CompletableFuture<Response> findByModuleProject(@RequestParam String projectName, 
                                                         @RequestParam String moduleName) {
    try {
      return CompletableFuture.completedFuture(weblogicService
                                           .findByProjModule(projectName, moduleName));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of findByModuleName.
} // End of WeblogicController.