package interfaces;

import observer.MyObserverInterface;

public interface DatabaseViewInterface extends MyObserverInterface{
	public void update();
	public DatabaseModelInterface getModelInterface();
}
