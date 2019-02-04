package com.fixapi.SessionModel.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.fixapi.SessionModel.domain.FixFunction;

@Repository
public interface FixFunctionRepository extends JpaRepository<FixFunction, Long> {

    public Optional<FixFunction> findByFunctionName(String functionName);
}
