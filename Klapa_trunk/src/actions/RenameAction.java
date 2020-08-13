package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTree;
import javax.swing.tree.MutableTreeNode;

import errorHandling.ErrorHandler;
import tree.workspace.WorkSpace;
import view.frame.MainFrame;

public class RenameAction extends MyAbstractAction {
	
	private static final long serialVersionUID = 1L;

	public RenameAction() {
		
		//putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_R, ActionEvent.CTRL_MASK));
		putValue(NAME, "Rename");
		putValue(SHORT_DESCRIPTION, "Rename");	
		
	}
	
	@Override
	public void actionPerformed(ActionEvent arg0) {
		
		JTree view = (JTree) MainFrame.getInstance().getDatabaseView();
		
		if (view.getLastSelectedPathComponent() == null) {
			ErrorHandler.getInstance().NothingChosenException("preimenovanje");
			return;
		}
			
		MutableTreeNode temp = ((MutableTreeNode)view.getLastSelectedPathComponent());
			
		if(temp instanceof WorkSpace) {
			ErrorHandler.getInstance().CantRenameException("Workspace");
			return;
		}
	
		view.startEditingAtPath(view.getSelectionPath());
	}

}
