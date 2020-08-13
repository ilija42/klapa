package view.components;

import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;

import view.frame.MainFrame;

public class Menu extends JMenuBar{
	
	private static final long serialVersionUID = 1L;

	public Menu() {
		
		JMenu fileMenu = new JMenu("File");
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveFileAsAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getSaveFileAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getNewAction());
		fileMenu.add(MainFrame.getInstance().getActionManager().getDeleteAction());
		
		fileMenu.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		JMenuItem newProjectItem = new JMenuItem("New project");
		newProjectItem.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		//fileMenu.add(newProjectItem);
		//newProjectItem.addActionListener(MainFrame.getInstance().getActionManager().getNewAction());
		
		JMenuItem deleteMenuItem = new JMenuItem("Delete");
		deleteMenuItem.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		//fileMenu.add(deleteMenuItem);
		//deleteMenuItem.addActionListener(MainFrame.getInstance().getActionManager().getDeleteAction());
		
		JMenu aboutMenu = new JMenu("Help");
		aboutMenu.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		JMenuItem aboutItem = new JMenuItem("About");
		aboutItem.setFont(new Font(Font.SANS_SERIF,Font.BOLD,15));
		//aboutMenu.add(aboutItem);
		
		aboutItem.addActionListener(MainFrame.getInstance().getActionManager().getAboutAction());
		
		add(fileMenu);
		add(aboutMenu);
		setBorder(BorderFactory.createEtchedBorder());
		setBorderPainted(true);
	}
	
}
