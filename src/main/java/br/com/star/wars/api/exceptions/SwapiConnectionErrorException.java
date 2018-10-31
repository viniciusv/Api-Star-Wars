package br.com.star.wars.api.exceptions;

public class SwapiConnectionErrorException extends RuntimeException
{

	private static final long serialVersionUID = 3352783180971384950L;
	
	public SwapiConnectionErrorException(String msg) {
		super(msg);
	}
	
	public SwapiConnectionErrorException(String msg, Throwable cause) {
		super(msg, cause);
	}

}
