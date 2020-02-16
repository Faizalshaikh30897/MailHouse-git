package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class GetMailPacket extends Packet{
	
	private Mail mail;
	
	public GetMailPacket(Mail mail){
		super(PacketTypes.GetMailPacket);
		this.mail = mail;
	}
	
	public GetMailPacket(){
		super(PacketTypes.GetMailPacket);
		this.mail =null;
	}
	
	public Mail getMail(){
		return this.mail;
	}
	
	public void setMail(Mail mail){
		this.mail = mail;
	}
	
}