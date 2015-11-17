package controleur;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientPool implements Observateur{

	private ArrayList<ClientThread> mesClientThread;
	
	public ClientPool() {
		mesClientThread = new ArrayList<ClientThread>();
	}

	public void addClient(Socket socket,BufferedReader in, PrintWriter out,String pseudo) {
		
		ClientThread cli = new ClientThread(socket,in,out,socket.getInetAddress(),pseudo);
		cli.addObservateur(this);
		cli.bienvenue();
		
		mesClientThread.add(cli);
		new Thread(cli).start();
		
		notifyAllUsers(socket,pseudo);
	}

	private void notifyAllUsers(Socket socket,String pseudo) {

		// TODO notifier tous les utilisateurs du nouveau user connecté

		for (ClientThread cli: mesClientThread) {
			
			cli.writeAll(pseudo + " s'est connecté !");

		}


	}

	private void dispatchNewMessage(String pseudo, String message) {
		System.err.println("[" + pseudo + "] : " + message);
		for (ClientThread cli: mesClientThread) {

			cli.writeAll("[" + pseudo + "] : " + message);


		}
	}

	@Override
	public void afficherNotification(ClientThread cli) {
		// TODO il faudra ajouter le dechiffrement des données recu, clientpool implemente donc l'interface cypher
		dispatchNewMessage(cli.getPseudo(),cli.getMessage());
		
	}

}
