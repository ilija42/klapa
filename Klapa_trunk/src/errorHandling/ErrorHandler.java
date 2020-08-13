package errorHandling;

import java.util.ArrayList;

import observer.MyObservableInterface;
import observer.MyObserverInterface;

public class ErrorHandler implements MyObservableInterface {
	private ArrayList <MyObserverInterface> observers = new ArrayList<>();
	boolean changed = false;
	private static ErrorHandler instance = null;
	
	
	public static ErrorHandler getInstance() {
		if(instance == null) instance = new ErrorHandler();
		return instance;	
	}
	
	public void BadInputException(String errMsg) {
		setChanged();
		notifyObservers("Pogresan unos " + errMsg);
	}
	
	public void CantAddNewException() {
		setChanged();
		notifyObservers("Ne mozete dodati nista na stranicu");
	}

	public void CantRenameException(String errMsg) {
		setChanged();
		notifyObservers("Ne mozete preimenovati " + errMsg);
	}

	public void NotEraseableException(String errMsg) {
		setChanged();
		notifyObservers("Nemoguce je obrisati " + errMsg);
	}
	
	public void NothingChosenException(String errMsg) {
		setChanged();
		notifyObservers("Nije selektovano nista za " + errMsg);
	}
	
	@Override
	public void addObserver(MyObserverInterface o) {
			observers.add(o);
			//System.out.println(o);
	}

	@Override
	public void deleteObserver(MyObserverInterface o) {
		observers.remove(o);
		
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public void setChanged() {
		changed = true;
	}

	@Override
	public void clearChanged() {
		changed = false;
	}


	
	public void notifyObservers(Object arg) {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, arg);
			}
			clearChanged();
		}
	}

	@Override
	public void notifyObservers() {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, null);
			}
			clearChanged();
		}
		
	}
}
