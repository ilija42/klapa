package actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.JTree;
import javax.swing.KeyStroke;

import FileHandling.ProjectSerializationHandler;
import interfaces.DatabaseModelInterface;
import tree.project.Project;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceModel;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class ImportFileAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 1L;

	public ImportFileAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		KeyEvent.VK_I, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, getIcon());
		putValue(NAME, "Import");
		putValue(SHORT_DESCRIPTION, "Import");
	}
	
	private static ImageIcon getIcon() {
		ImageIcon original = new ImageIcon("resources/import.png");
		Image image = original.getImage();
		Image newImage = image.getScaledInstance(32,  32, Image.SCALE_SMOOTH);
		original = new ImageIcon(newImage);
		return original;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		DatabaseModelInterface model = ((WorkSpaceTree) MainFrame.getInstance().getDatabaseView()).getModelInterface();
		Project project = (Project) ProjectSerializationHandler.getInstance().open();
		if (project == null) return;
		
	   	((WorkSpace)((WorkSpaceModel)model).getRoot()).addProject(project);
	   	((JTree) MainFrame.getInstance().getDatabaseView()).updateUI();
	   	((JTree) MainFrame.getInstance().getDatabaseView()).expandRow(0);
	}
	
}
