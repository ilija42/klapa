package view.graphics.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;

import view.graphics.painters.RectanglePainter;
import view.graphics.painters.SerializableStroke;

public class RectangleElement extends GraphicDevice{

	private static final long serialVersionUID = -7276787060932679517L;

	public RectangleElement(Point2D position, Dimension size, SerializableStroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new RectanglePainter(this);
	}
	
	//konstruktor kopije
	public RectangleElement(RectangleElement rectangle){
		super(rectangle);
		setName("kopija");
		elementPainter=new RectanglePainter(this);
	}
	
	public static GraphicDevice createDefault(Point2D pos){
		
		Point2D position = (Point2D) pos.clone();
        Paint fill = Color.WHITE;
	    RectangleElement rectangle = new RectangleElement(position, new Dimension(100,50),  new SerializableStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ), fill, Color.BLACK);
		return rectangle;
	}
	

	@Override
	public GraphicElement clone() {
		return new RectangleElement(this);
	}
	
}
