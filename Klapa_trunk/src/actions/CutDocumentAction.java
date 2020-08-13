package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import tree.document.Document;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class CutDocumentAction extends MyAbstractAction{

	private static final long serialVersionUID = -8803395335850965891L;

	public CutDocumentAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_X, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();	
		Document content = (Document) view.getLastSelectedPathComponent();
		
		MainFrame.getInstance().getClipboard().setContents(content, MainFrame.getInstance());
		ActionManager.getInstance().getDeleteAction().actionPerformed(e);
	}

}
