package mailhouse.domain;

import java.util.*;
import java.io.*;
import java.time.*;

public class Mail implements Serializable{
	
	private int mailId;
	private User fromUser;
	private User toUser;
	private String content;
	private String subject;
	private LocalDateTime time;
	private List<Attachment> attachment = new ArrayList<>();
	
	public Mail(int mailId,User fromUser,User toUser,String content,String subject,List<Attachment> attachment,LocalDateTime time){
		this.mailId = mailId;
		this.content = content;
		this.subject = subject;
		this.fromUser = fromUser;
		this.toUser = toUser;
		this.attachment = attachment;
		this.time = time;
	}
	
	public Mail(){}
	
	public int getMailId(){
		return this.mailId;
	}
	
	public void setMailId(int mailId){
		this.mailId = mailId;
	}
	
	public User getFromUser(){
		return this.fromUser;
	}
	
	public void setFromUser(User fromUser){
		this.fromUser = fromUser;
	}
	
	public User getToUser(){
		return this.toUser;
	} 
	
	public void setToUser(User toUser){
		this.toUser = toUser;
	}
	
	public String getContent(){
		return this.content;
	}
	
	public void setContent(String content){
		this.content = content;
	}
	
	public String getSubject(){
		return this.subject;
	}
	
	public void setSubject(String subject){
		this.subject = subject;
	}
	
	public List<Attachment> getAttachment(){
		return this.attachment;
	}
	
	public void setAttachment(List<Attachment> a){
		 this.attachment = a;
	}
	
	public void addAttachment(Attachment attachment){
		this.attachment.add(attachment);
	}
	
	public LocalDateTime getDateTime(){
		return this.time;
	}
	
	public void setDateTime(LocalDateTime time){
		this.time = time;
	}
	
	@Override
	public String toString(){
		return this.subject + " : " + this.content;
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.getMailId());
	}
}