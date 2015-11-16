package controleur;

import java.net.ServerSocket;

public class ChatServer {

	private ServerSocket serverSocket;
	private ClientPool pool;
	/**
	 * @return the serverSocket
	 */
	public ServerSocket getServerSocket() {
		return serverSocket;
	}
	/**
	 * @return the pool
	 */
	public ClientPool getPool() {
		return pool;
	}
	/**
	 * @param serverSocket the serverSocket to set
	 */
	public void setServerSocket(ServerSocket serverSocket) {
		this.serverSocket = serverSocket;
	}
	/**
	 * @param pool the pool to set
	 */
	public void setPool(ClientPool pool) {
		this.pool = pool;
	}

}
