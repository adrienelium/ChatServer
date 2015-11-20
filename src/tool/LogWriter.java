package tool;

import java.io.FileWriter;
import java.io.IOException;

public class LogWriter {
	
	private String nomfichier;
	private FileWriter fw;
	
	private static LogWriter INSTANCE = new LogWriter();
	
	private LogWriter() {
		this.nomfichier = "log.txt";
		
		try {
			this.fw = new FileWriter(this.nomfichier);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public static LogWriter getInstance() {
		return INSTANCE;
	}
	
	public void writeMessage(String str) {
		// TODO : rajouter une ligne sur le fichier de log
		System.err.println("Ecriture sur log.txt");
		try {
			fw.write(str + "\r\n");
			fw.flush();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
