package controleur;

import java.io.BufferedReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.ArrayList;

import tool.Crypto;
import tool.LogWriter;

public class ClientPool implements Observateur{

	private ArrayList<ClientThread> mesClientThread;
	
	public ClientPool() {
		mesClientThread = new ArrayList<ClientThread>();
	}

	public void addClient(Socket socket,BufferedReader in, PrintWriter out,String pseudo) {
		
		ClientThread cli = new ClientThread(socket,in,out,socket.getInetAddress(),pseudo);
		cli.addObservateur(this);
		
		String str = Crypto.encodeChaine("Bienvenue " + Crypto.decodeChaine(pseudo) + ", amuse toi bien sur le chat IRC");
		cli.bienvenue(str);
		
		mesClientThread.add(cli);
		Thread th = new Thread(cli);
		th.start();
		cli.setThread(th);
		
		notifyAllUsers(socket,pseudo);
	}

	private void notifyAllUsers(Socket socket,String pseudo) {

		// TODO notifier tous les utilisateurs du nouveau user connecté
		writeLogFile(Crypto.decodeChaine(pseudo)," s'est connecter");
		for (ClientThread cli: mesClientThread) {
			//System.err.println("Notifier");
			
			String str = Crypto.encodeChaine(Crypto.decodeChaine(pseudo) + " s'est connecté !");
			cli.writeAll(str);

		}


	}
	
	
	private void notifyAllUsersDeco(String pseudo) {
		// TODO Auto-generated method stub
		for (ClientThread cli: mesClientThread) {
			//System.err.println("Notifier");
			
			String str = Crypto.encodeChaine(Crypto.decodeChaine(pseudo) + " s'est deconnecté !");
			cli.writeAll(str);

		}
	}

	private void dispatchNewMessage(String pseudo, String message) {
		//System.err.println("[" + pseudo + "] : " + message);

		for (ClientThread cli: mesClientThread) {
			String str = "[" + pseudo + "] : " + message;
			cli.writeAll(Crypto.encodeChaine(str));

		}
	}

	@Override
	public void afficherNotification(ClientThread cli) {

		
		if (!cli.isEtatUser()) {
			writeLogFile(Crypto.decodeChaine(cli.getPseudo())," s'est deconnecter");
			
			cli.getThread().interrupt();
			mesClientThread.remove(cli);
			notifyAllUsersDeco(cli.getPseudo());
			
			
		}
		else {
			writeLogFile(Crypto.decodeChaine(cli.getPseudo()),Crypto.decodeChaine(cli.getMessage()));
			dispatchNewMessage(Crypto.decodeChaine(cli.getPseudo()),Crypto.decodeChaine(cli.getMessage()));
		}
		
		
		
	}

	

	public void flushClient() {
		// TODO Auto-generated method stub
		for (ClientThread cli: mesClientThread) {

			cli.setEtatUser(false);
			cli.getThread().interrupt();

		}
		
	}
	
	private synchronized void writeLogFile(String pseudo,String message) {
		
		LogWriter.getInstance().writeMessage("[" + pseudo + "] : " + message);
		
	}

}
