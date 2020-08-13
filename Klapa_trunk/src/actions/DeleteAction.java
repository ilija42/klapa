package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;
import javax.swing.tree.MutableTreeNode;

import errorHandling.ErrorHandler;
import interfaces.DatabaseModelInterface;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class DeleteAction extends MyAbstractAction {

	private static final long serialVersionUID = 1L;

	public DeleteAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_D, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("resources/delete.png"));
		putValue(NAME, "Delete");
		putValue(SHORT_DESCRIPTION, "Delete");
		
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		
			WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();
			MutableTreeNode temp = ((MutableTreeNode)view.getLastSelectedPathComponent());
			
			if (view.getLastSelectedPathComponent() == null) ErrorHandler.getInstance().NothingChosenException("brisanje");
			else if(temp instanceof WorkSpace) ErrorHandler.getInstance().NotEraseableException("Workspace");

			else {
				DatabaseModelInterface model = ((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getModelInterface();
				model.removeNodeFromParent(temp);
			}
			 
		
		
	}
	
}
