package view.graphics.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;

import view.graphics.painters.SerializableStroke;
import view.graphics.painters.TrianglePainter;

public class TriangleElement extends GraphicDevice {

	private static final long serialVersionUID = -1270383099259888494L;

	public TriangleElement(Point2D position, Dimension size, SerializableStroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new TrianglePainter(this);
	}
	
	//konstruktor kopije
	public TriangleElement(TriangleElement triangle){
		super(triangle);
		setName("kopija");
		elementPainter=new TrianglePainter(this);
	}
	
	public static GraphicDevice createDefault(Point2D pos){
		
		Point2D position = (Point2D) pos.clone();
        Paint fill = Color.WHITE;
	    TriangleElement triangle = new TriangleElement(position, new Dimension(100,100),  new SerializableStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ), fill, Color.BLACK);
		return triangle;
	}
	
	@Override
	public GraphicElement clone() {
		return new TriangleElement(this);
	}
	
	
}
