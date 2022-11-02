package com.asoft.api.controller;


import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.api.domain.model.Cliente;

@RestController
public class ClienteController {

	@GetMapping("/clientes")
	public List<Cliente> listar() {
	
		Cliente cliente1 = new Cliente();
		cliente1.setId(1L);
		cliente1.setNome("Antonio");
		cliente1.setEmail("antonio@gmail.com");
		cliente1.setTelefone("85 9 8585-8575");
		
		Cliente cliente2 = new Cliente();
		cliente2.setId(1L);
		cliente2.setNome("Pedro");
		cliente2.setEmail("pedro@gmail.com");
		cliente2.setTelefone("85 9 8447-8547");
		
		List<Cliente> lista = Arrays.asList(cliente1,cliente2);
		
		return lista ;
	}
}
