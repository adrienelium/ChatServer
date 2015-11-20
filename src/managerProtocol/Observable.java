package managerProtocol;

public interface Observable {
	public void addObservateur(Observateur obs);
	public void notifyAllObservateurs();
}
