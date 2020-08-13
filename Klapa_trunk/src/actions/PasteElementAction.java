package actions;

import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.awt.event.ActionEvent;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.util.ArrayList;

import javax.swing.JTabbedPane;
import javax.swing.KeyStroke;

import tree.page.Page;
import tree.page.PageSelectionModel;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;

public class PasteElementAction extends MyAbstractAction{

	private static final long serialVersionUID = 8044603998757126392L;

	public PasteElementAction() {
		putValue(ACCELERATOR_KEY,KeyStroke.getKeyStroke(KeyEvent.VK_V, ActionEvent.CTRL_MASK));
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		
		Transferable content = MainFrame.getInstance().getClipboard().getContents(MainFrame.getInstance());
		if (content == null || !content.isDataFlavorSupported(PageSelectionModel.elementsFlavor)) return;
		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		Page page = doc.getActivePageView().getPageModel();
		
			ArrayList<GraphicDevice> tempElements = new ArrayList<>();
			try {
				tempElements = (ArrayList<GraphicDevice>) content.getTransferData (PageSelectionModel.elementsFlavor);
			} catch (UnsupportedFlavorException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}					

			page.paste(tempElements);
	}
}
