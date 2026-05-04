package com.tragsa.microalumnos.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tragsa.microalumnos.domain.Alumno;
import com.tragsa.microalumnos.mapper.AlumnoMapper;
import com.tragsa.microalumnos.repository.AlumnoRepository;
@Service
public class AlumnoServiceImpl implements AlumnoService {
	//declara una variable de tipo AlumnoRepository para acceder a la base de datos y otra AlumnoMapper e inyectalas por constructor
	private final AlumnoRepository alumnoRepository;
	private final AlumnoMapper alumnoMapper;
	public AlumnoServiceImpl(AlumnoRepository alumnoRepository, AlumnoMapper alumnoMapper) {
		this.alumnoRepository = alumnoRepository;
		this.alumnoMapper = alumnoMapper;
	}

	@Override
	public List<Alumno> getAllAlumnos() {
		return alumnoRepository.findAll().stream()
				.map(alumnoMapper::toAlumno)
				.toList();
	}

	@Override
	public Alumno getAlumnoByDni(String dni) {
		return alumnoRepository.findById(dni)
				.map(alumnoMapper::toAlumno)
				.orElse(null);
	}

	@Override
	public List<Alumno> getAlumnosByCurso(String curso) {
		return alumnoRepository.findByCurso(curso).stream()
				.map(alumnoMapper::toAlumno)
				.toList();
	}

	@Override
	public Alumno createAlumno(Alumno alumno) {
		//añade el alumno si no existe dni y de lo contrario devuelve null
		if (alumnoRepository.existsById(alumno.getDni())) {
			return null;
		}
		return alumnoMapper.toAlumno(alumnoRepository.save(alumnoMapper.toAlumnoEntity(alumno)));
	}

	@Override
	public Alumno deleteAlumno(String dni) {
		//elimina el alumno si existe y devuelve el alumno eliminado, de lo contrario devuelve null
		return alumnoRepository.findById(dni)
				.map(alumno -> {
					alumnoRepository.delete(alumno);
					return alumnoMapper.toAlumno(alumno);
				})
				.orElse(null);
	}

	@Override
	public List<String> getCursos() {
		return alumnoRepository.findDistinctCurso();
	}

}
