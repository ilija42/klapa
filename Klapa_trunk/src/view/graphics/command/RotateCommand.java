package view.graphics.command;

import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.HashMap;

import tree.page.Page;
import view.desktop.PageView;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.DevicePainter;

public class RotateCommand extends AbstractCommand{
	
	private static final long serialVersionUID = 1695714719906188138L;
	private PageView pv;
	private ArrayList<GraphicDevice> elements;
	private ArrayList<Point2D> ogPoints;
	private boolean work = false;
	private HashMap<GraphicDevice,ArrayList<Double>> angles;
	private HashMap<GraphicDevice,ArrayList<Double>> reverseAngles;
	HashMap<GraphicDevice, Double> ogAngles;
	Page page;
	

	
	public RotateCommand(PageView pv, ArrayList<GraphicDevice> elements, ArrayList<Point2D> ogPoints,
			ArrayList<Shape> ogShapes, ArrayList<Point2D> finalPoints, ArrayList<Shape> finalShapes, 
			HashMap<GraphicDevice,ArrayList<Double>> angles, HashMap<GraphicDevice, Double> ogAngles) {
		
		this.pv = pv;
		this.elements = elements;
		this.ogPoints = ogPoints;
		this.angles = angles;
		page = pv.getPageModel();
		this.ogAngles = ogAngles;
		
		revertAngles();
	}

	@Override
	public void doCommand() {
		
		if (!work) { work = true; return; }
		int i = 0;
		for (GraphicDevice element: elements) {
			ArrayList<Double> currAngles = angles.get(element);
			rotate(element, currAngles, i);
			i++;
		}
	}

	@Override
	public void undoCommand() {
		
		/*for (GraphicDevice element: elements) {
			ArrayList<Double> currAngles = reverseAngles.get(element);
			rotate(element, currAngles);
		}*/
		int i=0;
		for (GraphicDevice element: elements) {
			ArrayList<Double> currAngle = new ArrayList<>();
			currAngle.add(ogAngles.get(element));
			rotate(element, currAngle, i);
			i++;
		}
	}
	
	public void rotate(GraphicDevice element, ArrayList<Double> currAngles){

		for(Double currAngle: currAngles) {
			
			AffineTransform at = new AffineTransform();
			GraphicDevice gdNew = element;
			GraphicDevice gdOld = gdNew;	
			gdOld= gdNew;
			at.setToIdentity();	
			at.rotate(currAngle,(gdNew.getPosition().getX()+(gdNew.getSize().getWidth())/2),(gdNew.getPosition().getY()+(gdNew.getSize().getHeight())/2));

			gdNew.setShape(at.createTransformedShape(gdNew.getOriginalShape()));
			gdNew.setHowtoDraw(at);
			((DevicePainter)gdNew.getPainter()).setShape(element.getShape());
				
			page.removeElement(gdOld);
			page.addElement(gdNew);
				
			 pv.repaint();
			 element.getPosition().setLocation(ogPoints.get(elements.indexOf(element)));
			((GraphicDevice) gdNew).setAngle(currAngle);
		}
	}
	
	public void rotate(GraphicDevice element, ArrayList<Double> currAngles, int i){

		for(Double currAngle: currAngles) {
			
			AffineTransform at = new AffineTransform();
			GraphicDevice gdNew = element;
			GraphicDevice gdOld = gdNew;	
			gdOld= gdNew;
			at.setToIdentity();	
			at.rotate(currAngle,(ogPoints.get(i).getX()+(gdNew.getSize().getWidth())/2),(ogPoints.get(i).getY()+(gdNew.getSize().getHeight())/2));

			gdNew.setShape(at.createTransformedShape(gdNew.getOriginalShape()));
			gdNew.setHowtoDraw(at);
			((DevicePainter)gdNew.getPainter()).setShape(element.getShape());
				
			page.removeElement(gdOld);
			page.addElement(gdNew);
				
			 pv.repaint();
			 element.getPosition().setLocation(ogPoints.get(elements.indexOf(element)));
			((GraphicDevice) gdNew).setAngle(currAngle);
		}
	}
	
	public void revertAngles() {
		reverseAngles = new HashMap<>();
		for (GraphicDevice element: elements) {
			ArrayList<Double> currAngles = angles.get(element);
			ArrayList<Double> currReverseAngles = new ArrayList<>();
			for (Double d: currAngles) {
				currReverseAngles.add(-d);
			}
			reverseAngles.put(element, currReverseAngles);
		}
	}
}
