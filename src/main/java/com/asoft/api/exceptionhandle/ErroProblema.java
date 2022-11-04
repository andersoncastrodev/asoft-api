package com.asoft.api.exceptionhandle;

import java.time.LocalDateTime;
import java.util.List;

public class ErroProblema {

	private Integer status;
	
	private LocalDateTime dataHora;
	
	private String titulo;
	
	private List<Campo> campos;

	
	//Classe Interna tipo Static
	public static class Campo{
		
		private String nome;
		private String mensagem;
		
		//Construtor Iniciando a Variaveis
		public Campo(String nome, String mensagem) {
			super();
			this.nome = nome;
			this.mensagem = mensagem;
		}
		
		public String getNome() {
			return nome;
		}
		public void setNome(String nome) {
			this.nome = nome;
		}
		public String getMensagem() {
			return mensagem;
		}
		public void setMensagem(String mensagem) {
			this.mensagem = mensagem;
		}

	}
	
	//Gets e Sets
	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public String getTitulo() {
		return titulo;
	}

	public void setTitulo(String titulo) {
		this.titulo = titulo;
	}

	//Get e Set da Lista<Campos>
	public List<Campo> getCampos() {
		return campos;
	}

	public void setCampos(List<Campo> campos) {
		this.campos = campos;
	}
	
	
}
