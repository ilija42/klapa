package actions;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;

import javax.swing.KeyStroke;

import observer.MyObservableNode;
import tree.document.Document;
import tree.project.Project;
import tree.workspace.WorkSpaceModel;
import tree.workspace.WorkSpaceTree;
import view.frame.MainFrame;

public class PasteDocumentAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 4534450567926662190L;

	public PasteDocumentAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		Transferable content = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		if (content == null || ! content.isDataFlavorSupported(Document.documentFlavor)) return;
		
		WorkSpaceTree wsView = (WorkSpaceTree) MainFrame.getInstance().getDatabaseView();
		MyObservableNode parent = ((MyObservableNode)wsView.getLastSelectedPathComponent());
		if (! (parent instanceof Project)) return;
		
		
		try {
			Document document = (Document) content.getTransferData (Document.documentFlavor);
			Document documentCopy = new Document(document);
			((WorkSpaceModel) wsView.getModel()).addNode(documentCopy, parent);
		} catch (UnsupportedFlavorException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
			return;
		}		
		
	}

}
