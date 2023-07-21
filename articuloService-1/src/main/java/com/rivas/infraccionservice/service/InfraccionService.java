package com.rivas.infraccionservice.service;
import com.rivas.infraccionservice.emtity.*;

import java.util.List;

import org.springframework.data.domain.Pageable;

import com.rivas.infraccionservice.emtity.Infraccion;

public interface InfraccionService {
	public List<Infraccion> findAll(Pageable page);
	public List<Infraccion> findByDni(String dni, Pageable page);
	public Infraccion findById(int id);
	public Infraccion save(Infraccion infraccion);
	public Infraccion update(Infraccion infraccion);
	public void delete(int id);
}
