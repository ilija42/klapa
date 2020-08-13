package view.graphics.command;

import java.awt.Dimension;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import view.desktop.PageView;
import view.graphics.elements.GraphicDevice;

public class ResizeElementCommand extends AbstractCommand{
	
	ArrayList<GraphicDevice> elements;
	HashMap<GraphicDevice,Point2D> ogLocations;
	HashMap<GraphicDevice, Shape> ogShapes;
	HashMap<GraphicDevice, Dimension> ogSizes;
	HashMap<GraphicDevice, Point2D> finalLocations;
	HashMap<GraphicDevice, Shape> finalShapes; 
	HashMap<GraphicDevice, Dimension> finalSizes;
	PageView pv;
	boolean work = false;
	
	

	public ResizeElementCommand(PageView pv, ArrayList<GraphicDevice> elements, HashMap<GraphicDevice, Point2D> ogLocations,
			HashMap<GraphicDevice, Shape> ogShapes, HashMap<GraphicDevice, Dimension> ogSizes,
			HashMap<GraphicDevice, Point2D> finalLocations, HashMap<GraphicDevice, Shape> finalShapes,
			HashMap<GraphicDevice, Dimension> finalSizes) {
		this.elements = elements;
		this.ogLocations = ogLocations;
		this.ogShapes = ogShapes;
		this.ogSizes = ogSizes;
		this.finalLocations = finalLocations;
		this.finalShapes = finalShapes;
		this.finalSizes = finalSizes;
		this.pv = pv;
	}

	@Override
	public void doCommand() {
		if (!work) {
			work = true;
			return;
		}
		
		for (GraphicDevice element: elements) {
			pv.getPageModel().removeElement(element);
			element.setShape(finalShapes.get(element));
			element.setSize(finalSizes.get(element));
			element.setPosition(finalLocations.get(element));
			element.getPainter().reDraw();
			pv.getPageModel().addElement(element);
		}
		
	}

	@Override
	public void undoCommand() {
		for (GraphicDevice element: elements) {
			pv.getPageModel().removeElement(element);
			element.setShape(ogShapes.get(element));
			element.setSize(ogSizes.get(element));
			element.setPosition(ogLocations.get(element));
			element.getPainter().reDraw();
			pv.getPageModel().addElement(element);
		}
		
	}
}
