package de.sones.GraphDSJavaClient.Errors;

public interface IWarning 
{
	public String getID();
	
	public String getMessage();
	
	public StackTraceElement getStackTrace();
}
