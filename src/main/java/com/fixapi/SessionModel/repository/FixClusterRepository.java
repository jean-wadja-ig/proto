package com.fixapi.SessionModel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixapi.SessionModel.domain.FixCluster;
import com.fixapi.SessionModel.domain.FixFunction;

@Repository
public interface FixClusterRepository extends JpaRepository<FixCluster, Long>{

    public Optional<FixCluster> findByClusterNameAndFixFunction(String clusterName, FixFunction fixFunction);
}
