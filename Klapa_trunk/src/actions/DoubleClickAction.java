package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.beans.PropertyVetoException;

import javax.swing.tree.DefaultMutableTreeNode;

import tree.document.Document;
import tree.project.Project;
import tree.workspace.WorkSpaceTree;
import view.desktop.ProjectView;
import view.desktop.ProjectViewManager;
import view.frame.MainFrame;
public class DoubleClickAction extends MouseAdapter{
	
	public void mousePressed(MouseEvent e) {
		
		 DefaultMutableTreeNode selectedNode = 	(DefaultMutableTreeNode)((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getLastSelectedPathComponent();

				 if (e.getClickCount() == 2 && selectedNode instanceof Project) {
						
						 	ProjectView projectView; 	
						 	
						 	if (ProjectViewManager.getInstance().isProjectViewOpen((Project)selectedNode))
								return;
						 	
						 		
						 	projectView = new ProjectView(((Project) selectedNode));
						 	ProjectViewManager.getInstance().addProjectView(projectView);
						 	((Project) selectedNode).addObserver(projectView);
						 	try {
								projectView.setSelected(true);
							} catch (PropertyVetoException e1) {
								// TODO Auto-generated catch block
								e1.printStackTrace();
							}
				
				 }
				 
				 else if (e.getClickCount() == 2 && selectedNode instanceof Document) {
					
					 Project p = (Project)((Document)selectedNode).getParent();
					 ProjectView pv = ProjectViewManager.getInstance().getProjectView(p);
					 if (pv == null) {

							//JOptionPane.showMessageDialog(MainFrame.getInstance(), " Prvo otvorite projekat \"" + p.getName() + "\""); //smeta collapsu
							return;
					 }
					 if (pv.getDocumentViewManager().isDocumentViewOpen((Document)selectedNode)) {

						// JOptionPane.showMessageDialog(MainFrame.getInstance(), "Dokument je vec otvoren"); //smeta collapsu
						 return;
					 }
					 			 
					 pv.getDocumentViewManager().addDocumentView((Document)selectedNode);
					 
				 }
      
    }
 }
			


