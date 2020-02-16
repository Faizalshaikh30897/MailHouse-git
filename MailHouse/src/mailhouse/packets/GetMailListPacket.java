package mailhouse.packets;

import java.util.*;
import mailhouse.domain.*;

public class GetMailListPacket extends Packet{
	
	private List<SubMail> mailList = new ArrayList<>();
	private User user;
	
	public GetMailListPacket(List<SubMail> mail,User user){
		super(PacketTypes.GetMailListPacket);
		this.mailList = mail;
		this.user = user;
	}
	
	
	public GetMailListPacket(){
		super(PacketTypes.GetMailListPacket);
	}
	
	public void setUser(User user){
		this.user = user;
	}
	
	public User getUser(){
		return this.user;
	}
	
	public void setmailList(List<SubMail> mailList){
		this.mailList = mailList;
	}
	
	public List<SubMail> getMailList(){
		return this.mailList;
	}
	
	public boolean addMail(SubMail subMail){
		return this.mailList.add(subMail);
	}
	
	public boolean removeMail(SubMail subMail){
		return this.mailList.remove(subMail);
	}
}