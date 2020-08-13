package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import tree.page.PageSelectionModel;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class CopyElementAction extends MyAbstractAction {

	private static final long serialVersionUID = -5108635284501934439L;

	public CopyElementAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		PageSelectionModel selection = doc.getActivePageView().getPageModel().getSelectionModel();
		
		
		MainFrame.getInstance().getClipboard().setContents(selection, MainFrame.getInstance());
		
	}
}
