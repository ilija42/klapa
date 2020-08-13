package view.graphics.painters;

import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.graphics.elements.GraphicElement;

public class DevicePainter extends ElementPainter implements Serializable {
	
	private static final long serialVersionUID = -4664369989326368314L;
	protected Shape shape;
	protected Shape oldShape;
	protected GraphicElement element;
	
	public DevicePainter(GraphicElement element) {
		super(element);
		this.element = element;
	}

	@Override
	public void paint(Graphics2D g, GraphicElement element) {

		g.setPaint(Color.BLUE);
		g.setStroke(element.getStroke());
		g.draw(getShape());
		g.setPaint(element.getPaint());
		g.fill(getShape());	
}
	


	@Override
	public boolean elementAt(GraphicElement element, Point2D pos) {
		return getShape().contains(pos);
	}
	
	public Shape getShape() {
		return shape;
	}
	
	public void setShape(Shape s) {
		shape = s;
	}

	@Override
	public void reDraw() {
		
	}
}
