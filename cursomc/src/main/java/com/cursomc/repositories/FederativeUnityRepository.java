package com.cursomc.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.cursomc.domain.FederativeUnity;

@Repository
public interface FederativeUnityRepository extends JpaRepository<FederativeUnity, Integer> {

}