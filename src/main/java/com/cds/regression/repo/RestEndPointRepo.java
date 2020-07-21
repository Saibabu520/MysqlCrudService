package com.cds.regression.repo;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.cds.regression.model.RestEndPoints;

@Repository
public interface RestEndPointRepo extends JpaRepository<RestEndPoints, String> {

  @Query(value = "select * from rest_endpoints md where md.module_id=?1 ", nativeQuery = true)
  Optional<List<RestEndPoints>> findByModuleId(String moduleId);

  @Query(value = "select * from rest_endpoints md where md.environment_id=?1  ", nativeQuery = true)
  Optional<List<RestEndPoints>> findByEnvironmentId(String EnvironmentId);

  @Query(value = "select * from rest_endpoints md where md.module_id=?1 and md.environment_id=?2 ", nativeQuery = true)
  Optional<List<RestEndPoints>> findByModuleIdEnvId(String moduleId, String envId);

  @Modifying
  @Query(value = "delete from rest_endpoints  where module_id=?1 ", nativeQuery = true)
  void deleteByModuleId(String moduleId);

  @Modifying
  @Query(value = "delete from rest_endpoints  where environment_id=?1 ", nativeQuery = true)
  void deleteByEnvironmentId(String EnvironmentId);
} // End of RestEndPointRepo.