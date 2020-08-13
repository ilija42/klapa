package actions;

import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.ImageIcon;
import javax.swing.KeyStroke;

import FileHandling.WorkSpaceSerializationHandler;
import view.dialog.SaveWorkspaceOption;
import view.frame.MainFrame;

public class SwitchAction extends MyAbstractAction{ 
	private static final long serialVersionUID = 1L;

	public SwitchAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		KeyEvent.VK_W, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, getIcon());
		putValue(NAME, "Switch");
		putValue(SHORT_DESCRIPTION, "Switch Workspace");
	}
	
	private static ImageIcon getIcon() {
		ImageIcon original = new ImageIcon("resources/switch.png");
		Image image = original.getImage();
		Image newImage = image.getScaledInstance(32,  32, Image.SCALE_SMOOTH);
		original = new ImageIcon(newImage);
		return original;
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		SaveWorkspaceOption option = new SaveWorkspaceOption();
		if (option.getState() == 2) WorkSpaceSerializationHandler.getInstance().save();
		
		MainFrame.getInstance().initializeWorkspace();

	}
}
