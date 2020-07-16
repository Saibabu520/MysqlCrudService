package com.cds.regression.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Environments;
import com.cds.regression.model.Projects;
import com.cds.regression.repo.EnvironmentsRepo;
import com.cds.regression.repo.ProjectsRepo;

@Service
public class EnvironmentsServiceImpl implements EnvironmentsService {

  @Autowired
  private EnvironmentsRepo environmentsRepo;
  
  @Autowired
  private ResponseCreator responseCreator;
  
  @Autowired
  private ProjectsRepo projectsRepo;
  
  /**
   * Method which will save environment in database by calling 
   * appropriate method of environmentsRepo.
   * 
   * @param- environment data as request.
   * @return- success or failure Response.
  */
  @Override
  public Response saveEnvironment(Environments env) {

    try {
      Optional<Projects> project = projectsRepo.findByProjectName(env.getProjectName());

      if (!project.isPresent()) {
        throw new DataException(env.getProjectName().concat(ConstantsUtil.NOT_FOUND));
      }
      env.setProject(project.get());
      Optional<String> envId = Optional.ofNullable(env.getId());
      if (!envId.isPresent()) {
        env.setId(UUID.randomUUID().toString());
      }
      environmentsRepo.save(env);
    } catch (DataException e) {
      throw new DataException(e.getMessage());
    } catch (Exception e) {
      throw new DataException(ConstantsUtil.INTERNAL_ERROR);
    }
    
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveEnvironment.

  /**
   * Method which will update environment in database.
   * @throws JSONException,IOException
   * 
   * @param- project data as request.
   * @return- success or failure Response.
  */
  @Override
  @Transactional
  public Response updateEnvironment(Environments env) throws IOException {
    
    findById(env.getId());
    
    if (env.getProjectName() != null) {
      Optional<Projects> project = projectsRepo.findByProjectName(env.getProjectName());
      
      if (!project.isPresent()) {
        throw new DataException(env.getProjectName().concat(ConstantsUtil.NOT_FOUND));
      }
      env.setProject(project.get());
    }

    environmentsRepo.save(env);
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of updateEnvironment.

  /**
   * Method which will delete environment data from database based on given environment id 
   * by calling appropriate method of environmentsRepo.
   * 
   * @param- environment id.
   * @return- success or failure Response.
  */
  @Override
  public Response deleteEnvironmentById(String id) {
    
    Optional<Environments> response = environmentsRepo.findById(id);
    if (response.isPresent()) {
      environmentsRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteEnvironmentById.

  /**
   * Method which will delete environment data from database based on given environment ip 
   * by calling appropriate method of environmentsRepo.
   * 
   * @param- environment ip.
   * @return- success or failure Response.
  */
  @Override
  public Response deleteEnvironmentByIp(String ip) {
    
    Optional<Environments> response = environmentsRepo.findByIp(ip);
    if (response.isPresent()) {
      environmentsRepo.deleteByIp(ip);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteEnvironmentByIp.
  
  /**
   * Method which will fetch all environments from database by calling environmentsRepo.
   * 
   * @return- success or failure Response.
  */
  @Override
  public Response findAll() {
    
    List<Environments> envs = environmentsRepo.findAll();
    
    if (envs.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(envs);
  } // End of findAll.

  /**
   * Method which will fetch environments from database based on given environment id 
   * by calling appropriate method of environmentsRepo.
   * 
   * @param- environment id.
   * @return- success or failure Response.
  */
  @Override
  public Response findById(String id) {
    
    Optional<Environments> env = environmentsRepo.findById(id);
    if (!env.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(env.get());
  } // End of findById.

  /**
   * Method which will fetch environments data from database based on given project name 
   * by calling appropriate method of environmentsRepo.
   * 
   * @param- projectName.
   * @return- success or failure Response.
  */
  @Override
  public Response findByProjectName(String projectName) {

    Optional<List<Environments>> environments;
    try {
      
      Optional<Projects> project = projectsRepo.findByProjectName(projectName);
      
      if (!project.isPresent()) {
        throw new DataException(projectName.concat(ConstantsUtil.NOT_FOUND));
      }
      environments = environmentsRepo.findByProjectId(project.get().getId());
      if (environments.get().isEmpty()) {
        throw new DataException(projectName.concat(ConstantsUtil.NOT_FOUND));
      }
    } catch (Exception e) {
      throw new DataException(e.getMessage());
    }
    return responseCreator.prepareGetCallResponse(environments);
  } // End of findByProjectName.
} // End of EnvironmentsServiceImpl.