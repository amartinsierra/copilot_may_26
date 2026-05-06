package com.tragsa.microtemas.service;

import java.util.List;

import com.tragsa.microtemas.dto.Comentario;
import com.tragsa.microtemas.dto.Tema;

public interface TemasService {
	List<Tema> getTemas();
	List<Comentario> getComentarios(int idTema);
	Tema saveTema(Tema tema);
}
