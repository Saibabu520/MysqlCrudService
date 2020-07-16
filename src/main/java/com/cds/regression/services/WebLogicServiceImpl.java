package com.cds.regression.services;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.commons.UtilFunctions;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Projects;
import com.cds.regression.model.Weblogic;
import com.cds.regression.repo.WeblogicRepo;

@Service
public class WebLogicServiceImpl implements WebLogicService {

  @Autowired
  private WeblogicRepo weblogicRepo;

  @Autowired
  private ResponseCreator responseCreator;
  
  @Autowired
  private UtilFunctions utilFunctions;

  @Override
  public Response saveWebLogic(Weblogic weblogic) {
    
    try {
      Projects proj = utilFunctions.fetchProjectData(weblogic.getProjectName());
      Optional<String> weblogicId = Optional.ofNullable(weblogic.getId());
      if (!weblogicId.isPresent()) {
        weblogic.setId(UUID.randomUUID().toString());
      }
      weblogicRepo.save(weblogic);
      
    } catch (DataException e) {
      throw new DataException(e.getMessage());
    } catch (Exception e) {
      throw new DataException(ConstantsUtil.INTERNAL_ERROR);
    }

    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveweblogic.

  @Override
  public Response findAll() {
    List<Weblogic> weblogic = weblogicRepo.findAll();

    if (weblogic.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(weblogic);
  } // End of findAll.

  @Override
  public Response findById(String id) {
    Optional<Weblogic> weblogic = weblogicRepo.findById(id);
    if (!weblogic.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(weblogic.get());
  } // End of findById.

  @Override
  public Response findByProjectName(String projectName) {
    Optional<List<Weblogic>> weblogic = weblogicRepo.findByProjectName(projectName);
    if (!weblogic.isPresent() || weblogic.get().isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(weblogic.get());
  } // End of findByProjectName.

  @Override
  public Response updateWebLogic(Weblogic weblogic) {
    
    findById(weblogic.getId());

    try {
      Map<String, String> fields = new HashMap<>();
      fields.put("projectName", weblogic.getProjectName());
      fields.put("Environment", weblogic.getEnvironment());
      fields.put("ModuleName", weblogic.getModuleName());
      fields.put("MessageBroker", weblogic.getMessageBroker());
      fields.put("Ip", weblogic.getIp());
      fields.put("Port", weblogic.getPort());
      fields.put("ConnectionFactory", weblogic.getConnectionFactory());
      fields.put("serverName", weblogic.getServerName());
      fields.put("serverModule", weblogic.getServerModule());
      fields.put("clientId", weblogic.getClientId());
      fields.put("subscriber", weblogic.getSubscriber());

      fields.values().removeAll(Collections.singleton(null));

      /*Update updateFields = new Update();
      for (Map.Entry<String, String> entry : fields.entrySet()) {
        updateFields.set(entry.getKey(), entry.getValue());
        
        if (entry.getKey().equals("projectName")) {
          Projects projData = utilFunctions.fetchProjectData(weblogic.getProjectName());
          updateFields.set("projectId", projData.getId());
        }
      }
      Query query = new Query(Criteria.where("_id").is(weblogic.getId()));
      mongoTemplate.updateFirst(query, updateFields, Weblogic.class);*/
    } catch (Exception e) {
      throw new DataException(e.getMessage());
    }

    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  }

  @Override
  public Response deleteWeblogicById(String id) {
    Optional<Weblogic> response = weblogicRepo.findById(id);
    if (response.isPresent()) {
      weblogicRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteWeblogicById.

  @Override
  public Response findByProjectId(String projectId) {
    Optional<Weblogic> response = weblogicRepo.findByProjectId(projectId);
    if (!response.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(response.get());
  } // End of findByProjectId.

  @Override
  public Response findByModuleName(String moduleName) {
    
    Optional<List<Weblogic>> weblogics = weblogicRepo.findByModuleName(moduleName);
    if (!weblogics.isPresent() || weblogics.get().isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(weblogics.get());
  } // End of findByModuleName.
  
  @Override
  public Response findByProjModule(String projName, String moduleName) {
    
    /*Optional<Weblogic> weblogic = weblogicRepo.findByProjModule(projName,moduleName);
    if (!weblogic.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }*/
    return responseCreator.prepareGetCallResponse(null);
    
  } // End of findByProjModule.
} // End of WebLogicServiceImpl.