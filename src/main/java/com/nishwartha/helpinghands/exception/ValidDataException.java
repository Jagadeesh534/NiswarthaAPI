package com.nishwartha.helpinghands.exception;

public class ValidDataException extends Exception{
	/**
	 * 
	 */
	private static final long serialVersionUID = 2619270204509738455L;

	private int errorCode;

	private String errorMessage;

	public ValidDataException() {
		super();
	}

	public ValidDataException(String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
	}

	public ValidDataException(int errorCode, String errorMessage) {
		super(errorMessage);
		this.errorMessage = errorMessage;
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorCode
	 */
	public int getErrorCode() {
		return errorCode;
	}

	/**
	 * @param errorCode
	 *            the errorCode to set
	 */
	public void setErrorCode(int errorCode) {
		this.errorCode = errorCode;
	}

	/**
	 * @return the errorMessage
	 */
	public String getErrorMessage() {
		return errorMessage;
	}

	/**
	 * @param errorMessage
	 *            the errorMessage to set
	 */
	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

}
