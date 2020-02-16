package mailhouse.domain;

import java.util.*;
import java.io.*;

public class Attachment implements Serializable{
	
	private int attachmentId;
	private File file;
	private String extension;
	private String fileName;
	private byte[] data;
	private int filesize;
	//private Mail mail;
	
public Attachment(int attachmentId, File file, String extension,String fileName){//,Mail mail){
		this.attachmentId = attachmentId;
		this.file = file;
		this.extension = extension;
		this.fileName = fileName;
		//this.mail = mail;
	}
	
	public Attachment(){}
	
	public int getAttachmentId(){
		return this.attachmentId;
	}
	
	public File getFile(){
		return this.file;
	}
	
	public String getExtension(){
		return this.extension;
	}
	
	public String getFileName(){
		return this.fileName;
	}
	
	//public Mail getMail(){
	//	return this.mail;
	//}
	
	public void setAttachmentId(int attachmentId){
		this.attachmentId = attachmentId;
	}
	
	public void setFile(File file){
		this.file = file;
	}
	
	public void setExtension(String extension){
		this.extension  =extension;
	}
	
	public void setFileName(String fileName){
		this.fileName = fileName;
	}
	
	//public void setMail(Mail mail){
	//	this.mail = mail;
	//}
	
	public byte[] getData(){
		return this.data;
	}
	
	public void setData(byte[] data){
		this.data = data;
	}
	
	public void setFilesize(int size){
		this.filesize = size;
	}
	
	public int getFilesize(){
		return this.filesize;
	}
	
	@Override
	public String toString(){
		return this.fileName + " : " + this.extension;
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.getAttachmentId());
	}
}