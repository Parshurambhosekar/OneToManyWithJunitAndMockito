package com.parshuram.onetomanymapping.exception;

public class ResourceNotFoundExcepton extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	

	String resourceName;
	String fieldName;
	Integer fieldValue;
	String userName;
	
	public ResourceNotFoundExcepton(String resourceName,String fieldName,Integer fieldValue) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,fieldValue));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.fieldValue=fieldValue;
	}
	
	public ResourceNotFoundExcepton(String resourceName,String fieldName,String userName) {
		super(String.format("%s is not found with %s : %s",resourceName,fieldName,userName));
		this.resourceName=resourceName;
		this.fieldName=fieldName;
		this.userName=userName;
	}	

}
