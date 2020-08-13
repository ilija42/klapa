package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import tree.page.PageSelectionModel;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class CutElementAction extends MyAbstractAction {
	
	private static final long serialVersionUID = 8957551768718752820L;

	public CutElementAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		PageSelectionModel selection = doc.getActivePageView().getPageModel().getSelectionModel();
		
		
		MainFrame.getInstance().getClipboard().setContents(selection, MainFrame.getInstance());
		doc.getStateManager().setDeleteState();
		
	}

}
