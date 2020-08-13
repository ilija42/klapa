package tree.workspace;

import java.io.Serializable;
import java.util.ArrayList;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.MutableTreeNode;
import interfaces.DatabaseModelInterface;
import observer.MyObservableInterface;
import observer.MyObservableNode;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.project.Project;
import view.dialog.LastProjectWarning;
import view.dialog.MoveDocumentsComboBox;
import view.dialog.MoveDocumentsDialog;

public class WorkSpaceModel extends DefaultTreeModel implements MyObservableInterface, DatabaseModelInterface,Serializable{
	
	private static final long serialVersionUID = -4106871153859661483L;
	private ArrayList <MyObserverInterface> observers = new ArrayList<>(); 
	private boolean changed = false;
	private Document sharedDocument = null;
	WorkSpace ws;
	
	public WorkSpaceModel(WorkSpace ws) {
		super(ws);
		this.ws = ws;
	}
	
	public void addNode(MyObservableNode newNode, MyObservableNode parent) {
		parent.add(newNode);
		setChanged();
		notifyObservers();
	}
	
	public void removeNodeFromParent(MutableTreeNode node) {
		
		if (node instanceof Project && ! ((Project) node).getChildren().isEmpty() && root.getChildCount()==1) {
			LastProjectWarning warning = new LastProjectWarning();
			if (warning.getState()) return;
		}
		
		node.removeFromParent();
		setChanged();
		notifyObservers(node);

		if (node instanceof Project && ! ((Project) node).getChildren().isEmpty() && root.getChildCount()>0) {
				MoveDocumentsDialog dialog = new MoveDocumentsDialog();
				if(dialog.getState()) {
					
					ArrayList<MyObservableNode> movingDocuments = ((Project) node).getChildren();
				  	Object[] projects =  ((MyObservableNode) root).getChildren().toArray();
				  	MoveDocumentsComboBox cb = new MoveDocumentsComboBox(projects, this, movingDocuments);
				}
		}
		
		
	}
	
	public void moveDocuments (MyObservableNode chosenProject, ArrayList<MyObservableNode> movingDocuments) {
		
	  	if (chosenProject == null) return;
	  	for (MyObservableNode doc: movingDocuments) {
	  		this.addNode(doc,chosenProject);
	  	}
	}

	@Override
	public void addObserver(MyObserverInterface o) {
		observers.add(o);
	}

	@Override
	public void deleteObserver(MyObserverInterface o) {
		observers.remove(o);
		
	}

	@Override
	public void notifyObservers() {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, null);
			}
			clearChanged();
		}
	}

	@Override
	public void notifyObservers(Object arg) {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, arg);
			}
			clearChanged();
		}
	}

	@Override
	public boolean hasChanged() {
		return changed;
	}

	@Override
	public void setChanged() {
		changed = true;
	}

	@Override
	public void clearChanged() {
		changed = false;
	}

	public Document getSharedDocument() {
		return sharedDocument;
	}
	
	public void setSharedDocument(Document document) {
		sharedDocument = document;
	}
}
