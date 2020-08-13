	package tree.workspace;

import javax.swing.JTree;
import javax.swing.SwingUtilities;
import javax.swing.tree.DefaultTreeCellRenderer;
import javax.swing.tree.MutableTreeNode;

import interfaces.DatabaseModelInterface;
import interfaces.DatabaseViewInterface;
import observer.MyObservableInterface;
import view.frame.MainFrame;

public class WorkSpaceTree extends JTree implements DatabaseViewInterface{
	
	private static final long serialVersionUID = 1L;
	
	
	public WorkSpaceTree() {
		
		setCellEditor(new WorkSpaceTreeEditor(this,new DefaultTreeCellRenderer()));
		addTreeSelectionListener(new WorkSpaceTreeController()); 
		setCellRenderer(new WorkspaceTreeCellRendered());
	    setEditable(true);
	    addMouseListener(MainFrame.getInstance().getActionManager().getRightClickAction());
	    addMouseListener(MainFrame.getInstance().getActionManager().getDoubleClickAction());
	}

	public void update(){
		  SwingUtilities.updateComponentTreeUI(this);
	}

	@Override
	public void update(MyObservableInterface o, Object arg) {
		update();
		
		if (arg instanceof MutableTreeNode)
			clearSelection();

	}

	@Override
	public DatabaseModelInterface getModelInterface() {
		return (DatabaseModelInterface) this.getModel();
	}
	
	
}
