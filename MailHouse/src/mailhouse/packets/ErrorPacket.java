package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;
import java.io.*;

public class ErrorPacket extends Packet{
	
	public String message;
	
	public ErrorPacket(String message){
		super(PacketTypes.ErrorPacket);
		this.message = message;
	}
	
	public String getMessage(){
		return this.message;
	}
	
	public void setMessage(String message){
		this.message = message;
	}
	
}