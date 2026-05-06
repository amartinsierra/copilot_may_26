package com.tragsa.microcliente.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.tragsa.microcliente.dto.Alumno;

@Service
public class AlumnosServiceImpl implements AlumnosService {

	private final RestClient restClient;
	private final String url = "http://localhost:8080/alumnos";

	public AlumnosServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Alumno> getAlumnosCalificacionMinima(Float calificacionMinima) {
		Alumno[] alumnos = restClient.get()
				.uri(url)
				.retrieve()
				.body(Alumno[].class);
		
		if (alumnos == null) {
			return List.of();
		}

		return Arrays.stream(alumnos)
				.filter(a -> a.getCalificacion() != null && a.getCalificacion() >= calificacionMinima)
				.toList();
	}

}
