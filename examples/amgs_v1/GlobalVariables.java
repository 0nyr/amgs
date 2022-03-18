import java.util.Observable;

public class GlobalVariables extends Observable {
	
	private int currentPanel;	//indicates which panel to display at a given time
	
	public GlobalVariables (int currentPanel) {
		this.currentPanel = currentPanel;
	}
	
	public void setCurrentPanel(int a) {
		currentPanel = a;
		setChanged();
		notifyObservers();
	}
	
	public int getCurrentPanel() {
		return currentPanel;
	}
	
	
	
}
