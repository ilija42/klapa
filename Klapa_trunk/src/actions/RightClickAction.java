 package actions;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

import javax.swing.SwingUtilities;
import javax.swing.tree.TreePath;

import observer.MyObservableNode;
import tree.document.Document;
import tree.project.Project;
import tree.workspace.WorkSpaceTree;
import view.components.RightClickDocumentMenu;
import view.components.RightClickMenu;
import view.components.RightClickProjectMenu;
import view.frame.MainFrame;

public class RightClickAction extends MouseAdapter {
	
	private static MainFrame mf = MainFrame.getInstance();
	
	
	public void mousePressed(MouseEvent e) {
		
        if (e.isPopupTrigger())
            doPop(e);
        
        WorkSpaceTree wsTree = (WorkSpaceTree) mf.getDatabaseView();
        
        if (SwingUtilities.isRightMouseButton(e)) {
        	TreePath selPath = wsTree.getClosestPathForLocation(e.getX(), e.getY());
        	if (! selPath.equals(wsTree.getSelectionPath())) {
        		wsTree.clearSelection();
            	wsTree.addSelectionPath(selPath); 
        	}
        }
    }
	
    public void mouseReleased(MouseEvent e) {
    	
		if (! e.isPopupTrigger()) return;

    	WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();		
		MyObservableNode node = (MyObservableNode) view.getLastSelectedPathComponent();
		
		if (node instanceof Document) {
			 doPopDocument(e);
			 return;
		}
	     
		if (node instanceof Project) {
			doPopProject(e);
			return;
		}
         
		doPop(e);
    }

    private void doPop(MouseEvent e) {
        RightClickMenu menu = new RightClickMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    private void doPopDocument(MouseEvent e) {
        RightClickDocumentMenu menu = new RightClickDocumentMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }
    
    private void doPopProject(MouseEvent e) {
    	RightClickProjectMenu menu = new RightClickProjectMenu();
        menu.show(e.getComponent(), e.getX(), e.getY());
    }

}
