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
import com.cds.regression.model.Queue;
import com.cds.regression.services.QueueService;

@Log4j2
@RestController
@RequestMapping("/queue")
public class QueueController {

	@Autowired
	private QueueService queueService;

	@Autowired
	private ExceptionProcessor exceptionProcessor;

	/**
	 * Method which will fetch all queue from database by calling appropriate method
	 * of queueService.
	 * 
	 * @return- List of queue.
	 */
	@Async
	@GetMapping(value = "/all")
	public CompletableFuture<Response> getAllQueue() {

		try {
			return CompletableFuture.completedFuture(queueService.findAll());
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End of getAllEnvs.

	/**
	 * Method which will save or update queue in database by calling appropriate
	 * method of queueService.
	 * 
	 * @param- queue data as request.
	 * @return- Message
	 */
	@Async
	@PostMapping(value = "/save")
	public CompletableFuture<Response> saveQueue(@RequestBody Queue queue) {

		try {
			return CompletableFuture.completedFuture(queueService.saveQueue(queue));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End of saveQueue.

	/**
	 * Method which will save or update queue in database by calling appropriate
	 * method of queueService.
	 * 
	 * @param- queue data as request.
	 * @return- success or failure message.
	 */
	@Async
	@PutMapping(value = "/update")
	public CompletableFuture<Response> updateQueue(@RequestBody Queue queue) {

		try {
			return CompletableFuture.completedFuture(queueService.updateQueue(queue));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End of updateQueue.

	/**
	 * Method which will delete queue data from database based on given environment
	 * id by calling appropriate method of queueService.
	 * 
	 * @param- queue id.
	 * @return- success or failure message.
	 */
	@Async
	@DeleteMapping(value = "/delete/id/{id}")
	public CompletableFuture<Response> deleteQueueById(@PathVariable String id) {

		try {
			return CompletableFuture.completedFuture(queueService.deleteQueueById(id));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End of deleteQueueById.

	/**
	 * Method which will fetch Queue data from database based on given Queue id by
	 * calling appropriate method of queueService.
	 * 
	 * @param- Queue id.
	 * @return- Queue pojo
	 */
	@Async
	@GetMapping(value = "/id/{id}")
	public CompletableFuture<Response> queueById(@PathVariable String id) {

		try {
			return CompletableFuture.completedFuture(queueService.findById(id));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End ofqueueById.

	/**
	 * Method which will fetch queue data from database based on given project name
	 * by calling appropriate method of queueService.
	 * 
	 * @param- project name.
	 * @return- queue pojo
	 */
	@Async
	@GetMapping(value = "/prjname/{projectName}")
	public CompletableFuture<Response> queueByProjName(@PathVariable String projectName) {

		try {
			return CompletableFuture.completedFuture(queueService.findByProjectName(projectName));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		} // End of queueByProjName.
	} // End of projectByName.

	/**
	 * Method which will fetch queue data from database based on given environment
	 * name by calling appropriate method of queueService.
	 * 
	 * @param- environment name.
	 * @return- queue pojo
	 */
	@Async
	@GetMapping(value = "/envname/{envName}")
	public CompletableFuture<Response> queueByEnvName(@PathVariable String envName) {

		try {
			return CompletableFuture.completedFuture(queueService.findByEnvName(envName));
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return CompletableFuture.completedFuture(exceptionProcessor.processException(e));
		}
	} // End of queueByEnvName.

	/**
	 * Method which will fetch queue data from database based on given ip address by
	 * calling appropriate method of queueService.
	 * 
	 * @param-ip address.
	 * @return- queue pojo
	 */
	@GetMapping(value = "/ip/{ip}")
	public Response queueByIp(@PathVariable String ip) {

		try {
			return queueService.findByIp(ip);
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return exceptionProcessor.processException(e);
		}
	} // End of queueByIp.

	/**
	 * Method which will fetch queue data from database based on given moduleName by
	 * calling appropriate method of queueService.
	 * 
	 * @param-moduleName. @return- queue pojo
	 */
	@GetMapping(value = "/module/{moduleName}")
	public Response queueByModuleName(@PathVariable String moduleName) {

		try {
			return queueService.findByModuleName(moduleName);
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return exceptionProcessor.processException(e);
		}
	} // End of queueByModuleName.

	/**
	 * Method which will fetch queue data from database based on given moduleName
	 * and projectname by calling appropriate method of queueService.
	 * 
	 * @param-moduleName and projectName.
	 * @return- queue pojo.
	 */
	@GetMapping(value = "/prjname/moduleName")
	public Response queueByModuleNamePrjName(@RequestParam String projectName, @RequestParam String moduleName) {

		try {
			return queueService.findByModuleNamePrjName(moduleName, projectName);
		} catch (Exception e) {
			log.info("Exception in controller : {}", e.getMessage());
			return exceptionProcessor.processException(e);
		}
	} // End of queueByModuleName.
} // End of QueueController.