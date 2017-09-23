package com.northqstandalone.maven.models;

public class ErrorModel {
	
	public String ErrorMessage;

	public String getErrorMessage() {
		return ErrorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		ErrorMessage = errorMessage;
	}
	
	public void clearErrorMessage() {
		ErrorMessage = "";
	}
}
