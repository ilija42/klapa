package view.dialog;

import javax.swing.JOptionPane;
import view.frame.MainFrame;

public class MoveDocumentsDialog {
	private boolean state = false;
	
	public MoveDocumentsDialog () {
		Object options[] = {"Premesti dokumente","Obrisi sve"};
		int option = JOptionPane.showOptionDialog(MainFrame.getInstance(),
				"Obrisani projekat nije prazan. Da li zelite da premestite sadrzaj u drugi projekat? ",
				"", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if(option == JOptionPane.YES_OPTION) state = true;
		else if(option == JOptionPane.NO_OPTION) state = false;
	}
	
	public boolean getState() {
		return state;
	}
	
}
