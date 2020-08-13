package view.graphics.state;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseEvent;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import tree.page.Page;
import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.MoveElementCommand;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.RectangleElement;
import view.graphics.painters.DevicePainter;
import view.graphics.painters.SerializableStroke;


public class SelectState extends State implements Serializable{

	private static final long serialVersionUID = -8653819049471314395L;

	private Point2D position;
	private RectangleElement rectangle;
	private boolean move = false;
	private boolean moved = false;
	private GraphicDevice toMove = null;
	double sumX,sumY;
	
	public SelectState(DocumentView documentView) {	
	}
	
	 public void mousePressed(MouseEvent e) {
		 if ( (e.getButton()==MouseEvent.BUTTON2)) return;
		 
		 sumX=0; sumY=0;
		 moved = false;
		 position = e.getPoint();
		 PageView pageView = (PageView)e.getComponent();
		 Page page = pageView.getPageModel();
		 boolean clickedOutside = true;

		 if (e.getButton()==MouseEvent.BUTTON1){
			 for(GraphicDevice gd : page.getSelectionModel().getSelectedElements()) {
				 if(gd.getShape().contains(e.getPoint())) {
					 move = true; 
					 toMove = gd;
					toMove.setOriginalShape(gd.getShape());
					 return;
				 }
				 
			 }
			 move=false;
			 position =  e.getPoint();	
			rectangle = new RectangleElement(position, new Dimension(0,0),  new SerializableStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ), Color.WHITE, Color.BLACK);
			
			
			for(GraphicDevice gd : page.getElements()) {
				if(gd.getShape().contains(e.getPoint())) clickedOutside = false;
			}
			
			if(clickedOutside) {page.getSelectionModel().getSelectedElements().clear();}
				
			for(GraphicDevice gd : pageView.getPageModel().getElements()) {
								
					if(!clickedOutside && (gd.getShape().contains(e.getPoint()) || page.getSelectionModel().getSelectedElements().contains(gd))) {
					
						 gd.setPaint(Color.lightGray); 
							pageView.repaint();
							
							if(!pageView.getPageModel().getSelectionModel().getSelectedElements().contains(gd))
								pageView.getPageModel().getSelectionModel().getSelectedElements().add(gd);	
					}
					else {
						gd.setPaint(Color.WHITE);
						pageView.repaint();
					}
			}	
		}
						
	}	

		public void mouseDragged(MouseEvent e){
			if ( (e.getButton()==MouseEvent.BUTTON2)) return;
			if(move) {
				double x = e.getPoint().getX()-toMove.getPosition().getX()-toMove.getSize().getWidth()/2;
				double y = e.getPoint().getY()-toMove.getPosition().getY()-toMove.getSize().getHeight()/2;
				sumX+=x;
				sumY+=y;
								
				if(toMove != null) {
					
					for(GraphicDevice gd :((PageView)e.getComponent()).getPageModel().getSelectionModel().getSelectedElements()) {
					
						AffineTransform at = new AffineTransform();
						gd.getPosition().setLocation(gd.getPosition().getX()+x, gd.getPosition().getY()+y);														
						GraphicDevice gdOld = gd;
						at.setToIdentity();
						gd.getElementPainter().reDraw();
						((DevicePainter) gd.getPainter()).setShape(gd.getShape());

						/*if(gd.getAngle()!=0 && !(gd instanceof CircleElement)) {
			
							at.rotate(gd.getAngle(),(gd.getPosition().getX()+(gd.getSize().getWidth())/2),(gd.getPosition().getY()+(gd.getSize().getHeight())/2));
							gd.setShape(at.createTransformedShape(gd.getOriginalShape()));						
						
							((DevicePainter) gd.getPainter()).setShape(gd.getShape());
						}*/
						 ((PageView)e.getComponent()).getPageModel().removeElement(gdOld);
						 ((PageView)e.getComponent()).getPageModel().addElement(gd);
						 ((PageView)e.getComponent()).repaint();
					
					}		
					moved = true;
				}
				return;
			}
			
			rectangle.setSize(new Dimension(-(int)(position.getX()-e.getPoint().getX()),-(int)(position.getY()-e.getPoint().getY())));
			
			PageView pageView = (PageView)e.getComponent();
			pageView.getPageModel().removeElement(rectangle);
			pageView.getPageModel().addElement(rectangle);
		
			rectangle.getPainter().reDraw();
			pageView.paintSelectionHandles();
			pageView.repaint();
			
			for(GraphicDevice gd : pageView.getPageModel().getElements()) {
				if(rectangle.getShape().contains(gd.getPosition()) && gd!=rectangle) {
						gd.setPaint(Color.lightGray); 
						pageView.repaint();
						
						if(!pageView.getPageModel().getSelectionModel().getSelectedElements().contains(gd))
							pageView.getPageModel().getSelectionModel().getSelectedElements().add(gd);			
				}
			}
		}
		
		public void mouseReleased(MouseEvent e){
			
			if ( (e.getButton()==MouseEvent.BUTTON2)) return;
			PageView pageView = (PageView)e.getComponent();
			if (pageView == null) return;
			
			if(move && moved) {
				ArrayList<GraphicDevice> elements = new ArrayList<>();
				elements.addAll(pageView.getPageModel().getSelectionModel().getSelectedElements());
				pageView.getCommandManager().addCommand(new MoveElementCommand(pageView.getPageModel(), sumX, sumY, elements));
			}
			pageView.getPageModel().removeElement(rectangle);	
		}
}
