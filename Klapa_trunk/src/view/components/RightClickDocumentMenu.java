package view.components;

import javax.swing.JMenuItem;

import view.frame.MainFrame;

public class RightClickDocumentMenu extends RightClickMenu {
	
	private static final long serialVersionUID = 1L;
	
	private JMenuItem shareMenuItem = new JMenuItem("Share");
	private JMenuItem cutMenuItem = new JMenuItem("Cut");
	private JMenuItem copyMenuItem = new JMenuItem("Copy");

	
	public RightClickDocumentMenu (){
		super();
		shareMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getShareDocumentAction());
		cutMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getCutDocumentAction());
		copyMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getCopyDocumentAction());

		add(shareMenuItem);
		add(cutMenuItem);
		add(copyMenuItem);
	}

}
