package de.sones.GraphDSClient.Errors;

public interface IWarning 
{
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
