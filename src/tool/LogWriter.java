package tool;

public class LogWriter {
	
	@SuppressWarnings("unused")
	private String nomfichier;
	private static LogWriter INSTANCE = new LogWriter();
	
	private LogWriter() {
		this.nomfichier = "log.txt";
	}
	
	public static LogWriter getInstance() {
		return INSTANCE;
	}
	
	public void writeMessage(String str) {
		// TODO : rajouter une ligne sur le fichier de log
	}
}
