package com.cds.regression.services;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Projects;
import com.cds.regression.repo.ProjectsRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProjectsServiceImpl implements ProjectsService {

  @Autowired
  private ProjectsRepo projectsRepo;
  
  @Autowired
  private ResponseCreator responseCreator;

  /**
   * Method which will save project in database by calling 
   * appropriate method of projectsRepo.
   * 
   * @param- project data as request.
   * @return- success or failure Response.
  */
  @Override
  public Response saveProject(Projects project) {
    
    Optional<Projects> response = projectsRepo.findByProjectName(project.getProjectName());
    if (!response.isPresent()) {
      Optional<String> projectId = Optional.ofNullable(project.getId());
      if (!projectId.isPresent()) {
        project.setId(UUID.randomUUID().toString());
      }
      projectsRepo.save(project);
    } else {
      throw new DataException(project.getProjectName().concat(ConstantsUtil.DATA_ALREADY_EXIST));
    }
 
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveProject.

  /**
   * Method which will delete project data from database based on given project id 
   * by calling appropriate method of projectsRepo.
   * 
   * @param- project id.
   * @return- success or failure Response.
  */
  @Override
  public Response deleteProject(String id) {
    
    Optional<Projects> response = projectsRepo.findById(id);
    if (response.isPresent()) {
      projectsRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteProject.

  /**
   * Method which will update project in database.
   * 
   * @param- project data as request.
   * @return- success or failure Response.
  */
  @Override
  @Transactional
  public Response updateProject(Projects project) {

    findById(project.getId());
    
    projectsRepo.save(project);
    
    if (project.getProjectName() != null) {
      System.err.println(project.getProjectName() +" "+project.getId() );
      projectsRepo.updateProjectInEnv(project.getProjectName(), project.getId());
    }

    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of updateProject.
  
  /**
   * Method which will fetch all projects from database by calling projectsRepo.
   * 
   * @return- success or failure Response.
  */
  @Override
  public Response findAll() {
    
    List<Projects> projects = projectsRepo.findAll();
    
    if (projects.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(projects);
  } // End of findAll.

  /**
   * Method which will fetch project data from database based on given project id 
   * by calling appropriate method of projectsRepo.
   * 
   * @param- project id.
   * @return- success or failure Response.
  */
  @Override
  public Response findById(String id) {
    
    Optional<Projects> projects = projectsRepo.findById(id);
    if (!projects.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(projects.get());
  } // End of findById.

  /**
   * Method which will fetch project data from database based on given project name 
   * by calling appropriate method of projectsRepo.
   * 
   * @param- projectName.
   * @return- success or failure Response.
  */
  @Override
  public Response findByProjectName(String projectName) {
    
    Optional<Projects> projects = projectsRepo.findByProjectName(projectName);
    if (!projects.isPresent()) {
      throw new DataException(projectName.concat(ConstantsUtil.NOT_FOUND));
    }
    return responseCreator.prepareGetCallResponse(projects.get());
  } // End of findByProjectName.
} // End of ProjectsServiceImpl.