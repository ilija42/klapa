package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import errorHandling.ErrorHandler;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintRotateAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 1314403211048108936L;

	public PaintRotateAction() {
		putValue(SMALL_ICON, loadIcon("resources/rotate.png"));
		putValue(SHORT_DESCRIPTION, "Rotate");
	}
	
	public void actionPerformed(ActionEvent arg0) {
		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setRotateState();
	}
}
