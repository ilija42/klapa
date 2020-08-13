package view.graphics.state;

import java.awt.Dimension;
import java.awt.Point;
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
import view.desktop.PageView.Handle;
import view.graphics.command.ResizeCommand;
import view.graphics.command.ResizeElementCommand;
import view.graphics.elements.CircleElement;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.DevicePainter;

public class ResizeState extends State  implements Serializable{
	
	private static final long serialVersionUID = 8185161026194432965L;
	Handle handle;
	DocumentView mediator;
	PageView pageView;
	Page page;
	Shape originalShape;
	Point originalPoint;
	Point startPoint;
	GraphicDevice element;
	
	HashMap<GraphicDevice,Point2D> ogLocations;
	HashMap<GraphicDevice, Shape> ogShapes;
	HashMap<GraphicDevice, Dimension> ogSizes;
	HashMap<GraphicDevice, Point2D> finalLocations;
	HashMap<GraphicDevice, Shape> finalShapes; 
	HashMap<GraphicDevice, Dimension> finalSizes;
	
	public ResizeState (DocumentView documentView) {
		mediator = documentView;
	}

	public void mousePressed(MouseEvent e) {
		if (! (e.getButton()==MouseEvent.BUTTON1)) return;
		pageView = (PageView)e.getComponent();
		page = pageView.getPageModel();
		ogLocations = new HashMap<>();
		ogShapes = new HashMap<>();
		ogSizes = new HashMap<>();
		finalLocations = new HashMap<>();
		finalShapes = new HashMap<>();
		finalSizes = new HashMap<>();
		
		originalPoint = e.getPoint();
		startPoint = e.getPoint();
		for (GraphicDevice selected : page.getSelectionModel().getSelectedElements()) {
			ogLocations.put(selected, selected.getPosition());
			ogShapes.put(selected, selected.getShape());
			ogSizes.put(selected, selected.getSize());
		}
	}	
	
	@Override
	public void mouseDragged(MouseEvent e) {
		if ( (e.getButton()==MouseEvent.BUTTON2)) return;
		element = null;
			for(GraphicDevice selected : page.getSelectionModel().getSelectedElements()) {

				if(selected.getShape().contains(startPoint)) {
					element = selected ;
				}
			}	
			
			if(element != null) updateElement(e.getPoint(), startPoint, element, page.getSelectionModel().getSelectedElements(), handle);
	}
	
	public void mouseReleased(MouseEvent e) {
		if ( (e.getButton()==MouseEvent.BUTTON2)) return;
		if (element != null) {
			ArrayList<GraphicDevice> elements = new ArrayList<>();
			elements.addAll(pageView.getPageModel().getSelectionModel().getSelectedElements());
			Point op = new Point (originalPoint);
			
			for (GraphicDevice selected : elements) {
				finalLocations.put(selected, selected.getPosition());
				finalShapes.put(selected, selected.getShape());
				finalSizes.put(selected, selected.getSize());
			}
			
			pageView.getCommandManager().addCommand(new ResizeElementCommand(pageView, elements, ogLocations, ogShapes, ogSizes, finalLocations, finalShapes, finalSizes));
			//pageView.getCommandManager().addCommand(new ResizeCommand(this, element, elements, op, e.getPoint(), handle));
		}
		
		handle = null;
	}
	
	public void updateHandle() {
		if(pageView==null)return;
		pageView.repaint();
		handle = null;
	}
	
