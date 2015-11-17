package controleur;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

public class ClientPool implements Observateur{

	private ArrayList<ClientThread> mesClientThread;

	private PrintWriter out;

	public void addClient(Socket socket,String pseudo) {
		// TODO Auto-generated method stub
		ClientThread cli = new ClientThread(socket,socket.getInetAddress(),pseudo);
		mesClientThread.add(cli);
		new Thread(cli).start();

		notifyUser(socket,pseudo);
		notifyAllUsers(socket,pseudo);
	}

	private void notifyAllUsers(Socket socket,String pseudo) {

		// TODO notifier tous les utilisateurs du nouveau user connecté

		for (ClientThread cli: mesClientThread) {

			try {

				if (!cli.getSocket().equals(socket)) {
					out = new PrintWriter(cli.getSocket().getOutputStream());
					out.println(pseudo + " s'est connecté !");
					out.flush();
				}


			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}


	}

	private void notifyUser(Socket socket,String pseudo) {
		// TODO notifier l'utilisateur de la réussite de sa connexion
		try {
			out = new PrintWriter(socket.getOutputStream());
			out.println("Bienvenue " + pseudo + ", amuse toi bien sur le chat IRC");
			out.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	private void dispatchNewMessage(String pseudo, String message) {
		System.err.println("[" + pseudo + "] : " + message);
		for (ClientThread cli: mesClientThread) {

			try {

				out = new PrintWriter(cli.getSocket().getOutputStream());
				out.println("[" + pseudo + "] : " + message);
				out.flush();

			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
	}

	@Override
	public void afficherNotification(ClientThread cli) {
		// TODO il faudra ajouter le dechiffrement des données recu, clientpool implemente donc l'interface cypher
		dispatchNewMessage(cli.getPseudo(),cli.getMessage());
		
	}

}
