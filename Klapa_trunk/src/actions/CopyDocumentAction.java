package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import tree.document.Document;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class CopyDocumentAction extends MyAbstractAction{

	private static final long serialVersionUID = -8254711893886436674L;

	public CopyDocumentAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_C, ActionEvent.CTRL_MASK));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();	
		Document content = (Document) view.getLastSelectedPathComponent();
		
		MainFrame.getInstance().getClipboard().setContents(content, MainFrame.getInstance());
	}

}
