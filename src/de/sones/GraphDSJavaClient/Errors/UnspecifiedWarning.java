package de.sones.GraphDSJavaClient.Errors;

public class UnspecifiedWarning implements IWarning 
{
	/**
	 * private members
	 */
	private String _ID;
	
	private String _Message;
	
	//currently unused
	private StackTraceElement _StackTrace;
	
	/**
	 * Constructors
	 */
	public UnspecifiedWarning(String myID, String myMessage)
	{		
		_ID = myID;
		_Message = myMessage;
		
		_StackTrace = null;
	}
	
	@Override
	public String getID() 
	{
		return _ID;
	}
	
	@Override
	public String getMessage() 
	{ 
		return _Message;
	}

	@Override
	public StackTraceElement getStackTrace() 
	{
		return _StackTrace;
	}
}
