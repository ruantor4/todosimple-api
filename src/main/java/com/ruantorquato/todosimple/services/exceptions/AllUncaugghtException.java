package com.ruantorquato.todosimple.services.exceptions;

public class AllUncaugghtException extends RuntimeException {

	private static final long serialVersionUID = 1L;

	public AllUncaugghtException(String message) {
		super("Unknown error occurred");
	}
}
