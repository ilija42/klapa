package errorHandling;

import javax.swing.JOptionPane;

import observer.MyObservableInterface;
import observer.MyObserverInterface;
import view.frame.MainFrame;

public class ErrorHandlerView extends JOptionPane implements MyObserverInterface{

	private static final long serialVersionUID = 1L;
	
	@Override
	public void update(MyObservableInterface o, Object arg) {
			showMessageDialog(MainFrame.getInstance(), arg, "Error", JOptionPane.ERROR_MESSAGE);
	}
	
}
