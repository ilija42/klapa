package view.components;

import javax.swing.Box;
import javax.swing.JToolBar;

import view.frame.MainFrame;

public class Toolbar extends JToolBar{

	private static final long serialVersionUID = 1L;

	public Toolbar() {
		
		add(Box.createHorizontalStrut(10));
		
		/*JButton btnEdit = new JButton(new ImageIcon("resources/edit.png"));
	
		btnEdit.setToolTipText("Edit");
		btnEdit.setPreferredSize(new Dimension(40,40));		
		btnEdit.setFocusable(false);
		 		ne treba nam jer imamo triple click rename	*/
		
		add(MainFrame.getInstance().getActionManager().getNewAction()); 
		add(Box.createHorizontalStrut(10));
		add(MainFrame.getInstance().getActionManager().getDeleteAction());
		add(Box.createHorizontalStrut(10));
		add(MainFrame.getInstance().getActionManager().getImportFileAction());
		add(Box.createHorizontalStrut(10));
		add(MainFrame.getInstance().getActionManager().getSwitchAction());
		/*add(Box.createHorizontalStrut(49));
		add(btnEdit);*/
		add(Box.createHorizontalStrut(49));
		add(MainFrame.getInstance().getActionManager().getCloseDocumentAction());
		add(Box.createHorizontalStrut(10));
		add(MainFrame.getInstance().getActionManager().getEditElementAction());
		setFloatable(false);
	
	}
}
