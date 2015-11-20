package managerProtocol;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

import tool.Crypto;

public class SocketServ implements Runnable {
	
	private ServerSocket serversocket;
	private ClientPool pool;
	
	public SocketServ() {
		
		this.pool = new ClientPool();
		try {
			this.serversocket = new ServerSocket(5000,5); 
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void run() {
		
		while (!Thread.interrupted()) {
			
			try {
				
				Socket socket = this.serversocket.accept(); 
				
				BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
				String pseudo = in.readLine();
				
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				
				this.pool.addClient(socket,in,out, pseudo); // Envoie de l'utilisateur au gestionnaire de pool
				
				System.out.println(Crypto.decodeChaine(pseudo) + " s'est connecter");
				


			} catch (SocketException e) {
				if (e.getMessage() == "socket closed") {
					System.out.println("Socket fermer, tout est bon");
				}
			}			
			catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

	public void stop() {
		// TODO Auto-generated method stub 
		this.pool.flushClient();
		try {
			this.serversocket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
