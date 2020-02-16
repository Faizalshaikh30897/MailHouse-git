package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class LogoutPacket extends Packet{
	
	public User user;
	
	public LogoutPacket(User user){
		super(PacketTypes.LogoutPacket);
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
}