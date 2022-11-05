package com.asoft.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.asoft.api.domain.model.Entrega;
import com.asoft.api.domain.repository.EntregaRepository;
import com.asoft.api.domain.service.EntregaService;
import com.asoft.api.factoryMapper.EntregaMapper;
import com.asoft.api.modelDTO.EntregaDTO;
import com.asoft.api.modelDTO.input.EntregaDTOInput;

@RestController
@RequestMapping("/entregas")
public class EntregaContoller {

	@Autowired
	private EntregaService entregaService;
	
	@Autowired
	private EntregaRepository entregaRepository;
	
	@Autowired
	private EntregaMapper entregaMapper;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public EntregaDTO criarEntrega(@Valid @RequestBody EntregaDTOInput entregaDTOInput) {
		Entrega novaEntrega = entregaMapper.toEntity(entregaDTOInput);
		
		Entrega entreaConsulta = entregaService.salvaEntrega(novaEntrega); 
		
		return entregaMapper.toModel( entreaConsulta );
	}
	
	//Traz todas as Entregas
	@GetMapping
	public List<EntregaDTO> listarEntregar(){
		List<Entrega> lista =  entregaRepository.findAll();
		return entregaMapper.toCollectionModel(lista);
	}
	
	@GetMapping("/{entregaId}")
	public ResponseEntity<EntregaDTO> buscarEntrega(@PathVariable Long entregaId){
		
		return entregaRepository.findById(entregaId)
				.map(entrega ->{			
				
					//AQUI TRANSFERI DO OBJETO MODEL (tabela no banco para o Objeto DTO) //
					// o objeto entrega = Model(tabela) e o Objeto entregaDto = Model DTO.
					//Mais Resumido.
					EntregaDTO entregaDto = entregaMapper.toModel(entrega);  // modelMapper.map(entrega, EntregaDTO.class);
					
					
					//AQUI TRANSFERI DO OBJETO MODEL (tabela no banco para o Objeto DTO) //
					// o objeto entrega = Model(tabela) e o Objeto entregaDto = Model DTO.
					
//					EntregaDTO entregaDto = new EntregaDTO();
//					entregaDto.setId(entrega.getId());
//					entregaDto.setNomeCliente(entrega.getCliente().getNome());
//					entregaDto.setDestinatario(new DestinatarioDTO() );
//					entregaDto.getDestinatario().setNome(entrega.getDestinatario().getNome());
//					entregaDto.getDestinatario().setLogradouro(entrega.getDestinatario().getLogradouro());
//					entregaDto.getDestinatario().setNumero(entrega.getDestinatario().getNumero());
//					entregaDto.getDestinatario().setComplemento(entrega.getDestinatario().getComplemento());
//					entregaDto.getDestinatario().setBairro(entrega.getDestinatario().getBairro());
//					
//					entregaDto.setTaxa(entrega.getTaxa());
//					entregaDto.setStatus(entrega.getStatus());
//					entregaDto.setDataPedido(entrega.getDataPedido());
//					entregaDto.setDataFinalizacao(entrega.getDataFinalizacao());
					
					return ResponseEntity.ok(entregaDto);
				})
				.orElse(ResponseEntity.notFound().build());
	}
}
