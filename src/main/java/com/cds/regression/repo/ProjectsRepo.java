package com.cds.regression.repo;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cds.regression.model.Projects;

@Repository
public interface ProjectsRepo extends JpaRepository<Projects, String> {

  Optional<Projects> findByProjectName(String projectName);
  
  @Modifying
  @Query("update Environments set projectName=?1 where projectId=?2")
  void updateProjectInEnv(String projectName, String id);
} // End of RegressionRepo.