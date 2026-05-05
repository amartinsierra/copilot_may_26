package com.tragsa.micropersonas.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.tragsa.micropersonas.entity.PersonaEntity;

public interface PersonaRepository extends JpaRepository<PersonaEntity, String> {
	
	List<PersonaEntity> findByEdadGreaterThan(int edad);
	//lista de personas cuyas edades están entre dos valores dados
	List<PersonaEntity> findByEdadBetween(int edadMin, int edadMax);
	@Query("SELECT COUNT(p) FROM PersonaEntity p WHERE p.edad > :edad")
	int countByEdadGreaterThan(int edad);
}
