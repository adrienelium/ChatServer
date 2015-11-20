package managerProtocol;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketServ serv = new SocketServ();
		
		Thread th = new Thread(serv);
		th.start();
		try {
			System.out.println(InetAddress.getLocalHost().getHostAddress());
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		System.out.println("Appuyer sur Entree pour fermer le server");
		try {
			if (System.in.read() > 0) {
				
				th.interrupt();
				serv.stop();
				
			}
		}
		catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
