package com.tragsa.micropersonas.repository;

import static org.assertj.core.api.Assertions.assertThat;

import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import com.tragsa.micropersonas.entity.PersonaEntity;

@DataJpaTest
class PersonaRepositoryTest {

	@Autowired
	private PersonaRepository personaRepository;

	@BeforeEach
	void setUp() {
		personaRepository.save(new PersonaEntity("Juan", "11111111A", 25, "juan@email.com"));
		personaRepository.save(new PersonaEntity("Maria", "22222222B", 34, "maria@email.com"));
		personaRepository.save(new PersonaEntity("Pedro", "33333333C", 42, "pedro@email.com"));
	}

	@Test
	void findByEdadGreaterThan_ReturnsFilteredList() {
		List<PersonaEntity> result = personaRepository.findByEdadGreaterThan(30);
		assertThat(result).hasSize(2);
		assertThat(result).extracting(PersonaEntity::getNombre).containsExactlyInAnyOrder("Maria", "Pedro");
	}

	@Test
	void findByEdadGreaterThan_WithNoMatches_ReturnsEmptyList() {
		List<PersonaEntity> result = personaRepository.findByEdadGreaterThan(50);
		assertThat(result).isEmpty();
	}

	@Test
	void findByEdadBetween_ReturnsListWithinRange() {
		List<PersonaEntity> result = personaRepository.findByEdadBetween(20, 35);
		assertThat(result).hasSize(2);
		assertThat(result).extracting(PersonaEntity::getNombre).containsExactlyInAnyOrder("Juan", "Maria");
	}

	@Test
	void findByEdadBetween_IncludingExactBoundaries_ReturnsInclusiveResults() {
		List<PersonaEntity> result = personaRepository.findByEdadBetween(25, 42);
		assertThat(result).hasSize(3);
	}

	@Test
	void countByEdadGreaterThan_ReturnsCorrectCount() {
		Integer count = personaRepository.countByEdadGreaterThan(30);
		assertThat(count).isEqualTo(2);
	}

	@Test
	void countByEdadGreaterThan_WithVeryHighAge_ReturnsZero() {
		Integer count = personaRepository.countByEdadGreaterThan(100);
		assertThat(count).isZero();
	}
}
