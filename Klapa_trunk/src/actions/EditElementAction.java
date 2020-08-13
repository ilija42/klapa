package actions;

import java.awt.event.ActionEvent;

import javax.swing.JOptionPane;
import javax.swing.JTabbedPane;

import errorHandling.ErrorHandler;
import view.desktop.DocumentView;
import view.desktop.PagePreView;
import view.desktop.ProjectView;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.editor.TextEditor;

public class EditElementAction extends MyAbstractAction{
	
	private static final long serialVersionUID = -7154274139091261399L;

	public EditElementAction() {
		putValue(SMALL_ICON, loadIcon("resources/editElement.png"));
		putValue(SHORT_DESCRIPTION, "Edit");
	}

	public void actionPerformed(ActionEvent arg0) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}
		
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
			if(doc.getActivePageView()==null) {
				ErrorHandler.getInstance().NothingChosenException("editovanje");
				return;
			}
			if(doc.getActivePageView().getPageModel().getSelectionModel().getSelectedElements().isEmpty() == false) {	
				GraphicDevice  gd = doc.getActivePageView().getPageModel().getSelectionModel().getSelectedElements().get(0);
				for(PagePreView ppv : doc.getDocumentPreView().getAllPagePreViews()) {
					if(ppv.getPageModel() == doc.getActivePageView().getPageModel())gd.setSlotType(ppv);
				}
				}
			else {
				ErrorHandler.getInstance().NothingChosenException("editovanje");
			}
			
			
		
		
	}
}
