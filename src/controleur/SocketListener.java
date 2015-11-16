package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;
import java.net.Socket;

public class SocketListener implements Runnable{
	private ServerSocket socketserver;
	private ClientPool pool;

	
	public SocketListener(ServerSocket socket) {
		this.socketserver = socket;
		this.pool = new ClientPool();
	}
	
	@Override
	public void run() {
		// TODO Auto-generated method stub
		try {
			
			Socket socket = this.socketserver.accept(); 
			
			BufferedReader in = new BufferedReader (new InputStreamReader (socket.getInputStream()));
			
			String pseudo = in.readLine();
			this.pool.addClient(socket, pseudo);
			System.out.println("Connexion d'un utilisateur");
			


		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
}
