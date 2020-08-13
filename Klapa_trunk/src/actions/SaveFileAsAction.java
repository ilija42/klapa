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

public class SaveFileAsAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 8752889924949451500L;
	
	public SaveFileAsAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		KeyEvent.VK_S, ActionEvent.SHIFT_MASK));
		putValue(SMALL_ICON, loadIcon("resources/saveAs.png"));
		putValue(NAME, "Save As");
		putValue(SHORT_DESCRIPTION, "Save As");
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
			 ErrorHandler.getInstance().NothingChosenException("Niste izabrali validnu datoteku");
		 }
		 
		 if (view.getLastSelectedPathComponent() instanceof WorkSpace) {
			WorkSpaceSerializationHandler.getInstance().saveAs();
		 }
		else if (view.getLastSelectedPathComponent() instanceof Project) {
			ProjectSerializationHandler.getInstance().saveAs((Project) view.getLastSelectedPathComponent());
		}
					
		if (! view.isSelectionEmpty())
			view.expandRow(view.getSelectionRows()[0]);
	

	}
		}
