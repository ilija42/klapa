package actions;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import FileHandling.ProjectSerializationHandler;
import FileHandling.WorkSpaceSerializationHandler;
import errorHandling.ErrorHandler;
import tree.document.Document;
import tree.page.Page;
import tree.project.Project;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;

public class SaveFileAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 8442287389997847737L;

	public SaveFileAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("resources/save.png"));
		putValue(NAME, "Save");
		putValue(SHORT_DESCRIPTION, "Save");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		for(Project p: MainFrame.getInstance().getWorkSpace().getProjects()) {
			for(Document d: p.getDocuments()) {
				for(Page pg : d.getPages()) {
					for(GraphicDevice gd : pg.getSelectionModel().getSelectedElements()) {
						gd.setPaint(Color.WHITE);
					}
					pg.getSelectionModel().getSelectedElements().clear();
				}
			}
		}
		
	   	WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();		//ne moze da se dodeli ovo u konstruktoru jer za vreme poziva ovog konstruktora nije zavrsena inicijalizacija mainframe
		//DatabaseModelInterface model = ((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getModelInterface();
		 
		 if(view.getLastSelectedPathComponent() == null ) ErrorHandler.getInstance().NothingChosenException("cuvanje");
		
		 if(view.getLastSelectedPathComponent() instanceof Document ) {
			 ErrorHandler.getInstance().NothingChosenException("za brisanje");
		 }
		
		 if (view.getLastSelectedPathComponent() instanceof WorkSpace) {
			WorkSpaceSerializationHandler.getInstance().save();
		 }
		else if (view.getLastSelectedPathComponent() instanceof Project) {
			ProjectSerializationHandler.getInstance().save((Project) view.getLastSelectedPathComponent());
		}
					
		if (! view.isSelectionEmpty())
			view.expandRow(view.getSelectionRows()[0]);
	}
}
