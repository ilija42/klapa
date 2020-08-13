package view.graphics.painters;

import java.awt.Graphics2D;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.graphics.elements.GraphicElement;


public abstract class ElementPainter implements Serializable{
	
	private static final long serialVersionUID = -1859513954426752515L;

	public ElementPainter(GraphicElement element) {	}

	public abstract void paint(Graphics2D g, GraphicElement element);
	
	public abstract boolean elementAt(GraphicElement element, Point2D pos);
	
	public abstract void reDraw();

}
