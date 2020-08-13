package observer;

import java.util.ArrayList;

public class MyObservableClass implements MyObservableInterface{
	private ArrayList <MyObserverInterface> observers = new ArrayList<>();
	boolean changed = false;
	
	public void addObserver(MyObserverInterface o) {
		observers.add(o);
	}
	
	public void deleteObserver(MyObserverInterface o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, null);
			}
			clearChanged();
		}
	}
	
	public void notifyObservers(Object arg) {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, arg);
			}
			clearChanged();
		}
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public void clearChanged() {
		changed = false;
	}

}
