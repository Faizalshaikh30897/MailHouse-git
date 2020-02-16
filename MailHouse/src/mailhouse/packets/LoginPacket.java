package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class LoginPacket extends Packet{
	
	public User user;
	
	public LoginPacket(User user){
		super(PacketTypes.LoginPacket);
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
}