package tree.workspace;

import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.EventObject;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.JTextField;
import javax.swing.JTree;
import javax.swing.tree.DefaultTreeCellEditor;
import javax.swing.tree.DefaultTreeCellRenderer;

import errorHandling.ErrorHandler;
import tree.document.Document;
import tree.page.Page;
import tree.project.Project;

public class WorkSpaceTreeEditor extends DefaultTreeCellEditor implements ActionListener{
	
	private Object stavka=null;
	private JTextField edit=null; 

	public WorkSpaceTreeEditor(JTree tree, DefaultTreeCellRenderer renderer) {
		super(tree, renderer);	
	}
	
	@Override
	public Component getTreeCellEditorComponent(JTree tree, Object value, boolean isSelected, boolean expanded,boolean leaf, int row) {
		 stavka = value;
		 super.getTreeCellEditorComponent(tree, value, isSelected, expanded, leaf, row);
		 edit = new JTextField(value.toString());
		 edit.addActionListener(this);
		 return edit; 
	}
	
	@Override
	public boolean isCellEditable(EventObject event) {
			if(event == null)return true;
		return false;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getActionCommand().isEmpty()) {
			ErrorHandler.getInstance().BadInputException("(prazno)");
			tree.clearSelection(); 

			return;
		}
			 
		Pattern pattern = Pattern.compile("[^A-Za-z0-9]");
		Matcher matcher = pattern.matcher(e.getActionCommand());
		boolean b = matcher.find();
		     
		if(b)	{
			ErrorHandler.getInstance().BadInputException("(zabranjen karakter)");
			tree.clearSelection(); 
			return;
		}
				
		if (stavka instanceof Project){
		 	((Project)stavka).setName(e.getActionCommand());
		}
		else if(stavka instanceof Page){
			((Page)stavka).setName(e.getActionCommand());
		} 
		else if(stavka instanceof Document){
			((Document)stavka).setName(e.getActionCommand());
		}
		tree.clearSelection(); 
		
		
	}
}
		

