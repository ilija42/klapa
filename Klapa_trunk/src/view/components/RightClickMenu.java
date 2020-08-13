package view.components;

import javax.swing.JMenuItem;
import javax.swing.JPopupMenu;

import view.frame.MainFrame;

public class RightClickMenu extends JPopupMenu{
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem newMenuItem = new JMenuItem("New");
	private JMenuItem deleteMenuItem = new JMenuItem("Delete");
	private JMenuItem renameMenuItem = new JMenuItem("Rename");

	public RightClickMenu() {
		
		newMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getNewAction());
		deleteMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getDeleteAction());
		renameMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getRenameAction());
		
		add(newMenuItem);
		add(deleteMenuItem);
		add(renameMenuItem);
	}
    
}
