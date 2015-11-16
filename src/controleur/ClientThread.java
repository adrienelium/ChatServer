package controleur;

import java.net.InetAddress;
import java.net.Socket;

public class ClientThread {
		private Socket socket;
		private String pseudo;
		private String ip;
		
		public ClientThread(Socket socket2, InetAddress inetAddress, String pseudo2) {
			this.ip = inetAddress.getHostAddress();
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
}
