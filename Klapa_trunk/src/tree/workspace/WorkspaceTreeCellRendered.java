package tree.workspace;

import java.awt.Component;
import java.net.URL;

import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellRenderer;

import tree.document.Document;
import tree.page.Page;
import tree.project.Project;


public class WorkspaceTreeCellRendered extends DefaultTreeCellRenderer {
	Object paint = null;
	private static final long serialVersionUID = 1L;
	public WorkspaceTreeCellRendered() {


	}
	
	@Override
	public Component getTreeCellRendererComponent(JTree tree, Object value, boolean sel, boolean expanded, boolean leaf,int row, boolean hasFocus) {
		super.getTreeCellRendererComponent(tree, value, sel, expanded, leaf, row, hasFocus);
		 
		if (value instanceof WorkSpace ) {
			URL imageURL = getClass().getResource("icons/Workspace.png");
            Icon icon = null;
            if (imageURL != null) {
            	icon = new ImageIcon(imageURL);
            }
                
            setIcon(icon);

        } else if (value instanceof Project ) {
        	URL  imageURL = getClass().getResource("icons/Project.png");
            Icon icon = null;
            if (imageURL != null) {
            	icon = new ImageIcon(imageURL);
            }
                
            setIcon(icon);
              
       } else if (value instanceof Document ) {
    	   URL  imageURL = getClass().getResource("icons/Document.png");
           Icon icon = null;
           if (imageURL != null)                       
               icon = new ImageIcon(imageURL);
           setIcon(icon);
        
       }else if (value instanceof Page ) {
    	   URL imageURL = getClass().getResource("icons/Page.png");
           Icon icon = null;
           if (imageURL != null)                       
               icon = new ImageIcon(imageURL);
           setIcon(icon);
           }
	return this;
	}
}