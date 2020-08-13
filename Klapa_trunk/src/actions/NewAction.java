package actions;

import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;

import javax.swing.KeyStroke;

import errorHandling.ErrorHandler;
import observer.MyObservableNode;
import tree.page.Page;
import tree.treeFactory.FactoryGenerator;
import tree.treeFactory.NodeFactory;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class NewAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 1L;
	//private FactoryGenerator factoryGenerator;

	public NewAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(
		        KeyEvent.VK_N, ActionEvent.CTRL_MASK));
		putValue(SMALL_ICON, loadIcon("resources/new.png"));
		putValue(NAME, "New");
		putValue(SHORT_DESCRIPTION, "New");
		//factoryGenerator = new FactoryGenerator();
	}

	
	public void actionPerformed(ActionEvent e) {	
		WorkSpaceTree view = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();		//ne moze da se dodeli ovo u konstruktoru jer za vreme poziva ovog konstruktora nije zavrsena inicijalizacija mainframe
		MyObservableNode parent = (MyObservableNode) view.getLastSelectedPathComponent();
		
		if (parent instanceof Page) {
			ErrorHandler.getInstance().CantAddNewException();
			return;
		}
		
		 if(parent == null ) {
			 ErrorHandler.getInstance().NothingChosenException("dodavanje");
			 return;
		 }
		 
		 NodeFactory nodeFactory = FactoryGenerator.getFactory(parent);
		 nodeFactory.factory(parent);
		 
		 view.update();
		
		 if (parent instanceof WorkSpace)
			 view.expandRow(0);

		if (! view.isSelectionEmpty())
			view.expandRow(view.getSelectionRows()[0]);
		
	}
	

}
