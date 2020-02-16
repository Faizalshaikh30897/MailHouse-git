package mailhouse.domain;

import java.util.*;
import java.io.*;

public class User implements Serializable{
	
	private int userId;
	private String username;
	private String password;
	private String email;
	
	
	public User(int userId,String username,String password,String email){//, boolean gender, int age){
		this.userId = userId;
		this.username = username;
		this.password = password;
		this.email = email;
	
		//this.gender = gender;
		//this.age = age;
	}
	
	public User(){}
	
	public int getUserId(){
		return this.userId;
	}
	
	public void setUserId(int userId){
		this.userId = userId;
	}
	
	public String getUsername(){
		return this.username;
	}
	
	public void setUsername(String username){
		this.username = username;
	}
	
	public String getPassword(){
		return this.password;
	}
	
	public void setPassword(String password){
		this.password = password;
	}
	
	public String getEmail(){
		return this.email;
	}
	
	public void setEmail(String email){
		this.email = email;
	}
	

	/*
	public boolean getGender(){
		return this.gender;
	}
	
	public void setGender(boolean gender){
		this.gender = gender;
	}
	
	public int getAge(){
		return this.age;
	}
	
	public void setAge(int age){
		this.age = age;
	}
	*/
	@Override
	public String toString(){
		return this.username + " : "+ this.email;
	}
	
	@Override
	public int hashCode(){
		return Objects.hashCode(this.getUserId());
	}
}