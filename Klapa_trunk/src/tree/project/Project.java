package tree.project;

import java.io.File;
import java.util.LinkedList;

import javax.swing.tree.MutableTreeNode;

import observer.MyObservableNode;
import tree.document.Document;

public class Project extends MyObservableNode  {
	
	private static final long serialVersionUID = -2141361553109655235L;
	private int num = 1;
	private File lastpath = null;

	
	public void add(MutableTreeNode newChild) {
		addDocument((Document)newChild);
	}
	
	public void addDocument(Document document) {
		if (children.contains(document)) return;
		children.add(document);
		if (document.getName() == null) document.setName("Document " + num);
		document.setParent(this);
		document.addParent(this);
		setChanged();
		notifyObservers(document);
		num++;
		this.registerChange();
	}
	
	public Document getDocument(int index) {
		return (Document) children.get(index);
	}	
	
	public LinkedList<Document> getDocuments() {
		LinkedList <Document> documents = new LinkedList<>();
		for (MyObservableNode n: children) {
			documents.add((Document)n);
		}
		return documents;
	}

	public File getLastpath() {
		return lastpath;
	}

	public void setLastpath(File lastpath) {
		this.lastpath = lastpath;
	}
	
}
