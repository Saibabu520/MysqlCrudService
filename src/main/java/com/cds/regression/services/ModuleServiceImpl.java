package com.cds.regression.services;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Modules;
import com.cds.regression.model.Projects;
import com.cds.regression.repo.ModuleRepo;
import com.cds.regression.repo.ProjectsRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ModuleServiceImpl implements ModuleService {

  @Autowired
  private ModuleRepo moduleRepo;
  @Autowired
  private ProjectsRepo projectsRepo;

  @Autowired
  private ResponseCreator responseCreator;

  /**
   * Method which will save modules in database by calling appropriate method of moduleRepo.
   * 
   * @param- modules data as request.
   * @return- success or failure Response.
   */
  @Override
  public Response saveModules(Modules modules) {
    try {
      Optional<Projects> project = projectsRepo.findById(modules.getProjects().getId());

      if (!project.isPresent()) {
        throw new DataException(modules.getProjects().getId().concat(ConstantsUtil.NOT_FOUND));
      }
      modules.setProjects(project.get());
      Optional<String> moduleId = Optional.ofNullable(modules.getId());
      if (!moduleId.isPresent()) {
        modules.setId(UUID.randomUUID().toString());
      }
      moduleRepo.save(modules);
    } catch (DataException e) {
      throw new DataException(e.getMessage());
    } catch (Exception e) {
      throw new DataException(ConstantsUtil.INTERNAL_ERROR);
    }

    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveModules.

  /**
   * Method which will update module in database.
   * 
   * @throws JSONException,IOException
   * 
   * @param- module data as request.
   * @return- success or failure Response.
   */
  @Override
  public Response updateModule(Modules module) {

    findById(module.getId());

    if (module.getProjects().getId() != null) {
      Optional<Projects> project = projectsRepo.findById(module.getProjects().getId());

      if (!project.isPresent()) {
        throw new DataException(
            module.getProjects().getProjectName().concat(ConstantsUtil.NOT_FOUND));
      }
      module.setProjects(project.get());
    }

    moduleRepo.save(module);
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);

  } // End of updateModule

  /**
   * Method which will delete module data from database based on given module id by calling
   * appropriate method of moduleRepo.
   * 
   * @param- module id.
   * @return- success or failure Response.
   */
  @Override
  public Response deleteModule(String id) {

    Optional<Modules> response = moduleRepo.findById(id);
    if (response.isPresent()) {
      moduleRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteModule.

  /**
   * Method which will fetch modules from database based on given project id by calling appropriate
   * method of moduleRepo.
   * 
   * @param -
   *          projectId
   * @return- success or failure Response.
   */
  @Override
  public Response findByProjectId(String projectId) {

    Optional<List<Modules>> modules = moduleRepo.findByProjectId(projectId);
    if (!modules.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }

    return responseCreator.prepareGetCallResponse(modules);

  } // End of findByProjectId.

  /**
   * Method which will fetch all modules from database by calling moduleRepo.
   * 
   * @return- success or failure Response.
   */
  @Override
  public Response findAll() {

    List<Modules> modules = moduleRepo.findAll();

    if (modules.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(modules);
  } // End of findAll.

  /**
   * Method which will fetch modules from database based on given module id by calling appropriate
   * method of moduleRepo.
   * 
   * @param- module id.
   * @return- success or failure Response.
   */
  @Override
  public Response findById(String id) {

    Optional<Modules> module = moduleRepo.findById(id);
    if (!module.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(module.get());
  } // End of findById.

  /**
   * Method which will delete module data from database based on given project id by calling
   * appropriate method of moduleRepo.
   * 
   * @param- projectId.
   * @return- success or failure Response.
   */
  @Transactional
  @Override
  public Response deleteByProjectId(String projectId) {

    Optional<List<Modules>> modules = moduleRepo.findByProjectId(projectId);
    if (modules.isPresent()) {
      // moduleRepo.deleteAll(modules.get());
      moduleRepo.deleteModuleByProjectId(projectId);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteByProjectId.

} // End of ModuleServiceImpl.