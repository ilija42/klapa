package view.graphics.state;
import java.awt.Shape;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;

import tree.page.Page;
import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.ResizeCommand;
import view.graphics.command.RotateCommand;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.DevicePainter;


public class RotateState extends State implements Serializable{

	private static final long serialVersionUID = -6614377717403465469L;
	private static final double PI = 3.14;
	DocumentView mediator;
	AffineTransform at;
	Point2D originalPoint;
	HashMap<Shape,Point2D> originalPoints;
	ArrayList<Shape> ogShapes;
	ArrayList<Point2D> ogPoints;
	ArrayList<Shape> finalShapes;
	ArrayList<Point2D> finalPoints;
	private boolean rotated = false;
	PageView pageView;
	Page page;
	HashMap<GraphicDevice, ArrayList<Double>> angles;
	HashMap<GraphicDevice, Double> ogAngles;
	
	public RotateState(DocumentView documentView) {
		mediator = documentView;
	}
	
 public void mousePressed(MouseEvent e) {
	 
	 rotated = false;
	 if (! (e.getButton()==MouseEvent.BUTTON1)) return;
	 
		angles= new HashMap<>();
		ogAngles = new HashMap<>();
		pageView = (PageView)e.getComponent();
		page = pageView.getPageModel();
		//Handle handleInMotion = pageView.getDeviceAndHandleForPoint(position);
		originalPoints = new HashMap<>();
		ogShapes = new ArrayList<>();
		finalShapes = new ArrayList<>();
		ogPoints = new ArrayList<>();
		finalPoints = new ArrayList<>();

		// if(handleInMotion == null){ 
		for(GraphicDevice selected : page.getSelectionModel().getSelectedElements()) {
			selected.setOriginalShape(selected.getShape()); 
			originalPoint = selected.getPosition();
			originalPoints.put(selected.getShape(), originalPoint);
			ogPoints.add(originalPoint);
			ogShapes.add(selected.getShape());
			angles.put(selected, new ArrayList<Double>());
			ogAngles.put(selected, selected.getAngle());
		}
		//	GraphicDevice selected= page.getElementAtPosition(position);
			//	if(selected != null) {	
				//	ArrayList<GraphicDevice> selection = new ArrayList<>();
				//	selection.add(selected);
				//	page.getSelectionModel().setSelectedElements(selection);
					
					//Shape shape = selected.getShape();
					
					//pageView.setCurrentTransformation(shape);
					
					// System.out.println("klikk"+originalPoint);

					//if(!(selected.getElementPainter() instanceof RePainter))selected.setElementPainter(new RePainter(selected));
				
				//else {
				//	page.getSelectionModel().clearSelection();

				//}
		// }				
}	

	public void mouseDragged(MouseEvent e){
		
		if ( (e.getButton()==MouseEvent.BUTTON2)) return;
		
		for(GraphicDevice currSelected : page.getSelectionModel().getSelectedElements()) {
			at = new AffineTransform();
			GraphicDevice gdNew = currSelected;
			GraphicDevice gdOld = gdNew;	
			gdOld= gdNew;
			at.setToIdentity();	
			
			double currAngle = getAngle(gdNew.getPosition(), e.getPoint().getX(),e.getPoint().getY(),gdNew);
			angles.get(currSelected).add(currAngle);
				
			at.rotate(currAngle,(gdNew.getPosition().getX()+(gdNew.getSize().getWidth())/2),(gdNew.getPosition().getY()+(gdNew.getSize().getHeight())/2));

			gdNew.setShape(at.createTransformedShape(gdNew.getOriginalShape()));
			 gdNew.setHowtoDraw(at);
				((DevicePainter)gdNew.getPainter()).setShape(currSelected.getShape());
				
				 page.removeElement(gdOld);
				 page.addElement(gdNew);
				
				 pageView.repaint();
				 currSelected.getPosition().setLocation(originalPoints.get(currSelected.getOriginalShape()));
				 ((GraphicDevice) gdNew).setAngle(currAngle);
				 rotated = true;
			}
	}
	
	public void mouseReleased(MouseEvent e) {	
		if ( (e.getButton()==MouseEvent.BUTTON2)) return;
		if (!rotated) return;
		
		for(GraphicDevice selected : page.getSelectionModel().getSelectedElements()) {
			finalPoints.add(selected.getPosition());
			finalShapes.add(selected.getShape());
		}
		ArrayList<GraphicDevice> elements = new ArrayList<>();
		elements.addAll(page.getSelectionModel().getSelectedElements());
		pageView.getCommandManager().addCommand(new RotateCommand(pageView, elements, ogPoints, ogShapes, finalPoints, finalShapes, angles, ogAngles));

	}
	
	private double getAngle(Point2D origin, double otherx, double othery, GraphicDevice gd) {
		
	    double dy = othery - (origin.getY()+(gd.getSize().getHeight())/2);
	    double dx = otherx- (origin.getX()+(gd.getSize().getWidth())/2);
	    double angle;	
	    if (dx == 0) 
	        angle = dy >= 0? PI/2: -PI/2;
	    else
	    {
	        angle = Math.atan(dy/dx);
	        if (dx < 0) 
	            angle += PI;
	    }
	    if (angle < 0)
	        angle += 2*PI;
	    return angle;
	}
	
	
}
