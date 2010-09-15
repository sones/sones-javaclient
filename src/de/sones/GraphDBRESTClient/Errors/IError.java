package de.sones.GraphDBRESTClient.Errors;

public interface IError 
{
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
