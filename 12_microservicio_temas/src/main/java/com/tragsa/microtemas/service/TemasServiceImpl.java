package com.tragsa.microtemas.service;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestClient;

import com.tragsa.microtemas.dto.Comentario;
import com.tragsa.microtemas.dto.Tema;
@Service
public class TemasServiceImpl implements TemasService {

	@Value("${urlBase}")
	private String urlBase;
	
	private RestClient restClient;

	public TemasServiceImpl(RestClient restClient) {
		this.restClient = restClient;
	}

	@Override
	public List<Tema> getTemas() {
		Tema[] temas = restClient.get()
				.uri(urlBase + "/posts")
				.retrieve()
				.body(Tema[].class);
		return (temas != null) ? Arrays.asList(temas) : List.of();
	}

	@Override
	public List<Comentario> getComentarios(int idTema) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Tema saveTema(Tema tema) {
		return restClient.post()
				.uri(urlBase + "/posts")
				.contentType(MediaType.APPLICATION_JSON)
				.body(tema)
				.retrieve()
				.body(Tema.class);
	}

}