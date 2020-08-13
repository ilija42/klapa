package view.desktop;

import java.util.ArrayList;

import javax.swing.JTabbedPane;

import actions.DocumentViewListener;
import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.project.Project;
import view.components.ButtonTabComponent;

public class DocumentViewManager extends JTabbedPane implements MyObserverInterface{
	
	private static final long serialVersionUID = 1L;

	private  ArrayList <DocumentView>    allDocumentViews    = new ArrayList<>();

	public DocumentViewManager() {
		addContainerListener(new DocumentViewListener(this));
	}
	
	public void addDocumentView(Document documentModel) {
		
		/*addChangeListener(new ChangeListener() {
	        public void stateChanged(ChangeEvent e) {
	    		((PaintToolBar)MainFrame.getInstance().getPaintBar()).getGroup().clearSelection();
	        }
	    });*/
		DocumentView documentView = new DocumentView(documentModel);
		allDocumentViews.add(documentView);
		ButtonTabComponent close = new ButtonTabComponent(this);
		insertTab(documentView.getDocumentModel().getName(), null, documentView, documentView.getDocumentModel().getName(), 0);
 		setTabComponentAt(0, close);
 		setSelectedIndex(0);
 		documentView.addObserver(this);
 	    documentModel.addObserver(documentView);
	}
	
	
	
	@Override
	public void update(MyObservableInterface o, Object arg) {
	
		if (o instanceof Project && arg instanceof Document) {	
			
			if (( ((Project)o).getDocuments()).contains((Document)arg)) {
				addDocumentView((Document)arg);
				return;
			}
			
			DocumentView temp = null;
			for (DocumentView dv: getAllDocumentViews()) {
				if (dv.getDocumentModel().equals(arg)) 
					temp = dv;
			}
			if (temp != null)
				removeView(temp);		
		}
		
		if(o instanceof DocumentView) {
			
			for(int i = 0; i < getTabCount(); i++)
			{
			   DocumentView dv = (DocumentView) getComponentAt(i);
			   if(dv.getDocumentModel() == ((DocumentView)o).getDocumentModel()) {
					setTitleAt(i,((DocumentView)o).getTitle());
					updateUI();
			   }
			}
	//	int i =indexOfComponent(((DocumentView) arg).getDocumentModel()); mozda moze i ovako ali ne radi
		}
	}
	
	public ArrayList<DocumentView> getAllDocumentViews() {
		return allDocumentViews;
	}
	
	public void replace (DocumentView dvOld, DocumentView dvNew) {
		if (allDocumentViews.contains(dvOld))
			allDocumentViews.add(allDocumentViews.indexOf(dvOld), dvNew);
	}
	
	public boolean contains (DocumentView dv) {
		if(allDocumentViews.contains(dv)) return true;
		return false;
	}
	
	public boolean isDocumentViewOpen(Document documentModel) {
		for (DocumentView dv: allDocumentViews) {
			if(dv.getDocumentModel()==documentModel) return true;
		}
		return false;
	}
	
	public void removeView(DocumentView dv) {
		allDocumentViews.remove(dv);
		remove(dv);
	}
	
}
