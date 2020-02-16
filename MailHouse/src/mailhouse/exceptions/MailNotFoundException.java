package mailhouse.exceptions;

import java.util.*;

public class MailNotFoundException extends Exception{
	
	String message;
	
	public MailNotFoundException(String msg){
		super(msg);
		this.message =msg;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}