package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import errorHandling.ErrorHandler;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class PaintRectangleAction extends MyAbstractAction{

	private static final long serialVersionUID = 559202662478959413L;

	public PaintRectangleAction() {
		putValue(ACCELERATOR_KEY, KeyStroke.getKeyStroke( KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("resources/rectangle.png"));
		putValue(SHORT_DESCRIPTION, "Rectangle");
	}

	public void actionPerformed(ActionEvent arg0) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}

		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setRectangleState();
	}
}
