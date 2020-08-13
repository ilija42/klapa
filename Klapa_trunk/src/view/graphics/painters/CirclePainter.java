package view.graphics.painters;

import java.awt.geom.Ellipse2D;

import view.graphics.elements.CircleElement;
import view.graphics.elements.GraphicElement;

public class CirclePainter extends DevicePainter{

	private static final long serialVersionUID = -7705581695309222620L;

	public CirclePainter(GraphicElement element) {
		super(element);
		reDraw();
		
	}
	public void reDraw() {
		CircleElement circle = (CircleElement) element;
		
		shape = new Ellipse2D.Double(circle.getPosition().getX(), circle.getPosition().getY(), circle.getSize().getWidth(), circle.getSize().getHeight());
		oldShape = shape;
		circle.setShape(shape);
	}
}
