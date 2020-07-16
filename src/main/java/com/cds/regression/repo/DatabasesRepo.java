package com.cds.regression.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.cds.regression.model.Databases;

public interface DatabasesRepo extends JpaRepository<Databases, String> {

  Optional<List<Databases>> findByProjectId(String projectId);

  @Query("select db from Databases db where db.projectId=?1 and db.environment=?2")
  Optional<List<Databases>> findByProjEnv(String projectId, String env);
} // End of DatabasesRepo.