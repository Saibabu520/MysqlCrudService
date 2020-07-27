package com.cds.regression.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cds.regression.model.Queue;

public interface QueueRepo extends JpaRepository<Queue, String> {

	Optional<List<Queue>> findByProjectId(String id);

	Optional<List<Queue>> findByEnvironment(String env);

	Optional<List<Queue>> findByModuleName(String moduleName);

	Optional<List<Queue>> findByIp(String ip);

	@Query(value = "select * from queue_details md where md.module_name=?2 and md.project_name=?1 ", nativeQuery = true)
	Optional<Queue> findByModuleProj(String projectName, String moduleName);
} // End of QueueRepo.