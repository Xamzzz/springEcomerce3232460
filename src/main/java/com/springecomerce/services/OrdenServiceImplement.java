package com.springecomerce.services;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springecomerce.model.Orden;
import com.springecomerce.model.Usuario;
import com.springecomerce.repository.IOrdenRepository;

@Service
public class OrdenServiceImplement implements IOrdenService {

	@Autowired
	private IOrdenRepository ordenRepository;

	@Override
	public Orden save(Orden orden) {
		// TODO Auto-generated method stub
		return ordenRepository.save(orden);
	}

	@Override
	public List<Orden> findAll() {
		// TODO Auto-generated method stub
		return ordenRepository.findAll();
	}

	@Override
	public List<Orden> findByUsuario(Usuario usuario) {
		// TODO Auto-generated method stub
		return ordenRepository.findByUsuario(usuario);
	}

	@Override
	public Optional<Orden> findById(Integer id) {
		// TODO Auto-generated method stub
		return ordenRepository.findById(id);
	}

	@Override
	public String generearNumeroOrden() {
		// TODO Auto-generated method stub
		int numero = 0;
		String numeroconcatenado = "";
		List<Orden> ordenes = findAll();
		List<Integer> numeros = new ArrayList<>();
		// Funciones de java 8
		// Variable anonima
		ordenes.stream().forEach(O -> numeros.add(Integer.parseInt(O.getNumero())));
		// Validacion
		if (ordenes.isEmpty()) {
			numero = 1;
		} else {
			numero = numeros.stream().max(Integer::compare).get();
			numero++;
		}
		// Numero de ordenes
		if (numero < 10) {
			numeroconcatenado = "000000000000" + String.valueOf(numero);
		} else if (numero < 100) {
			numeroconcatenado = "00000000000" + String.valueOf(numero);
		} else if (numero < 1000) {
			numeroconcatenado = "0000000000" + String.valueOf(numero);
		}
		return numeroconcatenado;
	}

}
