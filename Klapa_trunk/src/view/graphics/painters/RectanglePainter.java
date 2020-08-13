package view.graphics.painters;

import java.awt.geom.GeneralPath;

import view.graphics.elements.GraphicElement;
import view.graphics.elements.RectangleElement;


public class RectanglePainter extends DevicePainter{
	
	private static final long serialVersionUID = -8760902677305847232L;

	public RectanglePainter(GraphicElement element) {
		super(element);
		reDraw();
	}
	
	public void reDraw() {
		RectangleElement rectangle = (RectangleElement) element;
		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(rectangle.getPosition().getX(),rectangle.getPosition().getY());
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX()+rectangle.getSize().width,rectangle.getPosition().getY());
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX()+rectangle.getSize().width,rectangle.getPosition().getY()+rectangle.getSize().height);
		((GeneralPath)shape).lineTo(rectangle.getPosition().getX(),rectangle.getPosition().getY()+rectangle.getSize().height);
		((GeneralPath)shape).closePath();
		oldShape = shape;
		
		rectangle.setShape(shape);
		rectangle.setOriginalShape(shape);
	}
}