	public void updateElement(Point endPoint, Point startPoint, GraphicDevice selected, ArrayList<GraphicDevice> elements, Handle handle) {
		Dimension oldDim = selected.getSize();
		if(handle==null) {
			handle = pageView.getDeviceAndHandleForPoint(endPoint);
			this.handle = handle;
			return;
		}
	
		for(GraphicDevice element : elements) {
			page.removeElement(element);
		}
		
		double x =endPoint.getX()-startPoint.getX();
		double y =endPoint.getY()-startPoint.getY();
	
		switch (handle) {
		case North:
			for(GraphicDevice element : elements) {
				if (element.getSize().getHeight()-y < 20) return;
				
				element.setPosition(new Point((int)element.getPosition().getX(),(int) (element.getPosition().getY()+y)));
				element.setSize(new Dimension((int)oldDim.width,(int) (element.getSize().getHeight()-y)));
				element.getPainter().reDraw();
				page.addElement(element);
			
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {	
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			
			break;
		case East:
			for(GraphicDevice element : elements) {
				if ((int)oldDim.width+x < 20) return;
				
				element.setPosition(new Point((int) ((int)element.getPosition().getX()),(int) (element.getPosition().getY())));
				element.setSize(new Dimension((int) ((int)oldDim.width+x),(int) (element.getSize().getHeight())));
				element.getPainter().reDraw();
				page.addElement(element);
				
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			
			break;
		case West:
			for(GraphicDevice element : elements) {
				if ((int)oldDim.width-x < 20) return;
				
				element.setPosition(new Point((int) ((int)element.getPosition().getX()+x),(int) (element.getPosition().getY())));
				element.setSize(new Dimension((int) ((int)oldDim.width-x),(int) (element.getSize().getHeight())));
				element.getPainter().reDraw();
				page.addElement(element);
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			
			break;
		case South:
			for(GraphicDevice element : elements) {
				if ((element.getSize().getHeight()+y) < 20) return;
				
				element.setPosition(new Point((int)element.getPosition().getX(),(int) (element.getPosition().getY())));
				element.setSize(new Dimension((int)oldDim.width,(int) (element.getSize().getHeight()+y)));
				element.getPainter().reDraw();
				page.addElement(element);
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			
			break;
		case NorthWest:

			for(GraphicDevice element : elements) {
				if (((int)oldDim.width-x) < 20 || (int) element.getSize().getHeight()-y < 20) return;
				element.setPosition(new Point((int) ((int)element.getPosition().getX()+x),(int) (element.getPosition().getY()+y)));
				element.setSize(new Dimension((int) ((int)oldDim.width-x),(int) (element.getSize().getHeight()-y)));
				element.getPainter().reDraw();
				page.addElement(element);
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			break;
		case NorthEast:
			for(GraphicDevice element : elements) {
				if ((int)oldDim.width+x < 20 || (int) element.getSize().getHeight()-y < 20) return;
				element.setPosition(new Point((int)element.getPosition().getX(),(int) (element.getPosition().getY()+y)));
				element.setSize(new Dimension((int) ((int)oldDim.width+x),(int) (element.getSize().getHeight()-y)));
				element.getPainter().reDraw();
				page.addElement(element);
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
						break;
		case SouthWest:
			for(GraphicDevice element : elements) {
				if ((int)oldDim.width-x < 20 || (int) element.getSize().getHeight()+y < 20) return;
				element.setPosition(new Point((int) ((int)element.getPosition().getX()+x),(int) (element.getPosition().getY())));
				element.setSize(new Dimension((int) ((int)oldDim.width-x),(int) (element.getSize().getHeight()+y)));
				element.getPainter().reDraw();
				page.addElement(element);
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			break;
		case SouthEast:
			for(GraphicDevice element : elements) {
				if ((int)oldDim.width+x < 20 || (int) element.getSize().getHeight()+y < 20) return;
				element.setPosition(new Point((int)element.getPosition().getX(),(int) (element.getPosition().getY())));
				element.setSize(new Dimension((int) ((int)oldDim.width+x),(int) (element.getSize().getHeight()+y)));
				element.getPainter().reDraw();
				page.addElement(element);
				
				if(element.getAngle()!=0 && !(element instanceof CircleElement)) {
					page.removeElement(element);
					AffineTransform at = new AffineTransform();
					at.rotate(element.getAngle(),(element.getPosition().getX()+(element.getSize().getWidth())/2),(element.getPosition().getY()+(element.getSize().getHeight())/2));
					element.setShape(at.createTransformedShape(element.getOriginalShape()));						
				
					((DevicePainter) element.getPainter()).setShape(element.getShape());
					page.addElement(element);
					pageView.repaint();
				}
			}
			
			break;
			
		}
		
		//selected.setSize(new Dimension((int)(oldPoint.getX()-p.getX()+oldDim.width),(int)(oldPoint.getY()-p.getY())+oldDim.height));
	//	selected.getPainter().reDraw();
		
		if(this.startPoint == startPoint)
			this.startPoint.setLocation(startPoint.getX()+x,startPoint.getY()+y);
		pageView.repaint();
	}
	
	public void revertResize(Point finalPoint, Point originalPoint, GraphicDevice selected, ArrayList<GraphicDevice> elements, Handle handle) {
		updateElement(originalPoint, finalPoint, selected, elements, handle);
	}
}
	