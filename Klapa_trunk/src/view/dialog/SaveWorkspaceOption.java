package view.dialog;

import javax.swing.JOptionPane;

import view.frame.MainFrame;

public class SaveWorkspaceOption {
	private int state = 0;
	
	public SaveWorkspaceOption () {
		Object options[] = {"Save and Close","Close", "Cancel"};
		int option = JOptionPane.showOptionDialog(MainFrame.getInstance(),
				"Da li zelite da sacuvate vas Workspace? ",
				"", 
				JOptionPane.YES_NO_CANCEL_OPTION,
				JOptionPane.QUESTION_MESSAGE,
				null,
				options,
				options[1]);
		if(option == JOptionPane.YES_OPTION) state = 2;
		else if(option == JOptionPane.NO_OPTION) state = 1;
	}

	public int getState() {
		return state;
	}
	
}
