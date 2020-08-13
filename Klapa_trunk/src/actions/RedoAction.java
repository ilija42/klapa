package actions;

import java.awt.event.ActionEvent;

import javax.swing.JTabbedPane;

import view.desktop.DocumentView;
import view.desktop.PageView;
import view.desktop.ProjectView;
import view.frame.MainFrame;

public class RedoAction extends MyAbstractAction{

	private static final long serialVersionUID = -2698040343995309985L;

	
	public RedoAction() {
		putValue(SMALL_ICON, loadIcon("resources/redo.png"));
		putValue(SHORT_DESCRIPTION, "Redo");
	}
	
	@Override
	public void actionPerformed(ActionEvent e) {
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) return;
		JTabbedPane tabs = pv.getDocumentViewManager();
		if (tabs == null) return;
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		if (doc == null) return;
		PageView pageView = doc.getActivePageView();
		if (pageView == null) return;
		
		pageView.getCommandManager().doCommand();
	}

}
