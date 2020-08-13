package actions;

import java.awt.event.ContainerEvent;
import java.awt.event.ContainerListener;

import view.desktop.DocumentViewManager;

public class DocumentViewListener implements ContainerListener{
	
	DocumentViewManager manager;

	public DocumentViewListener(DocumentViewManager manager) {
		super();
		this.manager = manager;
	}

	@Override
	public void componentAdded(ContainerEvent e) {		
	}

	@Override
	public void componentRemoved(ContainerEvent e) {
		manager.getAllDocumentViews().remove(e.getChild());
	}
}
