package com.cds.regression.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.cds.regression.model.Environments;

public interface EnvironmentsRepo extends JpaRepository<Environments, String> {

  Optional<List<Environments>> findByProjectId(String id);

  Optional<Environments> findByIp(String ip);

  void deleteByIp(String ip);

  List<Environments> findByProjectName(String projectName);
  
  @Modifying
  @Query("update Environments set projectId=?2 where id=?1")
  void updateProjectInEnv(String envId, String projectId);
} // End of EnvironmentsRepo.