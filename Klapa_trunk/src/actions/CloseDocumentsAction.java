package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import errorHandling.ErrorHandler;
import view.desktop.ProjectView;
import view.desktop.ProjectViewManager;


public class CloseDocumentsAction extends MyAbstractAction {

	private static final long serialVersionUID = 1L;
	
	public CloseDocumentsAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.ALT_MASK));
		putValue(SMALL_ICON, loadIcon("resources/closeAllDocuments.png"));
		putValue(NAME, "Close Documents");
		putValue(SHORT_DESCRIPTION, "Close all Documents");
	}
	@Override
	public void actionPerformed(ActionEvent e) {
			for(ProjectView pv : ProjectViewManager.getInstance().getAllProjectViews()) {
			if(pv.isSelected()) {
				pv.getDocumentViewManager().removeAll();
				pv.getDocumentViewManager().updateUI();
				return;
			}
		}
		ErrorHandler.getInstance().NothingChosenException("za brisanje svih tabova");
	}

}
