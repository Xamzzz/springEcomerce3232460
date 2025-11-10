package com.springecomerce.services;

import java.util.List;
import java.util.Optional;

import com.springecomerce.model.Orden;
import com.springecomerce.model.Usuario;

public interface IOrdenService {
	
	public Orden save(Orden orden);
	
	public List<Orden> findAll();
	
	public List<Orden> findByUsuario(Usuario usuario);
	
	public Optional<Orden> findById(Integer id);
	
	public String generearNumeroOrden();
}
