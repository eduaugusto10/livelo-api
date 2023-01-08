package com.livelo.eduardo.exception;

public class LiveloException extends Exception {
	
	public LiveloException() {
		
	}
	
	public LiveloException(String message) {
		super(message);
	}
	
	public LiveloException(Throwable cause) {
		
	}
	
	public LiveloException(String message, Throwable cause) {
		super(message, cause);
	}
	
	public LiveloException(String message, Throwable cause, boolean enableSupression, boolean writableStackTrace) {
		super(message, cause, enableSupression, writableStackTrace);
	}

}
