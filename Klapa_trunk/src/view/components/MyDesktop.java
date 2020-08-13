package view.components;

import java.awt.Component;

import javax.swing.JDesktopPane;

import observer.MyObservableInterface;
import observer.MyObserverInterface;
import view.desktop.ProjectView;
import view.desktop.ProjectViewManager;

public class MyDesktop extends JDesktopPane implements MyObserverInterface{

	private static final long serialVersionUID = 1L;

	@Override
	public void update(MyObservableInterface o, Object arg) {
		
		if (((ProjectViewManager)o).contains((ProjectView)arg) ) {
			this.add((Component)arg);
		}
			
		else {
			((ProjectView)arg).setVisible(false);
			this.remove(((ProjectView)arg));
		}
			
		
	}

}
