package actions;

import java.awt.event.ActionEvent;

import observer.MyObservableNode;
import tree.document.Document;
import tree.project.Project;
import tree.workspace.WorkSpaceModel;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class ShareHereAction extends MyAbstractAction{

	private static final long serialVersionUID = 6396729159446677515L;

	@Override
	public void actionPerformed(ActionEvent e) {
		WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();	
		WorkSpaceModel model = (WorkSpaceModel)((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getModelInterface();
		Project project = (Project)(MyObservableNode) view.getLastSelectedPathComponent();
		Document document = model.getSharedDocument();
		
		if (document == null) return;
		project.addDocument(document);
		view.update();
		
	}
	
}
