package de.sones.GraphDSJavaClient.Errors;

public interface IError 
{
	public String getID();
	
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
