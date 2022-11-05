package com.asoft.api.factoryMapper;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.asoft.api.domain.model.Entrega;
import com.asoft.api.modelDTO.EntregaDTO;
import com.asoft.api.modelDTO.input.EntregaDTOInput;

@Component
public class EntregaMapper {

	@Autowired
	private ModelMapper modelMapper;
	
	//Retorna um Objeto Entrega
	public EntregaDTO toModel(Entrega entrega) {
		return modelMapper.map(entrega, EntregaDTO.class);
	}
	
    //Retorna uma Lista de EntregaDAO
	public List<EntregaDTO> toCollectionModel(List<Entrega> lista) {
		return lista.stream()
				.map(this::toModel)
				.collect(Collectors.toList());
	}
	
	//Ao Contrario, de DTO para MODEL Entrega.
	public Entrega toEntity(EntregaDTOInput entregaInput) {
		return modelMapper.map(entregaInput, Entrega.class);
	}
}
