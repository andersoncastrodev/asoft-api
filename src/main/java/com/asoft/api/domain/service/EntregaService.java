package com.asoft.api.domain.service;

import java.time.LocalDateTime;
import java.time.OffsetDateTime;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.asoft.api.domain.model.Cliente;
import com.asoft.api.domain.model.Entrega;
import com.asoft.api.domain.model.StatusEntrega;
import com.asoft.api.domain.repository.ClienteRepository;
import com.asoft.api.domain.repository.EntregaRepository;
import com.asoft.api.exception.DomainException;

@Service
public class EntregaService {

	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private ClienteService clienteService;
	
	
	@Transactional
	public Entrega salvaEntrega(Entrega entrega) {
		
		//Consultando se o cliente Existe.
		Cliente cliente = clienteService.buscarCliente( entrega.getCliente().getId() );
		
		entrega.setCliente(cliente);
		
		//Regras de negocios aqui, antes de SALVAR.
		entrega.setStatus(StatusEntrega.PENDENTE);
		entrega.setDataPedido( OffsetDateTime.now() );
		
		return entregaRepository.save(entrega);
	}
}
