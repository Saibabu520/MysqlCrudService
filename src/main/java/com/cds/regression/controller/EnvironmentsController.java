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
import org.springframework.web.bind.annotation.RestController;

import com.cds.regression.commons.Response;
import com.cds.regression.exceptions.ExceptionProcessor;
import com.cds.regression.model.Environments;
import com.cds.regression.services.EnvironmentsService;

@Log4j2
@RestController
@RequestMapping("/environments")
public class EnvironmentsController {

  @Autowired
  private EnvironmentsService environmentsService;
  
  @Autowired
  private ExceptionProcessor exceptionProcessor;
  
  /**
   * Method which will fetch all environments from database by calling 
   * appropriate method of environmentsService.
   * 
   * @return- List of environments.
  */
  @Async
  @GetMapping(value = "/all")
  public CompletableFuture<Response> getAllEnvs() {
    
    try {
      return CompletableFuture.completedFuture(environmentsService.findAll());
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of getAllEnvs.
  
  /**
   * Method which will save or update environment in database by calling 
   * appropriate method of environmentsService.
   * 
   * @param- environment data as request.
   * @return- Message
  */
  @Async
  @PostMapping(value = "/save")
  public CompletableFuture<Response> saveEnvironment(@RequestBody Environments env) {
   
    try {
      return CompletableFuture.completedFuture(environmentsService.saveEnvironment(env));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of saveEnvironment.
  
  /**
   * Method which will save or update environments in database by calling 
   * appropriate method of environmentsService.
   * 
   * @param- Environment data as request.
   * @return- success or failure message.
  */
  @Async
  @PostMapping(value = "/update")
  public CompletableFuture<Response> updateEnvironment(@RequestBody Environments env) {
   
    try {
      return CompletableFuture.completedFuture(environmentsService.updateEnvironment(env));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of updateEnvironment.
  
  /**
   * Method which will delete environment data from database based on given
   * environment id by calling appropriate method of environmentsService.
   * 
   * @param- environment id.
   * @return- success or failure message.
  */
  @Async
  @DeleteMapping(value = "/delete/id/{id}")
  public CompletableFuture<Response> deleteEnvironmentById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(environmentsService.deleteEnvironmentById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of deleteEnvironmentById.
  
  /**
   * Method which will delete environment data from database based on given
   * environment ip by calling appropriate method of environmentsService.
   * 
   * @param- environment ip.
   * @return- success or failure message.
  */
  @Async
  @DeleteMapping(value = "/delete/ip/{ip}")
  public CompletableFuture<Response> deleteEnvironmentByIp(@PathVariable String ip) {
    
    try {
      return CompletableFuture.completedFuture(environmentsService.deleteEnvironmentByIp(ip));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of deleteEnvironmentByIp.
  
  /**
   * Method which will fetch environment data from database based on given environment id 
   * by calling appropriate method of environmentsService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/id/{id}")
  public CompletableFuture<Response> environmentById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(environmentsService.findById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of environmentById.
  
  /**
   * Method which will fetch environment data from database based on given project name 
   * by calling appropriate method of environmentsService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/name/{projectName}")
  public CompletableFuture<Response> environmentByProjName(@PathVariable String projectName) {
    
    try {
      return CompletableFuture.completedFuture(environmentsService.findByProjectName(projectName));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of environmentByProjName.
} // End of EnvironmentsController.