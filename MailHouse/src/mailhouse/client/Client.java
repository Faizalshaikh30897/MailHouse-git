package mailhouse.client;

import java.util.*;
import mailhouse.domain.*;
import java.net.*;
import mailhouse.packets.*;
import java.io.*;
import java.nio.file.*;
import java.time.*;

public class Client{
	
	private List<SubMail> mails;
	private User user;
	private Mail currentMail;
	private InputStream in;
	private OutputStream out;
	private Connection conn;
	
	public Client(InetAddress host,int port)throws IOException{
		conn = new Connection(port,host);
		
		
		this.in = conn.getInputStream();
		this.out = conn.getOutputStream();
	}
	
	public User login(String email, String password){
		user = new User();
		user.setEmail(email);
		user.setPassword(password);
		
		LoginPacket login = new LoginPacket(user);
		try{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			oos.writeObject(login);
			
			Packet returnPacket = (Packet) ois.readObject();
			
			if(returnPacket.getPacketType() == PacketTypes.ErrorPacket){
				System.out.println("invalid username or password");
				return null;//convert to throw?
			}
			else {
				return ((LoginPacket)returnPacket).getUser();
			}
		}
		catch(IOException|ClassNotFoundException e){
				e.printStackTrace();
				return null;
		}
		
	}
	
	public User register(User user, UserProfile userprofile){
		
		
		
		ProfilePacket profile = new ProfilePacket(user,userprofile);
		try{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			oos.writeObject(profile);
			
			Packet returnPacket = (Packet) ois.readObject();
			
			if(returnPacket.getPacketType() == PacketTypes.ErrorPacket){
				System.out.println("invalid username or password");
				return null;//convert to throw?
			}
			else {
				return ((ProfilePacket)returnPacket).getUser();
			}
		}
		catch(IOException|ClassNotFoundException e){
				e.printStackTrace();
				return null;
		}
	}
	
	public List<SubMail> getMails(User user){
		GetMailListPacket p = new GetMailListPacket(new ArrayList<SubMail>(),user);
		try{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			oos.writeObject(p);
			
			Packet returnPacket = (Packet) ois.readObject();
			
			if(returnPacket.getPacketType() == PacketTypes.ErrorPacket){
				System.out.println(((ErrorPacket)returnPacket).getMessage());
				return null;//convert to throw?
			}
			
			else {
				return ((GetMailListPacket)returnPacket).getMailList();
			}
		}
		catch(IOException|ClassNotFoundException e){
				e.printStackTrace();
				return null;
		}
	}
	
	public Mail getMail(Mail mail){
		GetMailPacket p = new GetMailPacket(mail);
		try{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			oos.writeObject(p);
			
			Packet returnPacket = (Packet) ois.readObject();
			
			if(returnPacket.getPacketType() == PacketTypes.ErrorPacket){
				System.out.println(((ErrorPacket)returnPacket).getMessage());
				return null;//convert to throw?
			}
			
			else {
				this.currentMail = ((GetMailPacket)returnPacket).getMail();
				return this.currentMail;
			}
		}
		catch(IOException|ClassNotFoundException e){
				e.printStackTrace();
				return null;
		}
	}
	
	public boolean sendMail(Mail mail)throws ClassNotFoundException{
		
		try{
			ObjectOutputStream oos = new ObjectOutputStream(out);
			ObjectInputStream ois = new ObjectInputStream(in);
			
			List<Attachment> attachments = mail.getAttachment();
			
			if(attachments.size() > 0){
				//int n;
				//byte[] = new byte[4092];
				//byte[] done = new byte[3];
				//String str = "done";
				//done = str.getBytes();
				
				
				for(int i=0;i<attachments.size();i++){
					//DataOutputStream dos = new DataOutputStream(new BufferedOutputStream(this.out));
					File file = attachments.get(i).getFile();
					//FileInputStream fis = new FileInputStream(file);
					
					byte[] fileContent = Files.readAllBytes(file.toPath());
					
					attachments.get(i).setData(fileContent);
					attachments.get(i).setFilesize(fileContent.length);
					System.out.println(attachments.get(i).getFilesize());
					System.out.println("attachment before send");
					//dos.writeInt(fileContent.length);
					//dos.write(fileContent);
					//oos.writeObject(attachments.get(i));
					
					System.out.println("attachment after send");
					
					
				}
			}
			
			SendMailPacket smpacket = new SendMailPacket(mail);
			oos.writeObject(smpacket);
			oos.flush();
			return true;
		}
		catch(IOException e){
				e.printStackTrace();
				return false;
		}
		
	}
	
	public void logout(){
		try{
			//logout on server
			in.close();
			out.close();
			conn.closeConnection();
		}
		catch(IOException e){
			
		}
	}
	
	public static void main(String[] args){
		try{
			InetAddress addr = InetAddress.getByName("127.0.0.1");
			Client c = new Client(addr,9999);
			//User user = c.login("abc@mailhouse.com","abc");
			User user = new User();
			user.setUsername("pqr");
			user.setPassword("password");
			user.setEmail("pqr@mailhouse.com");
			UserProfile userprofile= new UserProfile(false,LocalDateTime.now());
			User userprofiletest= c.register(user,userprofile);
			/*
			Mail mail = new Mail();
			User fuser = new User();
			User tuser = new User();
			fuser.setEmail("abc@mailhouse.com");
			tuser.setEmail("xyz@mailhouse.com");
			mail.setSubject("subject of email to xyz");
			mail.setFromUser(fuser);
			mail.setToUser(tuser);
			mail.setText("content to xyz");
			mail.setDateTime(LocalDateTime.now());
			
			Attachment a = new Attachment();
			a.setFileName("MEPnewmodule");
			a.setExtension(".txt");
			a.setFile(new File("C:\\Users\\faiza\\Desktop\\MEPnewmodule.txt"));
			
			Attachment b = new Attachment();
			b.setFileName("MEP_imp");
			b.setExtension(".txt");
			b.setFile(new File("C:\\Users\\faiza\\Desktop\\MEP_imp.txt"));
		
			mail.addAttachment(a);
			mail.addAttachment(b);
			
			c.sendMail(mail);
			*/
			
			
			List<SubMail> mails = c.getMails(user);
			if(mails!=null){
				for(SubMail m : mails){
					System.out.println(m.getSubject() + " timestamp: "+ m.getDateTime());
					
				}
			}
			
			Mail mail = new Mail();
			mail.setMailId(16);
			mail = c.getMail(mail);
			System.out.println("subject: "+ mail.getSubject()+ "content: "+ mail.getContent());
			for(Attachment at : mail.getAttachment()){
				System.out.println("filename: "+ at.getFileName()+at.getExtension());
			}
			while(true){
				
			}
			//if(user ==null){
			//	System.out.println("Error");
			//}
			//else{
			//	System.out.println(user.getUsername());
			//}
		}
		catch(Exception e){
			e.printStackTrace();
		}
	}
}