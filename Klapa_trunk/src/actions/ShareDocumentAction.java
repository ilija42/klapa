package actions;

import java.awt.event.ActionEvent;

import observer.MyObservableNode;
import tree.document.Document;
import tree.workspace.WorkSpaceModel;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class ShareDocumentAction extends MyAbstractAction {

	private static final long serialVersionUID = 8579526933280450290L;

	@Override
	public void actionPerformed(ActionEvent e) {
		WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();	
		WorkSpaceModel model = (WorkSpaceModel)((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getModelInterface();
		MyObservableNode node = (MyObservableNode) view.getLastSelectedPathComponent();
		
		model.setSharedDocument((Document) node);
	}

}
