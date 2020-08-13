package view.graphics.command;
import java.awt.geom.Point2D;
import java.io.Serializable;

import tree.page.Page;
import tree.page.PageSelectionModel;
import view.desktop.PageView;
import view.graphics.elements.CircleElement;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.RectangleElement;
import view.graphics.elements.TriangleElement;

public class AddElementCommand extends AbstractCommand implements Serializable{
	
	private static final long serialVersionUID = 901811695385858801L;
	Page page;
	Point2D lastPosition;
	GraphicDevice element = null;
	PageSelectionModel selectionModel;
	int elementType;

	public AddElementCommand (Page page, Point2D lastPosition, int elementType) {
		this.page = page;
		this.lastPosition = lastPosition;
		this.selectionModel = page.getSelectionModel();
		this.elementType = elementType;
	}
	
	@Override
	public void doCommand() {
		if ( element == null ) {
			if ( elementType == PageView.CIRCLE ){
				element = CircleElement.createDefault(lastPosition);
			}
			else if ( elementType == PageView.RECTANGLE ){
				element = RectangleElement.createDefault(lastPosition);
			}
			else if ( elementType == PageView.TRIANGLE ){
				element = TriangleElement.createDefault(lastPosition);
			}
		}

		page.addElement(element);	
		selectionModel.clearSelection();
		
	}

	@Override
	public void undoCommand() {
		selectionModel.clearSelection();
		page.removeElement(element);
	}

}
