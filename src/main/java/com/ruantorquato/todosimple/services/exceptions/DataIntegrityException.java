package com.ruantorquato.todosimple.services.exceptions;

public class DataIntegrityException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public DataIntegrityException (Object id) {
		super("Usuário existente" + id);
	}

}
