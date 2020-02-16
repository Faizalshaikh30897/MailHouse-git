package mailhouse.server;

import java.util.*;
import java.net.*;
import java.io.*;
import java.util.concurrent.*;

public class MailServer {
	
	private ServerSocket server;
	
	public MailServer(int port) throws IOException{
		this.server = new ServerSocket(port);
	}
	
	public void run(){
		try{
			ExecutorService ex = Executors.newFixedThreadPool(5);
			while(true){
				Socket s1 = this.server.accept();
				
				Runnable r = new Handler(s1.getInputStream(),s1.getOutputStream());
				ex.submit(r);
			}
		}
		catch(IOException e){
			System.out.println(e.getMessage());
		}
	}
	
	public static void main(String[] args){
		try{
			MailServer ms = new MailServer(9999);
			ms.run();
		}
		catch(Exception e){
			
			System.out.println("Exception: "+e.getMessage());
		}
		
	}
}