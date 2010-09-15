package de.sones.GraphDBRESTClient.Errors;

public interface IWarning 
{
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
