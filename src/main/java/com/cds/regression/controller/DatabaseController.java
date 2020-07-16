package com.cds.regression.controller;

import java.util.concurrent.CompletableFuture;

import lombok.extern.log4j.Log4j2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cds.regression.commons.Response;
import com.cds.regression.exceptions.ExceptionProcessor;
import com.cds.regression.model.Databases;
import com.cds.regression.services.DatabasesService;

@Log4j2
@RestController
@RequestMapping("/databases")
public class DatabaseController {

  @Autowired
  private DatabasesService databasesService;
  
  @Autowired
  private ExceptionProcessor exceptionProcessor;
  
  /**
   * Method which will fetch all databases by calling 
   * appropriate method of databasesService.
   * 
   * @return- List of databases.
  */
  @Async
  @GetMapping(value = "/all")
  public CompletableFuture<Response> getAllDatabases() {
    
    try {
      return CompletableFuture.completedFuture(databasesService.findAll());
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of getAllDatabases.
  
  /**
   * Method which will save or update database data by calling 
   * appropriate method of databasesService.
   * 
   * @param- database data as request.
   * @return- Message
  */
  @Async
  @PostMapping(value = "/save")
  public CompletableFuture<Response> saveDatabase(@RequestBody Databases database) {
   
    try {
      return CompletableFuture.completedFuture(databasesService.saveDatabase(database));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of saveDatabase.
  
  /**
   * Method which will save or update database data by calling 
   * appropriate method of databasesService.
   * 
   * @param- database data as request.
   * @return- success or failure message.
  */
  @Async
  @PutMapping(value = "/update")
  public CompletableFuture<Response> updateDatabase(@RequestBody Databases database) {
   
    try {
      return CompletableFuture.completedFuture(databasesService.updateDatabase(database));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of updateDatabase.
  
  /**
   * Method which will delete database data from database based on given
   * database id by calling appropriate method of databasesService.
   * 
   * @param- database id.
   * @return- success or failure message.
  */
  @Async
  @DeleteMapping(value = "/delete/{id}")
  public CompletableFuture<Response> deleteDatabase(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(databasesService.deleteDatabase(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of deleteDatabase.
  
  /**
   * Method which will fetch database data from database based on given database id 
   * by calling appropriate method of databasesService.
   * 
   * @param- database id.
   * @return- success or failure message.
  */
  @Async
  @GetMapping(value = "/id/{id}")
  public CompletableFuture<Response> databaseById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(databasesService.findById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of databaseById.
  
  /**
   * Method which will fetch project data from database based on given project name 
   * by calling appropriate method of databasesService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/projectId/{projectId}")
  public CompletableFuture<Response> databaseByProjName(@PathVariable String projectId) {
    
    try {
      return CompletableFuture.completedFuture(databasesService.findByProjectId(projectId));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    } 
  } // End of databaseByProjName.
  
  /**
   * Method which will fetch project data from database based on given project name 
   * by calling appropriate method of databasesService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/proj-env")
  public CompletableFuture<Response> dbByProjEnv(@RequestParam String projectId, 
                                                 @RequestParam String environment) {
    try {
      return CompletableFuture.completedFuture(databasesService.findByProjectEnv(projectId,environment));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    } 
  } // End of dbByProjEnv.
} // End of DatabaseController.