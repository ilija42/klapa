package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintTriangleAction extends MyAbstractAction{
	
	private static final long serialVersionUID = -8596846138215145278L;

	public PaintTriangleAction() {
		putValue(SMALL_ICON, loadIcon("resources/triangle.png"));		
		putValue(SHORT_DESCRIPTION, "Triangle");
	}

	public void actionPerformed(ActionEvent arg0) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setTriangleState();
	}
}
