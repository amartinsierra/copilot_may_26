package com.tragsa.microbuscador.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.tragsa.microbuscador.model.Item;
import com.tragsa.microbuscador.repository.ItemsRepository;

import lombok.RequiredArgsConstructor;
@Service
@RequiredArgsConstructor 
public class BuscadorServiceImpl implements BuscadorService {
	private final ItemsRepository itemsRepository;	
	
	@Override
	public List<Item> buscarPorTematica(String tematica) {
		return itemsRepository.findByTematica(tematica);
	}
	@Override
	public boolean nuevoItem(Item item) {
		if(itemsRepository.findFirstByUrl(item.getUrl()).isEmpty()){
			itemsRepository.save(item);
			return true;
		}
		return false;
	}

}
