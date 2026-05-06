package com.tragsa.microbuscador.service;

import java.util.List;

import com.tragsa.microbuscador.model.Item;

public interface BuscadorService {
	List<Item> buscarPorTematica(String tematica);
	boolean nuevoItem(Item item);
}
