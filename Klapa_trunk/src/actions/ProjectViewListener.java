package actions;
import javax.swing.event.InternalFrameEvent;
import javax.swing.event.InternalFrameListener;

import view.components.PaintToolBar;
import view.desktop.ProjectView;
import view.desktop.ProjectViewManager;
import view.frame.MainFrame;

public class ProjectViewListener implements InternalFrameListener{
	

	@Override
	public void internalFrameOpened(InternalFrameEvent e) {
		
	}
	

	@Override
	public void internalFrameClosing(InternalFrameEvent e) {
		
		ProjectViewManager.getInstance().removeProjectView((ProjectView)e.getInternalFrame());
		((PaintToolBar)MainFrame.getInstance().getPaintBar()).getGroup().clearSelection();
	}

	@Override
	public void internalFrameClosed(InternalFrameEvent e) {
		
		
	}

	@Override
	public void internalFrameIconified(InternalFrameEvent e) {
		
		
	}

	@Override
	public void internalFrameDeiconified(InternalFrameEvent e) {
		
		
	}

	@Override
	public void internalFrameActivated(InternalFrameEvent e) {
		
		
	}
	
	@Override
	public void internalFrameDeactivated(InternalFrameEvent e) {
		
		
	}
	
}
