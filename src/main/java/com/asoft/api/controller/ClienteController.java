package com.asoft.api.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.api.domain.model.Cliente;
import com.asoft.api.domain.repository.ClienteRepository;

@RestController
public class ClienteController {

	@PersistenceContext
	private EntityManager manager; 
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@GetMapping("/clientes")
	public List<Cliente> listar() {
	
//		Cliente cliente1 = new Cliente();
//		cliente1.setId(1L);
//		cliente1.setNome("Antonio21");
//		cliente1.setEmail("antonio@gmail.com");
//		cliente1.setTelefone("85 9 8585-8575");
//		
//		Cliente cliente2 = new Cliente();
//		cliente2.setId(1L);
//		cliente2.setNome("Pedro");
//		cliente2.setEmail("pedro@gmail.com");
//		cliente2.setTelefone("85 9 8447-8547");
//		
//		List<Cliente> lista1 = Arrays.asList(cliente1,cliente2);
		
//		List<Cliente> lista2 =  manager.createQuery("from Cliente", Cliente.class).getResultList()
		
//		List<Cliente> lista3 = clienteRepository.findAll();
		
		List<Cliente> lista4 = clienteRepository.findByNome("Antonio Silva");
		
		List<Cliente> lista5 = clienteRepository.findByNomeContaining("F");
		
		return lista5; 
		
	}
}
