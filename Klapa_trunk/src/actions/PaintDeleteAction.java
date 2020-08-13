package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import errorHandling.ErrorHandler;
import view.components.PaintToolBar;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintDeleteAction extends MyAbstractAction {

	private static final long serialVersionUID = 1L;

	public PaintDeleteAction() {
		putValue(SMALL_ICON, loadIcon("resources/deleteElement.png"));
		putValue(SHORT_DESCRIPTION, "Delete");
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setDeleteState();
		((PaintToolBar) MainFrame.getInstance().getPaintBar()).selectButton();
	}
}
