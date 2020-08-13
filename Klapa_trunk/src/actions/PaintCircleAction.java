package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintCircleAction extends MyAbstractAction{
	
	private static final long serialVersionUID = -7154274139091261399L;

	public PaintCircleAction() {
		putValue(SMALL_ICON, loadIcon("resources/circle.png"));
		putValue(SHORT_DESCRIPTION, "Circle");
	}

	public void actionPerformed(ActionEvent arg0) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setCircleState();
	}
}
