package view.components;

import java.util.ArrayList;
import java.util.Collections;

import javax.swing.AbstractButton;
import javax.swing.Box;
import javax.swing.ButtonGroup;
import javax.swing.JToggleButton;
import javax.swing.JToolBar;

import view.frame.MainFrame;

public class PaintToolBar extends JToolBar{
	
	private ButtonGroup group;

	private static final long serialVersionUID = 4811945619758406189L;
	
	public PaintToolBar() {
		super(JToolBar.VERTICAL);

			add(Box.createVerticalStrut(40));
			//setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
			group = new ButtonGroup();
			
			JToggleButton btn5 = new JToggleButton(MainFrame.getInstance().getActionManager().getPaintSelection());
			group.add(btn5);
			add(btn5);
			
			add(Box.createVerticalStrut(10));
			
			JToggleButton btn6 = new JToggleButton(MainFrame.getInstance().getActionManager().getPaintDeleteAction());
			group.add(btn6);
			add(btn6);
			
			add(Box.createVerticalStrut(10));
			
			JToggleButton btn0 = new JToggleButton(MainFrame.getInstance().getActionManager().getPaintResizeAction());
			group.add(btn0);
			add(btn0);
			
			add(Box.createVerticalStrut(10));
			
			JToggleButton btn1 = new JToggleButton(MainFrame.getInstance().getActionManager().getPaintRotateAction());
			group.add(btn1);
			add(btn1);
			
			addSeparator();
	
			add(Box.createVerticalStrut(10));

			JToggleButton btn2 = new JToggleButton(MainFrame.getInstance().getActionManager().getPaintRectangleAction());
			group.add(btn2);
			add(btn2);
			
			

			add(Box.createVerticalStrut(10));
			JToggleButton btn3=new JToggleButton(MainFrame.getInstance().getActionManager().getPaintTriangleAction());
			group.add(btn3);
			add(btn3);


			add(Box.createVerticalStrut(10));
			JToggleButton btn4=new JToggleButton(MainFrame.getInstance().getActionManager().getPaintCircleAction());
			group.add(btn4);
			add(btn4);
			
			addSeparator();
			
			add(Box.createVerticalStrut(10));
			JToggleButton undoBtn = new JToggleButton(MainFrame.getInstance().getActionManager().getUndoAction());
			group.add(undoBtn);
			add(undoBtn);
			
			add(Box.createVerticalStrut(10));
			JToggleButton redoBtn = new JToggleButton(MainFrame.getInstance().getActionManager().getRedoAction());
			group.add(redoBtn);
			add(redoBtn);
		
	}
	
	public ButtonGroup getGroup() {
		return group;
	}
	
	public void selectButton() {
		ArrayList<AbstractButton> buttons = Collections.list(group.getElements());
		
		group.setSelected(buttons.get(0).getModel(), true);
	}
	

}
