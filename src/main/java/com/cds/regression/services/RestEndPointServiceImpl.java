package com.cds.regression.services;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.commons.ResponseCreator;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Environments;
import com.cds.regression.model.Modules;
import com.cds.regression.model.RestEndPoints;
import com.cds.regression.repo.EnvironmentsRepo;
import com.cds.regression.repo.ModuleRepo;
import com.cds.regression.repo.RestEndPointRepo;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RestEndPointServiceImpl implements RestEndPointService {

  @Autowired
  private ModuleRepo moduleRepo;
  @Autowired
  private EnvironmentsRepo environmentsRepo;

  @Autowired
  private RestEndPointRepo endPointRepo;

  @Autowired
  private ResponseCreator responseCreator;

  /**
   * Method which will save rest endpoint in database by calling appropriate method of endpoinRepo.
   * 
   * @param- restEndPoints data as request.
   * @return- success or failure Response.
   */
  @Override
  public Response saveRestEndPoints(RestEndPoints restEndPoints) {
    try {
      Optional<Modules> modules = moduleRepo.findById(restEndPoints.getModules().getId());

      Optional<Environments> environments = environmentsRepo
          .findById(restEndPoints.getEnvrnts().getId());

      if (!modules.isPresent()) {
        throw new DataException(restEndPoints.getModules().getId().concat(ConstantsUtil.NOT_FOUND));
      }
      if (!environments.isPresent()) {
        throw new DataException(restEndPoints.getEnvrnts().getId().concat(ConstantsUtil.NOT_FOUND));
      }
      Optional<String> endpointId = Optional.ofNullable(restEndPoints.getId());
      if (!endpointId.isPresent()) {
        restEndPoints.setId(UUID.randomUUID().toString());
      }

      restEndPoints.setEnvrnts(environments.get());
      restEndPoints.setModules(modules.get());

      endPointRepo.save(restEndPoints);
    } catch (DataException e) {
      throw new DataException(e.getMessage());
    } catch (Exception e) {
      throw new DataException(ConstantsUtil.INTERNAL_ERROR);
    }

    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of saveRestEndPoints.

  /**
   * Method which will update restEndpoint in database.
   * 
   * @throws JSONException,IOException
   * 
   * @param- restEndpoint data as request.
   * @return- success or failure Response.
   */
  @Override
  public Response updateRestEndPoints(RestEndPoints restEndPoints) {

    try {
      findById(restEndPoints.getId());

      if (!restEndPoints.getEnvrnts().getId().isEmpty()) {
        Optional<Environments> env = environmentsRepo.findById(restEndPoints.getEnvrnts().getId());

        if (env == null) {
          throw new DataException("Given environment".concat(ConstantsUtil.NOT_FOUND));
        } else {
          restEndPoints.setEnvrnts(env.get());
        }
      }

      if (!restEndPoints.getModules().getId().isEmpty()) {
        Optional<Modules> module = moduleRepo.findById(restEndPoints.getModules().getId());

        if (module == null) {
          throw new DataException("Given module ".concat(ConstantsUtil.NOT_FOUND));
        } else {
          restEndPoints.setModules(module.get());
        }

      }

      endPointRepo.save(restEndPoints);

    } catch (DataException e) {
      throw new DataException(e.getMessage());
    } catch (Exception e) {
      throw new DataException(ConstantsUtil.INTERNAL_ERROR);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);

  } // End of updateRestEndPoints.

  /**
   * Method which will fetch all restEndPoint from database by calling restEndPointRepo.
   * 
   * @return- success or failure Response.
   */
  @Override
  public Response findAll() {

    List<RestEndPoints> endPoints = endPointRepo.findAll();

    if (endPoints.isEmpty()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(endPoints);
  } // End of findAll.

  /**
   * Method which will fetch restEndPoint from database based on given restEndPoint id by calling
   * appropriate method of restEndPointRepo.
   * 
   * @param- restEndPoint id.
   * @return- success or failure Response.
   */
  @Override
  public Response findById(String id) {

    Optional<RestEndPoints> endPoint = endPointRepo.findById(id);
    if (!endPoint.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareGetCallResponse(endPoint.get());
  } // End of findById.

  /**
   * Method which will fetch restEndPoint from database based on given envId id by calling
   * appropriate method of restEndPoint.
   *
   * @param -envId
   * @return- success or failure Response.
   */
  @Override
  public Response findByEnvId(String envId) {

    Optional<List<RestEndPoints>> endPoints = endPointRepo.findByEnvironmentId(envId);
    if (!endPoints.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }

    return responseCreator.prepareGetCallResponse(endPoints);

  } // End of findByEnvId.

  /**
   * Method which will fetch restEndPoint from database based on given moduleId id by calling
   * appropriate method of restEndPoint.
   *
   * @param -
   *          moduleId
   * @return- success or failure Response.
   */
  @Override
  public Response findByModuleId(String moduleId) {

    Optional<List<RestEndPoints>> endPoints = endPointRepo.findByModuleId(moduleId);
    if (!endPoints.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }

    return responseCreator.prepareGetCallResponse(endPoints);

  } // End of findByModuleId.

  /**
   * Method which will delete restEndPoint data from database based on given restEndpoint id by
   * calling appropriate method of restEndpointRepo.
   * 
   * @param- restEndpoint id.
   * @return- success or failure Response.
   */
  @Override
  public Response deleteById(String id) {

    Optional<RestEndPoints> response = endPointRepo.findById(id);
    if (response.isPresent()) {
      endPointRepo.deleteById(id);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteById.

  /**
   * Method which will delete restEndPoint data from database based on given env id by calling
   * appropriate method of restEndPointRepo.
   *
   * @param- envId.
   * @return- success or failure Response.
   */

  @Transactional
  @Override
  public Response deleteByEnvId(String envId) {

    Optional<List<RestEndPoints>> endPoint = endPointRepo.findByEnvironmentId(envId);
    if (endPoint.isPresent()) {
      endPointRepo.deleteByEnvironmentId(envId);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteByEnvId.

  /**
   * Method which will delete restEndPoint data from database based on given module id by calling
   * appropriate method of restEndPointRepo.
   *
   * @param- moduleId.
   * @return- success or failure Response.
   */
  @Transactional
  @Override
  public Response deleteByModuleId(String moduleId) {

    Optional<List<RestEndPoints>> endPoint = endPointRepo.findByModuleId(moduleId);
    if (endPoint.isPresent()) {
      endPointRepo.deleteByModuleId(moduleId);
    } else {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }
    return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
  } // End of deleteByModuleId.

  /**
   * Method which will fetch restEndPoint from database based on given moduleId id and envId by
   * calling appropriate method of restEndPoint.
   *
   * @param - moduleId an envId.
   * @return- success or failure Response.
   */
  @Override
  public Response findByModuleIdEnvId(String moduleId, String envId) {

    Optional<List<RestEndPoints>> endPoints = endPointRepo.findByModuleIdEnvId(moduleId, envId);
    if (!endPoints.isPresent()) {
      throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
    }

    return responseCreator.prepareGetCallResponse(endPoints);

  } // End of findByModuleIdEnvId.

} // End of RestEndPointServiceImpl.