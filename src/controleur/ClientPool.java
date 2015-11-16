package controleur;

import java.net.Socket;
import java.util.ArrayList;

public class ClientPool implements Runnable {

	private ArrayList<ClientThread> mesClientThread;

	public void addClient(Socket socket,String pseudo) {
		// TODO Auto-generated method stub
		mesClientThread.add(new ClientThread(socket,socket.getInetAddress(),pseudo));
		
		notifyUser(socket);
		notifyAllUsers(socket);
	}

	private void notifyAllUsers(Socket socket) {
		// TODO notifier tout les utilisateurs du nouveau user connecter
		
	}

	private void notifyUser(Socket socket) {
		// TODO notifier l'utilisateur de la réussite de sa connexion
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {
			
		}
	}

}
