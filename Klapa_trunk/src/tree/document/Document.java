package tree.document;

import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.tree.MutableTreeNode;

import observer.MyObservableNode;
import tree.page.Page;

public class Document extends MyObservableNode  implements Serializable,Transferable, ClipboardOwner{
	
	private static final long serialVersionUID = 1001776283722334898L;
	int num = 1;
	static public DataFlavor documentFlavor; 
	private DataFlavor [] supportedFlavors = {documentFlavor}; 
	
	public Document() {
		try {
			documentFlavor =
			new DataFlavor (Class.forName ("tree.document.Document"),"Document");
		 }
		catch (ClassNotFoundException e) {
			 e.printStackTrace ();
		 }
	}
	
	//TODO konstruktor kopije
	public Document (Document doc) {
		this.num = doc.num;
		this.setName(doc.getName() + " kopija");
		for (MyObservableNode page: doc.children) {
			Page pageCopy = new Page((Page)page);
			this.add(pageCopy);
		}
		
	}
	
	 public void add(MutableTreeNode newChild) {
			addPage((Page)newChild);
		}
		
		public void addPage(Page page) {
			children.add(page);
			page.setName("Page " + num);
			page.setParent(this);
			page.addParent(this);
			num++;
			setChanged();
			notifyObservers(page);
			this.registerChange();
		}
		
		public Page getPage(int index) {
			return (Page) children.get(index);
		}	
	
		public LinkedList<Page> getPages() {
			LinkedList <Page> pages = new LinkedList<>();
			for (MyObservableNode n: children) {
				pages.add((Page)n);
			}
			return pages;
		}

		@Override
		public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
			if (flavor.equals (documentFlavor))
				 return (this);
		    else
				 throw new UnsupportedFlavorException (documentFlavor);
		}

		@Override
		public DataFlavor[] getTransferDataFlavors() {
			return supportedFlavors;
		}

		@Override
		public boolean isDataFlavorSupported(DataFlavor flavor) {
			return (flavor.equals (documentFlavor)); 
		}

		@Override
		public void lostOwnership(Clipboard clipboard, Transferable contents) {
			
		}		
}
