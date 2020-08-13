package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import errorHandling.ErrorHandler;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintSelection extends MyAbstractAction{

	private static final long serialVersionUID = -9111263041948601200L;

	public PaintSelection() {
		putValue(SMALL_ICON, loadIcon("resources/select.png"));
		putValue(SHORT_DESCRIPTION, "Select");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setSelectState();
	}
}
