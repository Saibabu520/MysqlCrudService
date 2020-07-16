package com.cds.regression.controller;

import com.cds.regression.commons.Response;
import com.cds.regression.exceptions.ExceptionProcessor;
import com.cds.regression.model.Projects;
import com.cds.regression.services.ProjectsService;

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
import org.springframework.web.bind.annotation.RestController;

@Log4j2
@RestController
@RequestMapping("/projects")
public class ProjectsController {
  
  @Autowired
  private ProjectsService projectsService;
  
  @Autowired
  private ExceptionProcessor exceptionProcessor;
  
  /**
   * Method which will fetch all projects from database by calling 
   * appropriate method of projectsService.
   * 
   * @return- List of projects.
  */
  @Async
  @GetMapping(value = "/all")
  public CompletableFuture<Response> getAllProjects() {
    
    try {
      return CompletableFuture.completedFuture(projectsService.findAll());
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of getAllProjects.
  
  /**
   * Method which will save or update project in database by calling 
   * appropriate method of projectsService.
   * 
   * @param- project data as request.
   * @return- Message
  */
  @Async
  @PostMapping(value = "/save")
  public CompletableFuture<Response> saveProject(@RequestBody Projects project) {
   
    try {
      return CompletableFuture.completedFuture(projectsService.saveProject(project));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of saveProject.
  
  /**
   * Method which will save or update project in database by calling 
   * appropriate method of projectsService.
   * 
   * @param- project data as request.
   * @return- success or failure message.
  */
  @Async
  @PutMapping(value = "/update")
  public CompletableFuture<Response> updateProject(@RequestBody Projects project) {
   
    try {
      return CompletableFuture.completedFuture(projectsService.updateProject(project));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of updateProject.
  
  /**
   * Method which will delete project data from database based on given project id 
   * by calling appropriate method of projectsService.
   * 
   * @param- project id.
   * @return- success or failure message.
  */
  @Async
  @DeleteMapping(value = "/delete/{id}")
  public CompletableFuture<Response> deleteProject(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(projectsService.deleteProject(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of deleteProject.

  /**
   * Method which will fetch project data from database based on given project id 
   * by calling appropriate method of projectsService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/id/{id}")
  public CompletableFuture<Response> projectById(@PathVariable String id) {
    
    try {
      return CompletableFuture.completedFuture(projectsService.findById(id));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of projectById.
  
  /**
   * Method which will fetch project data from database based on given project name 
   * by calling appropriate method of projectsService.
   * 
   * @param- project id.
   * @return- Projects pojo
  */
  @Async
  @GetMapping(value = "/name/{projectName}")
  public CompletableFuture<Response> projectByName(@PathVariable String projectName) {
    
    try {
      return CompletableFuture.completedFuture(projectsService.findByProjectName(projectName));
    } catch (Exception e) {
      log.info("Exception in controller : {}", e.getMessage());
      return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
    }
  } // End of projectByName.
} // End of ProjectsController.