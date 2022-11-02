package com.asoft.api.domain.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.asoft.api.domain.model.Cliente;

@Repository
public interface ClienteRepository extends JpaRepository<Cliente, Long> {

	//Pequisas Personalizadas //
	
	//Por nome completo
	List<Cliente> findByNome(String nome);
	
	//Por like, usando o containing
	List<Cliente> findByNomeContaining(String nome);
	
	
}
