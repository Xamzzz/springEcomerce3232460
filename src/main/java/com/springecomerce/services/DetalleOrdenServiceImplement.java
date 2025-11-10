package com.springecomerce.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.springecomerce.model.DetalleOrden;
import com.springecomerce.repository.IDetalleOrdenRepository;

@Service
public class DetalleOrdenServiceImplement implements IDetalleOrdenService {
	
	@Autowired
	private IDetalleOrdenRepository detalleRepository;

	@Override
	public DetalleOrden save(DetalleOrden detalleOrden) {
		// TODO Auto-generated method stub
		return detalleRepository.save(detalleOrden);
	}

}
