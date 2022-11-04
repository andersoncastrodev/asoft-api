package com.asoft.api.exceptionhandle;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import com.asoft.api.exception.DomainException;

@ControllerAdvice //Tratar Exceções do controle Geral.
public class ApiExceptionHandler extends ResponseEntityExceptionHandler {

	@Override //Sobrecrevendo o metodo Original
	protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex,
			HttpHeaders headers, HttpStatus status, WebRequest request) {

		List<ErroProblema.Campo> campos = new ArrayList<>(); 
		
		for(ObjectError error : ex.getBindingResult().getAllErrors()) {
			String nome =  ((FieldError) error).getField();
			String mensagem = error.getDefaultMessage();
			
			campos.add(new ErroProblema.Campo(nome, mensagem));
		}
		
		ErroProblema erroProblema = new ErroProblema();
		erroProblema.setStatus( status.value() );
		erroProblema.setDataHora(LocalDateTime.now());
		erroProblema.setTitulo("Um ao Mais campos estão Invalidos.");
		erroProblema.setCampos(campos);
		
        //Metodo Original: handleExceptionInternal(ex, body, headers, status, request);
		return handleExceptionInternal(ex, erroProblema, headers, status, request);
	}
	
	@ExceptionHandler(DomainException.class)
	public ResponseEntity<Object> handleDomainException (DomainException ex, WebRequest request){
		
		HttpStatus status = HttpStatus.BAD_REQUEST;
		
		ErroProblema erroProblema = new ErroProblema();
		erroProblema.setStatus( status.value() );
		erroProblema.setDataHora(LocalDateTime.now());
		erroProblema.setTitulo( ex.getMessage() );
		
		return handleExceptionInternal(ex, erroProblema, new HttpHeaders(), status, request);
	}
	
}
