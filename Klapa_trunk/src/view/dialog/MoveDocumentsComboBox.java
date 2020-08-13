package view.dialog;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JPanel;

import observer.MyObservableNode;
import tree.workspace.WorkSpaceModel;


public class MoveDocumentsComboBox extends JFrame{
	
	private static final long serialVersionUID = 8622473786164856567L;
	MyObservableNode chosenProject = null;
	
	public MoveDocumentsComboBox(Object[]projects, WorkSpaceModel model, ArrayList<MyObservableNode> movingDocuments) {
		JFrame f = new JFrame("Odabir projekta");
		//f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


	  	JComboBox cb = new JComboBox(projects);
	  	cb.setBounds(100, 100,90,20);    
	  	cb.setSelectedItem(null);
	    
	  	JPanel p = new JPanel();
		p.setSize(90,50);
		p.setLocation(200, 200);
	    JButton okBtn = new JButton("Premesti");
	    okBtn.addActionListener(new ActionListener() { 
	    	  public void actionPerformed(ActionEvent e) { 
	    		    chosenProject = (MyObservableNode)cb.getSelectedItem();
	    		    model.moveDocuments(chosenProject, movingDocuments);
	    		    f.setVisible(false);
	    		  } } );
	    //p.setAlignmentY(JPanel.BOTTOM_ALIGNMENT);
	    
	    f.add(cb);
	    p.add(okBtn);
	    f.add(p);
	    f.setLayout(null);    
	    f.setSize(350,300);
		f.setLocationRelativeTo(null);
	    f.setVisible(true); 
	}
	
	public MyObservableNode getChosenProject() {
		return chosenProject;
	}
}
