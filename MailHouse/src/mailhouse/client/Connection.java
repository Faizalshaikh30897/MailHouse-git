package mailhouse.client;

import java.util.*;
import mailhouse.domain.*;
import java.net.*;
import java.io.*;

public class Connection{
	
	private int port;
	private InetAddress host;
	private Socket s = null;
	
	public Connection(int port, InetAddress host)throws IOException{
		
		this.port = port;
		this.host = host;
		createConnection();
	}
	
	public void createConnection()throws IOException{
		s = new Socket(host,port);
	}
	
	public InputStream getInputStream()throws IOException{
		return this.s.getInputStream();
	}
	
	public OutputStream getOutputStream() throws IOException{
		return this.s.getOutputStream();
	}
	
	public void closeConnection()throws IOException{
		s.close();
	}
	
	//public Client getClient(){
	//	this.s = Client(s.getInputStream(), s.getOutputStream());
	//	return this.client;
	//}
}