package actions;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import view.dialog.AboutDialog;

public class AboutAction implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		
		AboutDialog dialog = new AboutDialog();	
		dialog.setVisible(true);
		
	}

}