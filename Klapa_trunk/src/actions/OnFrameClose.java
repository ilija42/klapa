package actions;

import java.awt.Color;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import FileHandling.WorkSpaceSerializationHandler;
import tree.document.Document;
import tree.page.Page;
import tree.project.Project;
import view.dialog.SaveWorkspaceOption;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;

public class OnFrameClose implements WindowListener {

	@Override
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowClosed(WindowEvent e) {
			
	}

	@Override
	public void windowClosing(WindowEvent e) {
		
		SaveWorkspaceOption option = new SaveWorkspaceOption();
		if(option.getState() == 2) {
			WorkSpaceSerializationHandler.getInstance().save();
		}
		else if (option.getState() == 1) System.exit(0);				
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

}
