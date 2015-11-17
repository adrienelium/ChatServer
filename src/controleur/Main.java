package controleur;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		SocketServ serv = new SocketServ();
		
		new Thread(serv).start();
	}

}
