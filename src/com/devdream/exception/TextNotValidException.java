package com.devdream.exception;

/**
 * Occurs when an input text field takes an invalid value.
 * 
 * @author Asier Gonzalez
 * @version 1.0
 * @since 1.0
 */
public class TextNotValidException extends Exception {

	private static final long serialVersionUID = 5187217164342195848L;
	
	public static final String FIELD_NOT_VALID = "Field input not valid: ";
	
	private String fieldInfo;
	
	public TextNotValidException(String fieldInfo) {
		this.fieldInfo = fieldInfo;
	}
	
	@Override
	public String getMessage() {
		return FIELD_NOT_VALID + fieldInfo;
	}

}
