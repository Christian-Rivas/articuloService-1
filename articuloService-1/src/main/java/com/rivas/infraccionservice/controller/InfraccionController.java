package com.rivas.infraccionservice.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


import com.rivas.infraccionservice.service.InfraccionService;
import com.rivas.infraccionservice.emtity.Infraccion;

@RestController
@RequestMapping("/infracciones/")
public class InfraccionController {
	@Autowired
	private InfraccionService service;
	
	@GetMapping()
	public ResponseEntity<List<Infraccion>> findAll(
			@RequestParam(value= "dni", required= false, defaultValue= "") String dni,
			@RequestParam(value= "offset", required= false, defaultValue= "0") int pageNumber,
			@RequestParam(value= "limit", required= false, defaultValue= "5") int pageSize
			){
		Pageable page= PageRequest.of(pageNumber, pageSize);
		List<Infraccion> infraccion;
		if(dni== null) {
			infraccion=service.findAll(page);
		}else {
			infraccion=service.findByDni(dni, page);
		}
		
		if(infraccion.isEmpty()) {
			return ResponseEntity.noContent().build();
		}
		return ResponseEntity.ok(infraccion);		
	}
	

	@GetMapping(value="{id}")
	public ResponseEntity<Infraccion> findById(@PathVariable("id")int id){
		Infraccion infraccion= service.findById(id);
		if(infraccion==null) {
			return ResponseEntity.notFound().build();
		}
		return ResponseEntity.ok(infraccion);
	}
    @GetMapping("/usuario/{dni}")
    public ResponseEntity<List<Infraccion>> findByDni(@PathVariable String dni,Pageable page) {
        List<Infraccion> infracciones = service.findByDni(dni,page);
        return new ResponseEntity<>(infracciones, HttpStatus.OK);
    }
    
	@PostMapping
	public ResponseEntity<Infraccion> create(@RequestBody Infraccion infraccion){
		Infraccion registro= service.save(infraccion);
		return ResponseEntity.status(HttpStatus.CREATED).body(registro);
	}
	
	@PutMapping(value="/{id}")
	public ResponseEntity<Infraccion> update(@PathVariable("id")int id, @RequestBody Infraccion infraccion){
		Infraccion registro = service.findById(id);
		
		if(registro==null) {
			return ResponseEntity.notFound().build();
		}
        registro.setActivo(false);
        service.save(registro);
		return ResponseEntity.ok(infraccion);

	}
	
	@DeleteMapping(value="/{id}")
	public ResponseEntity<Infraccion> delete(@PathVariable("id")int id){
		service.delete(id);
		return ResponseEntity.ok(null);
	}
	
	
	

}
