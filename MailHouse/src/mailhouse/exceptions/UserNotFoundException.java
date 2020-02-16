package mailhouse.exceptions;

import java.util.*;

public class UserNotFoundException extends Exception{
	
	String message;
	
	public UserNotFoundException(String msg){
		super(msg);
		this.message =msg;
	}
	
	public String getMessage(){
		return this.message;
	}
	
}