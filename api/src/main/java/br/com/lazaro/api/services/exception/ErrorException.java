package br.com.lazaro.api.services.exception;

public class ErrorException extends RuntimeException{
	
	private static final long serialVersionUID = 1L;
	
	public ErrorException(String msg) {
		super(msg);
	}

}
