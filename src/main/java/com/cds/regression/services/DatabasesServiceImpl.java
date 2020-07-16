package com.cds.regression.services;

import java.io.IOException;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.commons.UtilFunctions;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Databases;
import com.cds.regression.model.Environments;
import com.cds.regression.model.Projects;
import com.cds.regression.repo.DatabasesRepo;
import com.cds.regression.repo.EnvironmentsRepo;

@Service
public class DatabasesServiceImpl implements DatabasesService {
  
  @Autowired
  private DatabasesRepo databasesRepo;

  @Autowired
  private ResponseCreator responseCreator;
  
  @Autowired
  private UtilFunctions utilFunctions;
  
  @Autowired
  private EnvironmentsRepo environmentsRepo;
  
  /**
   * Method which will save database by calling appropriate
   * method of databasesRepo.
   * 
   * @param- database data as request.
   * @return- success or failure Response.
  */
  @Override
  public Response saveDatabase(Databases database) {
    
    try {
      Environments env = fetchEnvId(database.getProjectName(),database.getEnvironment());
      if (env == null) {
        throw new DataException("Given environment".concat(ConstantsUtil.NOT_FOUND));
      }
      database.setProjectId(env.getProject().getId());
      database.setEnvs(env);
      database.setCreatedDate(UtilFunctions.getCurrentDate());
      
      Optional<String> databaseId = Optional.ofNullable(database.getId());
      if (!databaseId.isPresent()) {
        database.setId(UUID.randomUUID().toString());
      }
      databasesRepo.save(database);
    } catch (Exception e) {
      throw new DataException(e.getMessage());
    }
    
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveDatabase.

  /**
   * Method which will fetch environment id from db based on given project name 
   * and environment type.
   * 
   * @param- projectName,envType.
   * @return- envId.
   */
  private Environments fetchEnvId(String projectName,String envType) {

    for (Environments env : environmentsRepo.findByProjectName(projectName)) {
      if (env.getProjectName().equals(projectName) && env.getEnvironment().equals(envType)) {
        return env;
      }
    }
    return null;
  } // End of fetchEnvId.

  /**
   * Method which will update database data in database.
   * 
   * @param- database data as request.
   * @return- success or failure Response.
  */
  @Override
  public Response updateDatabase(Databases database) {
    
    findById(database.getId());
    
    if (database.getProjectName() != null || database.getEnvironment() != null) {
      Environments env = fetchEnvId(database.getProjectName(),database.getEnvironment());
      if (env == null) {
        throw new DataException("Given environment".concat(ConstantsUtil.NOT_FOUND));
      } else {
        database.setEnvs(env);
        database.setProjectId(env.getProject().getId());
      }
    }
    
    databasesRepo.save(database);
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of updateDatabase.

  /**
   * Method which will delete database data from database based on given database id 
   * by calling appropriate method of databasesRepo.
   * 
   * @param- database id.
   * @return- success or failure Response.
  */
  @Override
  public Response deleteDatabase(String id) {
    
    Optional<Databases> response = databasesRepo.findById(id);
    if (response.isPresent()) {
      databasesRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteDatabase.

  /**
   * Method which will fetch all databases by calling databasesRepo.
   * 
   * @return- success or failure Response.
  */
  @Override
  public Response findAll() {
    
    List<Databases> databases = databasesRepo.findAll();
    
    if (databases.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(databases);
  } // End of findAll.

  /**
   * Method which will fetch db data from database based on given db id 
   * by calling appropriate method of databasesRepo.
   * 
   * @param- database id.
   * @return- success or failure Response.
  */
  @Override
  public Response findById(String id) {
    
    Optional<Databases> db = databasesRepo.findById(id);
    if (!db.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(db.get());
  } // End of findById.
  
  /**
   * Method which will fetch databases data from database based on given project name 
   * by calling appropriate method of databasesRepo.
   * 
   * @param- projectName.
   * @return- success or failure Response.
  */
  @Override
  public Response findByProjectId(String projectId) {

    Optional<List<Databases>> databases;
    try {
      Projects projData = utilFunctions.fetchProjectData(projectId);
      databases = databasesRepo.findByProjectId(projData.getId());
      if (!databases.isPresent()) {
        throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
      }
    } catch (Exception e) {
      throw new DataException(e.getMessage());
    }
    return responseCreator.prepareGetCallResponse(databases.get());
  } // End of findByProjectName.

  /**
   * Method which will fetch databases data from database based on given project name 
   * and env type by calling appropriate method of databasesRepo.
   * @throws IOException 
   * 
   * @param- projectName, environment.
   * @return- success or failure Response.
  */
  @Override
  public Response findByProjectEnv(String projectId,String env) throws IOException {
    
    utilFunctions.fetchProjectData(projectId);
    Optional<List<Databases>> db = databasesRepo.findByProjEnv(projectId,env);
    if (!db.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(db);
  } // End of findByProjectEnv.
} // End of DatabasesServiceImpl.