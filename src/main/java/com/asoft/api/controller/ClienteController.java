package com.asoft.api.controller;


import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.api.domain.model.Cliente;
import com.asoft.api.domain.repository.ClienteRepository;
import com.asoft.api.domain.service.ClienteService;

@RestController
@RequestMapping("/clientes")
public class ClienteController {

	@PersistenceContext
	private EntityManager manager; 
	
	@Autowired
	private ClienteRepository clienteRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@GetMapping
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
		
		//Busca por Like
		List<Cliente> lista5 = clienteRepository.findByNomeContaining("F");
		
		return clienteRepository.findAll(); 
		
	}
	
	
	@GetMapping("/{clienteId}")
	public ResponseEntity<Cliente> buscarId(@PathVariable Long clienteId) {
		
		// 
//		Optional<Cliente> cliente = clienteRepository.findById(clienteId);
//		
//		if (cliente.isPresent()) {
//			return ResponseEntity.ok(cliente.get());
//		}
//		return ResponseEntity.notFound().build();
		
		// Menos codigo
		
		return clienteRepository.findById(clienteId)
				.map(cliente -> ResponseEntity.ok(cliente))
				.orElse(ResponseEntity.notFound().build());
	}
	
	//Salvando Cliente via post.
	@PostMapping
	public Cliente adicionar(@Valid @RequestBody Cliente cliente) {
		//return clienteRepository.save(cliente);
		return clienteService.salvar(cliente);
	}
	
	//Update de cliente
	@PutMapping("/{clienteId}")
	public ResponseEntity<Cliente> atualizar(@PathVariable Long clienteId,@Valid @RequestBody Cliente cliente){
		
		//Verifica se Existe
		if (!clienteRepository.existsById(clienteId) ) {
			return ResponseEntity.notFound().build(); //Erro 404
		}
		//Seta o id para o jpa atualizar.
		cliente.setId(clienteId);
		
		//Salvando
		//cliente = clienteRepository.save(cliente);
		cliente = clienteService.salvar(cliente);
		return ResponseEntity.ok(cliente); 
	}
	
	@DeleteMapping("/{clienteId}")
	public ResponseEntity<Void> remover(@PathVariable Long clienteId){
		
		//Verifica se Existe
		if (!clienteRepository.existsById(clienteId) ) {
			return ResponseEntity.notFound().build(); //Erro 404
		}
		
		//clienteRepository.deleteById(clienteId);
		clienteService.excluir(clienteId);
		
		return ResponseEntity.noContent().build();
	}
	
}
