package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class SendMailPacket extends Packet{
	
	private Mail mail;
	
	public SendMailPacket(Mail mail){
		super(PacketTypes.SendMailPacket);
		this.mail = mail;
	}
	
	public Mail getMail(){
		return this.mail;
	}
	
	public void setMail(){
		this.mail = mail;
	}
	
}