package br.com.star.wars.api.exceptions;

public class ObjectNotFoundException extends RuntimeException
{

	private static final long serialVersionUID = 3352783180971384950L;
	
	public ObjectNotFoundException(String msg) {
		super(msg);
	}
	
	public ObjectNotFoundException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
