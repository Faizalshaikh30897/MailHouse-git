package mailhouse.server;

import java.util.*;
import java.net.*;
import java.sql.*;
import mailhouse.domain.*;
import java.io.*;
import mailhouse.exceptions.*;
import java.time.*;


public class DataAccess{
	
	private String jdbcURL;
	private String driver;
	private String username;
	private String password;
	
	public DataAccess(String jdbcURL,String driver,String username,String password){
		this.jdbcURL = jdbcURL;
		this.driver = driver;
		this.username = username;
		this.password = password;
	}
	
	public User login(User user)throws ClassNotFoundException,UserNotFoundException{
		
		Class.forName(driver);
		
		
		Connection con = null;
		
		try{
			con = DriverManager.getConnection(jdbcURL,username,password);
			
			PreparedStatement ps = con.prepareStatement("select * from login where email = ?  and password = ?");
			ps.setString(1,user.getEmail());
			ps.setString(2,user.getPassword());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				con.close();
				return user;
			}
			else{
				con.close();
				throw new UserNotFoundException("invalid username or password");
			
			}
			
			
		}catch(SQLException e){
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			e.printStackTrace();
			return null;
		}
		
	}
	
	
	public User register(UserProfile userprofile,User user)throws ClassNotFoundException,UserNotFoundException{
		
		Class.forName(driver);
		
		
		Connection con = null;
		
		try{
			con = DriverManager.getConnection(jdbcURL,username,password);
			con.setAutoCommit(false);
			
			System.out.println("before first insert in user" + userprofile.getGender());
			PreparedStatement ps = con.prepareStatement("INSERT INTO public.login( email, password, username)	VALUES ( ?, ?, ?) RETURNING user_id");
			ps.setString(1,user.getEmail());
			ps.setString(2,user.getPassword());
			ps.setString(3,user.getUsername());
			
			ResultSet rs = ps.executeQuery();
			rs.next();
			user.setUserId(rs.getInt("user_id"));
			System.out.println("before second insert in userprofile" + rs.getInt("user_id"));
			PreparedStatement ps1 = con.prepareStatement("INSERT INTO public.user_profile( date_of_birth, gender, user_id)	VALUES (?, ?, ?)");
			
			ps1.setTimestamp(1,Timestamp.valueOf(userprofile.getDateOfBirth()));
			
			ps1.setBoolean(2,userprofile.getGender()); // 0=male 1=female
			//System.out.println("gender"+userprofile.getGender());
			ps1.setInt(3,user.getUserId());
			
			ps1.executeUpdate();
			System.out.println("register at dataaccess");
	
			con.commit();
			con.setAutoCommit(true);
			System.out.println("register at dataaccess");		
			return user;
		}catch(SQLException e){
			try{
				con.rollback();
				con.setAutoCommit(true);
			}
			catch(SQLException exp){}
			e.printStackTrace();
			return null;
		}
		finally{
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
		}
		
	}

	
	public User getUserByEmail(User user)throws ClassNotFoundException,UserNotFoundException{
		System.out.println("get user by email ");
		Class.forName(driver);
		Connection con = null;
		
		try{
			con = DriverManager.getConnection(jdbcURL,username,password);
			
			PreparedStatement ps = con.prepareStatement("select * from login where email = ?");
			ps.setString(1,user.getEmail());
			
			ResultSet rs = ps.executeQuery();
			
			if(rs.next()){	
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				con.close();
				return user;
			}
			else {
				con.close();
				throw new UserNotFoundException("given email does not exist");
			}
			
			
		}catch(SQLException e){
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			e.printStackTrace();
			return null;
		}
		
	}
	
	public User getUserById(int id)throws ClassNotFoundException,UserNotFoundException{
		
		Class.forName(driver);
		Connection con = null;
		
		try{
			con = DriverManager.getConnection(jdbcURL,username,password);
			
			PreparedStatement ps = con.prepareStatement("select * from login where user_id = ?");
			ps.setInt(1,id);
			
			ResultSet rs = ps.executeQuery();
			User user = new User();
			if(rs.next()){	
				user.setUserId(rs.getInt("user_id"));
				user.setUsername(rs.getString("username"));
				user.setPassword(rs.getString("password"));
				user.setPassword(rs.getString("email"));
				con.close();
				return user;
				
			}
			else {
				con.close();
				throw new UserNotFoundException("given email does not exist");
			}
			
			
		}catch(SQLException e){
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			e.printStackTrace();
			return null;
		}
		
	}
	
	public Mail storeMail(Mail mail)throws SQLException,UserNotFoundException,ClassNotFoundException,IOException{
		//System.out.println("mail insert" + in);
		Class.forName(driver);
		Connection con = null;
		
		try{
			
			con = DriverManager.getConnection(jdbcURL,username,password);
			con.setAutoCommit(false);
			User fromUser = this.getUserByEmail(mail.getFromUser());
			User toUser = this.getUserByEmail(mail.getToUser());
			
			
			
			PreparedStatement ps = con.prepareStatement("insert into mail(from_user,to_user,subject,content,time) values(?,?,?,?,?) returning mail_id");
			ps.setInt(1,fromUser.getUserId());
			ps.setInt(2,toUser.getUserId());
			ps.setString(3,mail.getSubject());
			ps.setString(4,mail.getContent());
			ps.setTimestamp(5,Timestamp.valueOf(LocalDateTime.now()));
			
			ResultSet rs = ps.executeQuery();
			
			rs.next();
			int mailId = rs.getInt("mail_id");
			List<Attachment> attachments = mail.getAttachment();
			mail.setMailId(mailId);
			System.out.println("mail insert " + attachments.size());
			if(attachments.size()>0){
				System.out.println("attachment before insert");
				
				for(int i = 0; i < attachments.size();i++){
					//DataInputStream dis = new DataInputStream(new BufferedInputStream(in));
					//int filesize = dis.readInt();
					//byte[] fileContent = new byte[filesize]; 
					//dis.readFully(fileContent, 0, fileContent.length);
					System.out.println(attachments.get(i).getFilesize());
					
					
					ps = con.prepareStatement("insert into attachments(mail_id,filename,extension,file) values(?,?,?,?)");
					ps.setInt(1,mailId);
					ps.setString(2,attachments.get(i).getFileName());
					ps.setString(3,attachments.get(i).getExtension());
					ps.setBytes(4,attachments.get(i).getData());
					
					ps.executeUpdate();
					System.out.println("attachment after insert");
				}
			}
			
			con.commit();
			con.setAutoCommit(true);
			con.close();
			return mail;
			
		}catch(SQLException e){
			System.out.println("dataaccess catch");
			con.setAutoCommit(true);
			con.rollback();
			e.printStackTrace();
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			return mail;
		}
	}
	
	
	
	public List<SubMail> getMails(User user)throws UserNotFoundException,ClassNotFoundException{
		
		Class.forName(driver);
		Connection con = null;
		
		try{
			
			con = DriverManager.getConnection(jdbcURL,username,password);
			//user = this.getUserByEmail(mail.getFromUser());
			PreparedStatement ps = con.prepareStatement("select * from mail where to_user = ?");
			ps.setInt(1,user.getUserId());
			
			ResultSet rs = ps.executeQuery();
			List<SubMail> mails = new ArrayList<>();
			
			while(rs.next()){	
				User fromUser = this.getUserById(rs.getInt("to_user"));	
				SubMail submail = new SubMail(rs.getInt("mail_id"),fromUser,rs.getString("subject"),rs.getTimestamp("time").toLocalDateTime());
				mails.add(submail);
			}
			con.close();
			return mails;
		}catch(SQLException e){
			System.out.println("dataaccess catch");
			
			e.printStackTrace();
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			return null;
		}
	}
	
	public Mail getMail(Mail mail)throws UserNotFoundException,ClassNotFoundException,MailNotFoundException{
		Class.forName(driver);
		Connection con = null;
		
		try{
			
			con = DriverManager.getConnection(jdbcURL,username,password);
			//user = this.getUserByEmail(mail.getFromUser());
			PreparedStatement ps = con.prepareStatement("select * from mail where mail_id = ?");
			ps.setInt(1,mail.getMailId());
		
			ResultSet rs = ps.executeQuery();
			if(rs.next()){
				mail.setFromUser(this.getUserById(rs.getInt("from_user")));
				mail.setToUser(this.getUserById(rs.getInt("to_user")));
				mail.setContent(rs.getString("content"));
				mail.setSubject(rs.getString("subject"));
				mail.setDateTime(rs.getTimestamp("time").toLocalDateTime());
				
				ps = con.prepareStatement("select attachment_id ,mail_id, filename,extension from attachments where mail_id = ?");
				ps.setInt(1,mail.getMailId());
				List<Attachment> attachments = new ArrayList<>();
				rs = ps.executeQuery();
				while(rs.next()){
					Attachment a = new Attachment();
					a.setAttachmentId(rs.getInt("attachment_id"));
					a.setFileName(rs.getString("filename"));
					a.setExtension(rs.getString("extension"));
					attachments.add(a);
				}
				mail.setAttachment(attachments);
				con.close();
				return mail;
			}
			else{
				con.close();
				throw new MailNotFoundException("mail doesnot exist");
			}
			
		
		}catch(SQLException e){
			System.out.println("dataaccess catch");
			
			e.printStackTrace();
			try{
			con.close();
			}
			catch(SQLException exp){
					
			}	
			return null;
		}
	}
	
}