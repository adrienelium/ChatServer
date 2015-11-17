package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.*;

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
		
		while (true) {
			
			try {
				
				Socket socket = this.serversocket.accept(); 
				
				BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
				String pseudo = in.readLine();
				
				PrintWriter out = new PrintWriter(socket.getOutputStream());
				
				this.pool.addClient(socket,in,out, pseudo); // Envoie de l'utilisateur au gestionnaire de pool
				System.out.println("Connexion d'un utilisateur");
				


			}catch (IOException e) {
				e.printStackTrace();
			}
			
		}
		
	}

}
