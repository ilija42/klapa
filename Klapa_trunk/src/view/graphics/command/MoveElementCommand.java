package view.graphics.command;

import java.awt.geom.AffineTransform;
import java.util.ArrayList;

import tree.page.Page;
import view.graphics.elements.CircleElement;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.DevicePainter;

public class MoveElementCommand extends AbstractCommand{
	
	Page page;
	ArrayList <GraphicDevice> elements = null;
	double x,y;
	boolean work = false;
	
	public MoveElementCommand(Page page, double x, double y, ArrayList<GraphicDevice> elements) {
		this.page = page;
		this.elements = elements;
		this.x = x;
		this.y = y;
	}

	@Override
	public void doCommand() {
		
		if (!work) {
			work = true;
			System.out.println("prvi");
			return;
		}
		for(GraphicDevice gd : elements) {
			
			AffineTransform at = new AffineTransform();
			gd.getPosition().setLocation(gd.getPosition().getX()+x, gd.getPosition().getY()+y);														
			GraphicDevice gdOld = gd;
			at.setToIdentity();
			gd.getElementPainter().reDraw();
			((DevicePainter) gd.getPainter()).setShape(gd.getShape());

			if(gd.getAngle()!=0 && !(gd instanceof CircleElement)) {

				at.rotate(gd.getAngle(),(gd.getPosition().getX()+(gd.getSize().getWidth())/2),(gd.getPosition().getY()+(gd.getSize().getHeight())/2));
				gd.setShape(at.createTransformedShape(gd.getOriginalShape()));						
			
				((DevicePainter) gd.getPainter()).setShape(gd.getShape());
			}
			 page.removeElement(gdOld);
			 page.addElement(gd);
		}	
		
	}

	@Override
	public void undoCommand() {
			for(GraphicDevice gd : elements) {
			
				AffineTransform at = new AffineTransform();
				gd.getPosition().setLocation(gd.getPosition().getX()-x, gd.getPosition().getY()-y);														
				GraphicDevice gdOld = gd;
				at.setToIdentity();
				gd.getElementPainter().reDraw();
				((DevicePainter) gd.getPainter()).setShape(gd.getShape());
				/*
				if(gd.getAngle()!=0 && !(gd instanceof CircleElement)) {

					at.rotate(gd.getAngle(),(gd.getPosition().getX()+(gd.getSize().getWidth())/2),(gd.getPosition().getY()+(gd.getSize().getHeight())/2));
					gd.setShape(at.createTransformedShape(gd.getOriginalShape()));						
			
					((DevicePainter) gd.getPainter()).setShape(gd.getShape());
				} */
				page.removeElement(gdOld);
				page.addElement(gd);
		}	
		
	}

}
