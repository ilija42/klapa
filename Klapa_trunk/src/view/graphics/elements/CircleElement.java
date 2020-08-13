package view.graphics.elements;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.geom.Point2D;

import view.graphics.painters.CirclePainter;
import view.graphics.painters.SerializableStroke;

public class CircleElement extends GraphicDevice {

	private static final long serialVersionUID = 8153542458339847766L;

	public CircleElement(Point2D position, Dimension size, SerializableStroke stroke, Paint paint, Color strokeColor) {
		super(position, size, stroke, paint, strokeColor);
		elementPainter = new CirclePainter(this);
	}
	
	//konstruktor kopije
	public CircleElement(CircleElement circle){
		super(circle);
		setName("kopija");
		elementPainter=new CirclePainter(this);
	}
	
	public static GraphicDevice createDefault(Point2D pos){
		
		Point2D position = (Point2D) pos.clone();
        Paint fill = Color.WHITE;
	    CircleElement circle = new CircleElement(position, new Dimension(100,100),  new SerializableStroke((float)(2), BasicStroke.CAP_SQUARE,BasicStroke.JOIN_BEVEL ), fill, Color.BLACK);
		return circle;
	}

	@Override
	public GraphicElement clone() {
		return new CircleElement(this);
	}

}
