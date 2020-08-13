package actions;

import java.awt.Shape;
import java.awt.event.ActionEvent;
import java.awt.geom.AffineTransform;

import javax.swing.JTabbedPane;

import errorHandling.ErrorHandler;
import view.desktop.DocumentView;
import view.desktop.ProjectView;
import view.frame.MainFrame;
import view.graphics.elements.CircleElement;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.DevicePainter;
import view.graphics.state.SelectState;

public class PaintResizeAction extends MyAbstractAction{
	
	private static final long serialVersionUID = 8793344742130921931L;

	public PaintResizeAction() {
		putValue(SMALL_ICON, loadIcon("resources/resize.png"));
		putValue(SHORT_DESCRIPTION, "Resize");
	}
	
	public void actionPerformed(ActionEvent arg0) {

		
		ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
		if (pv == null) {
			ErrorHandler.getInstance().NothingChosenException("editovanje");
			return;
		}
		JTabbedPane tabs = pv.getDocumentViewManager();
		DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
		
		doc.getStateManager().setResizeState();
		if(!(doc.getStateManager().getCurrentState() instanceof SelectState)) {
			return;
		}
		for(GraphicDevice selected : doc.getActivePageView().getPageModel().getSelectionModel().getSelectedElements()) {
			AffineTransform at = new AffineTransform();
				if(selected != null) {	
					Shape shape = selected.getShape();
					selected.setOriginalShape(shape); 
					doc.getActivePageView().setCurrentTransformation(shape);				
					doc.getActivePageView().paintSelectionHandles();
					doc.getActivePageView().repaint();
					
					if(selected.getAngle()!=0 && !(selected instanceof CircleElement)) {
						
						at.rotate(selected.getAngle(),(selected.getPosition().getX()+(selected.getSize().getWidth())/2),(selected.getPosition().getY()+(selected.getSize().getHeight())/2));
						selected.setShape(at.createTransformedShape(selected.getOriginalShape()));						
					
						((DevicePainter) selected.getPainter()).setShape(selected.getShape());
					}
				}
				
			
			}
	}
}
