package com.project.RestApi.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.project.RestApi.entity.Cycle;

public interface CycleRepository extends JpaRepository<Cycle, Integer> {
    Optional<Cycle> findByName(String name);

}
