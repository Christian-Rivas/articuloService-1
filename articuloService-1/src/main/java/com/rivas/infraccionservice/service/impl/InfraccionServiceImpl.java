package com.rivas.infraccionservice.service.impl;

import java.util.List;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.rivas.infraccionservice.emtity.Infraccion;
import com.rivas.infraccionservice.repository.InfraccionRepository;
import com.rivas.infraccionservice.service.InfraccionService;

@Service
public class InfraccionServiceImpl implements InfraccionService {
	@Autowired
	private InfraccionRepository repository;

	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findAll(Pageable page) {
		try {
			return repository.findAll(page).toList();
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public List<Infraccion> findByDni(String dni, Pageable page) {
		try {
			return repository.findByDniContaining(dni, page);
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	@Transactional(readOnly=true)
	public Infraccion findById(int id) {
		try {
			Infraccion registro= repository.findById(id).orElseThrow();
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Infraccion save(Infraccion infraccion) {
		try {
			infraccion.setActivo(true);
			Infraccion registro=repository.save(infraccion);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public Infraccion update(Infraccion infraccion) {
		try {
			Infraccion registro=repository.findById(infraccion.getId()).orElseThrow();
			registro.setDni(infraccion.getDni());
			registro.setFalta(infraccion.getFalta());
			registro.setInfraccion(infraccion.getInfraccion());
			registro.setDescripcion(infraccion.getDescripcion());
			//registro.setInfraccion(infraccion.getPrecio());

			repository.save(registro);
			return registro;
		}catch(Exception e) {
			return null;
		}
	}

	@Override
	public void delete(int id) {
		try {
			Infraccion registro=repository.findById(id).orElseThrow();;
			repository.delete(registro);
		}catch(Exception e) {
			
		}
		
	}

}
