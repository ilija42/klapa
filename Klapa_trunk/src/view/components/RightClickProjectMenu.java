package view.components;

import javax.swing.JMenuItem;

import view.frame.MainFrame;

public class RightClickProjectMenu extends RightClickMenu{
	
	private static final long serialVersionUID = 4117973420282888292L;
	private JMenuItem shareMenuItem = new JMenuItem("Share here");
	private JMenuItem pasteMenuItem = new JMenuItem("Paste");

	
	public RightClickProjectMenu() {
		super();
		
		shareMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getShareHereAction());
		pasteMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getPasteDocumentAction());

		add(shareMenuItem);
		add(pasteMenuItem);
	}
}
