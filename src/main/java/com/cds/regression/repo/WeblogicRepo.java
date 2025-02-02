package com.cds.regression.repo;

import com.cds.regression.model.Weblogic;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface WeblogicRepo extends JpaRepository<Weblogic, String> {
  
  Optional<List<Weblogic>> findByProjectName(String projectName);
  
  Optional<Weblogic> findByClientId(String clientId);

  Optional<Weblogic> findByServerName(String serverName);

  Optional<Weblogic> findByProjectId(String projectId);

  Optional<List<Weblogic>> findByModuleName(String moduleName);

  @Query(value = "select * from weblogic_details md where md.module_name=?2 and md.project_name=?1 ", nativeQuery = true)
  
  Optional<Weblogic> findByProjModule(String projName, String moduleName);
  
  
} // End of WeblogicRepo.