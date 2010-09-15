package de.sones.GraphDSClient.Errors;

public interface IError 
{
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
