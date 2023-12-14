package ar.com.codoacodo.interfaces;

import java.util.ArrayList;

import ar.com.codoacodo.entity.Orador;

public interface IOrador {
	void save(Orador orador);

	Orador getById(Long id);
	
	ArrayList<Orador> getAll();
	
	void deleteById(Long id);
	
	Orador upDate(Orador orador);
}
