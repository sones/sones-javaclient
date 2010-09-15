package de.sones.GraphDSJavaClient.Errors;

public interface IWarning 
{
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
