package com.interqu.errors;

import org.springframework.beans.factory.annotation.Autowired;

import com.interqu.db.ErrorRepository;

public class ErrorReport {

	@Autowired
	private ErrorRepository errorRepo;
	
	private long timestamp;
	private String error;
	private String errorLocation;
	private String errorDetails;
	
	public ErrorReport(long timestamp, String error, String errorLocation, String errorDetails) {
		super();
		this.timestamp = timestamp;
		this.error = error;
		this.errorLocation = errorLocation;
		this.errorDetails = errorDetails;
		errorRepo.save(this);
	}
	
	public ErrorReport(String error, String errorLocaiton, String errorDetails) {
		new ErrorReport(System.currentTimeMillis(), error, errorLocaiton, errorDetails);
	}
	
	public long getTimestamp() {
		return timestamp;
	}
	public void setTimestamp(long timestamp) {
		this.timestamp = timestamp;
	}
	public String getError() {
		return error;
	}
	public void setError(String error) {
		this.error = error;
	}
	public String getErrorDetails() {
		return errorDetails;
	}
	public void setErrorDetails(String errorDetails) {
		this.errorDetails = errorDetails;
	}

	public String getErrorLocation() {
		return errorLocation;
	}

	public void setErrorLocation(String errorLocation) {
		this.errorLocation = errorLocation;
	}
	
	
	
}
