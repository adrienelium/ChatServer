package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.*;

public class SocketServ implements Runnable {
	
	private ServerSocket serversocket;
	
	public SocketServ() {
		
		try {
			this.serversocket = new ServerSocket(2009,5); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while (true) {
			
			new Thread(new SocketListener(this.serversocket)).start();
			
		}
		
	}

}
