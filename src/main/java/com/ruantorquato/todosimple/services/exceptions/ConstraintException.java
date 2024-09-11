package com.ruantorquato.todosimple.services.exceptions;

public class ConstraintException extends RuntimeException {
	
	private static final long serialVersionUID = 1L;

	public ConstraintException (Object id) {
		super("Usuário existente" + id);
	}

}
