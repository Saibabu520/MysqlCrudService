package com.cds.regression.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cds.regression.model.Modules;

@Repository
public interface ModuleRepo extends JpaRepository<Modules, String> {

  @Query(value = "select * from modules md where md.project_id=?1 ", nativeQuery = true)
  Optional<List<Modules>> findByProjectId(String projectId);

  @Modifying
  @Query(value = "delete from modules  where project_id=?1 ", nativeQuery = true)
  void deleteModuleByProjectId(String projectId);

} // End of ModuleRepo.