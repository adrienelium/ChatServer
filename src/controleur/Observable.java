package controleur;

public interface Observable {
	public void addObservateur(Observateur obs);
	public void notifyAllObservateur();
}
