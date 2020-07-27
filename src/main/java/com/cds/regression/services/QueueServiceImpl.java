package com.cds.regression.services;

import com.cds.regression.commons.ConstantsUtil;
import com.cds.regression.commons.Response;
import com.cds.regression.exceptions.DataException;
import com.cds.regression.model.Projects;
import com.cds.regression.model.Queue;
import com.cds.regression.repo.QueueRepo;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class QueueServiceImpl implements QueueService {

	@Autowired
	private QueueRepo queueRepo;
	@Autowired
	private com.cds.regression.commons.ResponseCreator responseCreator;

	@Autowired
	private ProjectsService projectsService;

	/**
	 * Method which will save queue in database by calling appropriate method of
	 * queueRepo.
	 * 
	 * @throws- JSONException
	 * @param- environment data as request.
	 * @return- success or failure Response.
	 */

	@Override
	public Response saveQueue(Queue queue) {

		try {
			Projects proj = (Projects) projectsService.findByProjectName(queue.getProjectName()).getData();

			// System.out.println("prj data" + proj);

			queue.setProjectId(proj.getId());

			Optional<String> queueId = Optional.ofNullable(queue.getId());
			if (!queueId.isPresent()) {
				queue.setId(UUID.randomUUID().toString());
			}
			queueRepo.save(queue);
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}

		return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
	} // End of saveQueue.

	/**
	 * Method which will update queue in database.
	 * 
	 * @throws JSONException
	 * 
	 * @param- queue data as request.
	 * @return- success or failure Response.
	 */
	@Override
	public Response updateQueue(Queue queue) {

		findById(queue.getId());

		try {

			queueRepo.save(queue);

		} catch (DataException e) {
			throw new DataException(e.getMessage());
		} catch (Exception e) {
			throw new DataException(ConstantsUtil.INTERNAL_ERROR);
		}

		return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
	} // End of updateEnvironment.

	/**
	 * Method which will delete queue data from database based on given queue id by
	 * calling appropriate method of queueRepo.
	 * 
	 * @param- environment id.
	 * @return- success or failure Response.
	 */
	@Override
	public Response deleteQueueById(String id) {

		Optional<Queue> response = queueRepo.findById(id);
		if (response.isPresent()) {
			queueRepo.deleteById(id);
		} else {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareUpdateResponse(ConstantsUtil.SUCCESS_MSG);
	} // End of deleteEnvironmentById.

	/**
	 * Method which will fetch all queue from database by calling queueRepo.
	 * 
	 * @return- success or failure Response.
	 */
	@Override
	public Response findAll() {

		List<Queue> queues = queueRepo.findAll();

		if (queues.isEmpty()) {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareGetCallResponse(queues);
	} // End of findAll.

	/**
	 * Method which will fetch queues from database based on given queue id by
	 * calling appropriate method of queueRepo.
	 * 
	 * @param- queue id.
	 * @return- success or failure Response.
	 */
	@Override
	public Response findById(String id) {

		Optional<Queue> queue = queueRepo.findById(id);
		if (!queue.isPresent()) {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // End of findById.

	/**
	 * Method which will fetch queue data from database based on given project name
	 * by calling appropriate method of queueRepo.
	 * 
	 * @throws- JSONException
	 * @param- projectName.
	 * @return- success or failure Response.
	 */
	@Override
	public Response findByProjectName(String projectName) {

		Optional<List<Queue>> queue;
		try {
			Projects projData = (Projects) projectsService.findByProjectName(projectName).getData();

			queue = queueRepo.findByProjectId(projData.getId());
			if (!queue.isPresent()) {
				throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // End of findByProjectName.

	/**
	 * Method which will fetch queue data from database based on given environment
	 * name by calling appropriate method of queueRepo.
	 * 
	 * @throws- JSONException
	 * @param- environmentName.
	 * @return- success or failure Response.
	 */

	@Override
	public Response findByEnvName(String envName) {

		Optional<List<Queue>> queue;
		try {
			queue = queueRepo.findByEnvironment(envName);
			if (queue.get().size() <= 0) {
				throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
			}
		} catch (Exception e) {
			throw new DataException(e.getMessage());
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // End of findByProjectName.

	/**
	 * Method which will fetch queues from database based on given ip address by
	 * calling appropriate method of queueRepo.
	 * 
	 * @param- ip address.
	 * @return- success or failure Response.
	 */
	@Override
	public Response findByIp(String ip) {

		Optional<List<Queue>> queue = queueRepo.findByIp(ip);
		if (!queue.isPresent() || queue.get().isEmpty()) {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // End of findByIp.

	/**
	 * Method which will fetch queues from database based on given moduleName by
	 * calling appropriate method of queueRepo.
	 * 
	 * @param- moduleName.
	 * @return- success or failure Response.
	 */
	@Override
	public Response findByModuleName(String moduleName) {

		Optional<List<Queue>> queue = queueRepo.findByModuleName(moduleName);
		if (!queue.isPresent() || queue.get().isEmpty()) {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // End of findByModuleName.

	/**
	 * Method which will fetch queues from database based on given moduleName and
	 * projectname by calling appropriate method of queueRepo.
	 * 
	 * @param- moduleName and projectName.
	 * @return- success or failure Response.
	 */
	@Override
	public Response findByModuleNamePrjName(String moduleName, String projectName) {

		Optional<Queue> queue = queueRepo.findByModuleProj(projectName, moduleName);
		if (!queue.isPresent()) {
			throw new DataException(ConstantsUtil.DATA_NOT_FOUND);
		}
		return responseCreator.prepareGetCallResponse(queue.get());
	} // end of findByModuleNamePrjName.
} // End of QueueServiceImpl.