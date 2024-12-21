package com.tecnica.pokeapi.repository;


import com.tecnica.pokeapi.model.CoachEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoachRepository extends JpaRepository<CoachEntity, Integer> {
}
