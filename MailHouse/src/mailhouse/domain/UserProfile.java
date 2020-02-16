package mailhouse.domain;

import java.util.*;
import java.time.*;
import java.io.*;

public class UserProfile implements Serializable{
	
	private File image;
	/*public enum Gender{
		MALE(0),
		FEMALE(1);
		private int n;
		private Gender(int n){
			this.n = n;
		}
		public int getn(){
			return this.n;
		}	
		
		public void setn(int n){
			this.n=n;
		}
	}*/
	private boolean gender;
	private LocalDateTime dateOfBirth;
	
	public UserProfile(File image,boolean gender,LocalDateTime dateOfBirth){
		this.image = image;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	public UserProfile(boolean gender,LocalDateTime dateOfBirth){
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	public UserProfile(){
		
	}
	public File getImage(){
		return this.image;
	}
	
	public void setImage(File image){
		this.image  = image;
	}
	
	public boolean getGender(){
		return this.gender;
	}
	
	public void setGender(boolean gender){
		this.gender = gender;
	}
	
	public LocalDateTime getDateOfBirth(){
		return this.dateOfBirth;
	}
	
	public void setDateOfBirth(LocalDateTime dateOfBirth){
		this.dateOfBirth =dateOfBirth;
	}
	
	@Override
	public String toString(){
		return "UserProfile:" + this.dateOfBirth.toString();
	}
	
	
	
}