package view.graphics.painters;

import java.awt.geom.GeneralPath;

import view.graphics.elements.GraphicElement;
import view.graphics.elements.TriangleElement;

public class TrianglePainter extends DevicePainter{

	private static final long serialVersionUID = -2619950899102357245L;

	public TrianglePainter(GraphicElement element) {
		super(element);
		reDraw();
	}
	public void reDraw() {
		TriangleElement triangle = (TriangleElement) element;

		shape=new GeneralPath();
		((GeneralPath)shape).moveTo(triangle.getPosition().getX(),triangle.getPosition().getY());
		
		((GeneralPath)shape).lineTo(triangle.getPosition().getX()+triangle.getSize().width,triangle.getPosition().getY());
		
		((GeneralPath)shape).lineTo(triangle.getPosition().getX()+triangle.getSize().width/2, triangle.getPosition().getY()+triangle.getSize().height);
		
		oldShape = shape;
		((GeneralPath)shape).closePath();
		 triangle.setShape(shape);
		 triangle.setOriginalShape(shape);
	}
}
