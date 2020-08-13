package view.dialog;

import javax.swing.JOptionPane;

import view.frame.MainFrame;

public class LastProjectWarning {

private boolean state = false;
	
	public LastProjectWarning () {
		Object options[] = {"U redu","Obrisi sve"};
		int option = JOptionPane.showOptionDialog(MainFrame.getInstance(),
				"Projekat koji zelite da obrisete je poslednji projekat u Workspace-u. "
				+ "Ukoliko zelite da premestite njegov sadrzaj napravite novi projekat pre nego sto nastavite.",
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
