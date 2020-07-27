package com.cds.regression.services;

import org.springframework.stereotype.Controller;

import com.cds.regression.commons.Response;
import com.cds.regression.model.Queue;

@Controller
public interface QueueService {

	Response saveQueue(Queue project);

	Response updateQueue(Queue project);

	Response deleteQueueById(String id);

	Response findAll();

	Response findById(String id);

	Response findByProjectName(String projectName);

	Response findByEnvName(String envName);

	Response findByIp(String ip);

	Response findByModuleNamePrjName(String moduleName, String projectName);

	Response findByModuleName(String moduleName);
} // End of QueueService.