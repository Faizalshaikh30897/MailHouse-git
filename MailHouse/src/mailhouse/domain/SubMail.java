package mailhouse.domain;

import java.util.*;
import java.io.*;
import java.time.*;

public class SubMail implements Serializable{
	
	private int mailId;
	private User fromUser;
	private String subject;
	private LocalDateTime time;
	
	public SubMail(int mailId,User fromUser,String subject,LocalDateTime time){
		
		this.mailId = mailId;
		this.subject = subject;
		this.fromUser = fromUser;
		this.time = time;
	}
	
	public SubMail(){}
	
	public int getMailId(){
		return this.mailId;
	}
	
	public void setMailId(){
		this.mailId = mailId;
	}
	
	public User getFromUser(){
		return this.fromUser;
	}
	
	public void setFromUser(User fromUser){
		this.fromUser = fromUser;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	
	public LocalDateTime getDateTime(){
		return this.time;
	}
	
	public void setDateTime(LocalDateTime time){
		this.time = time;
	}
	
	@Override
	public String toString(){
		return this.subject;
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.getMailId());
	}
}