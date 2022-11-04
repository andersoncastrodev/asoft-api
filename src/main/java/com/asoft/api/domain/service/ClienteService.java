package com.asoft.api.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asoft.api.domain.model.Cliente;
import com.asoft.api.domain.repository.ClienteRepository;
import com.asoft.api.exception.DomainException;

@Service
public class ClienteService {

	@Autowired
	private ClienteRepository clienteRepository;
	
	
	@Transactional //Uso o transactional Caso de erro no processor ele  cancelar a operacao.
	public Cliente salvar(Cliente cliente) {
		
		boolean emailUso = clienteRepository.findByEmail(cliente.getEmail())
				.stream()
				.anyMatch(clienteExistente -> !clienteExistente.equals(cliente));
		
		if(emailUso) {
			throw new DomainException("Já existe um cliente cadastrado com este email");
		}
		return clienteRepository.save(cliente);
	}
	
	@Transactional
	public void excluir(Long clienteId) {
		clienteRepository.deleteById(clienteId);
	}
	
	//Busca de o Cliente e Existe. Senão Lança uma Exceção 
	public Cliente buscarCliente(Long clienteId) {
		Cliente cliente = clienteRepository.findById(clienteId)
				.orElseThrow( ()-> new DomainException("Cliente Não Encontrado") );
		return cliente;
	}
	
}
