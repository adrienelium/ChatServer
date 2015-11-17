package controleur;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.InetAddress;
import java.net.Socket;
import java.util.ArrayList;

public class ClientThread implements Runnable,Observable{
		private Socket socket;
		private String pseudo;
		private String ip;
		private String dernierMessage;
		
		private BufferedReader in;
		private PrintWriter out;
		
		private ArrayList<Observateur> mesObservateur;
		
		public ClientThread(Socket socket2,BufferedReader in,PrintWriter out, InetAddress inetAddress, String pseudo2) {
			this.in = in;
			this.out = out;
			this.ip = inetAddress.getHostAddress();
			this.socket = socket2;
			this.pseudo = pseudo2;
			mesObservateur = new ArrayList<Observateur>();
		}
		/**
		 * @return the socket
		 */
		public Socket getSocket() {
			return socket;
		}
		/**
		 * @return the pseudo
		 */
		public String getPseudo() {
			return pseudo;
		}
		/**
		 * @return the ip
		 */
		public String getIp() {
			return ip;
		}
		/**
		 * @param socket the socket to set
		 */
		public void setSocket(Socket socket) {
			this.socket = socket;
		}
		/**
		 * @param pseudo the pseudo to set
		 */
		public void setPseudo(String pseudo) {
			this.pseudo = pseudo;
		}
		/**
		 * @param ip the ip to set
		 */
		public void setIp(String ip) {
			this.ip = ip;
		}
		
		public String getMessage() {
			// TODO Auto-generated method stub
			return dernierMessage;
		}
		
		public void setMessaghe(String message) {
			this.dernierMessage = message;
		}
		
		@Override
		public void run() {
			// TODO Ecoute les messages envoyer par l'utilisateur et notifie l'observateur pool client
			boolean var = true;
			while (var) {
				try {
					in = new BufferedReader(new InputStreamReader(this.socket.getInputStream()));
					String messageDistant = in.readLine();
					
					//System.err.println(messageDistant);
					
					this.dernierMessage = messageDistant;
					notifyAllObservateur();
					
				} catch (IOException e) {
					// TODO Auto-generated catch block
					var = false;
					System.err.println(this.pseudo + " s'est déconnecté !");
					//e.printStackTrace();
				}
			}
			
		}
		@Override
		public void addObservateur(Observateur obs) {
			// TODO Auto-generated method stub
			mesObservateur.add(obs);
			
		}
		@Override
		public void notifyAllObservateur() {
			// TODO Auto-generated method stub
			for (Observateur obs : mesObservateur) {
				obs.afficherNotification(this);
			}
			
		}
		public void writeAll(String string) {
			// TODO Auto-generated method stub
			out.println(string);
			out.flush();
		}
		public void bienvenue() {
			// TODO Auto-generated method stub
			out.println("Bienvenue " + pseudo + ", amuse toi bien sur le chat IRC");
			out.flush();

		}
}
