package view.graphics.elements;

import java.awt.Color;
import java.awt.Paint;
import java.awt.Point;
import java.awt.Shape;
import java.awt.geom.AffineTransform;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.graphics.painters.ElementPainter;
import view.graphics.painters.SerializableStroke;



public abstract class GraphicElement implements Serializable{
	
	private static final long serialVersionUID = 7515826534411628476L;
	protected Paint paint;
	protected SerializableStroke stroke;
	protected Color  strokeColor;
	protected AffineTransform howtoDraw = null;
	protected Shape originalShape = null;
	protected Point2D originalPoint = null;
	protected String name;
	protected String description;
	protected ElementPainter elementPainter; //instancira se prilikom konstrukcije konkretnih elemenata
	
	abstract public GraphicElement clone();
	
	public GraphicElement(SerializableStroke stroke, Paint paint, Color strokeColor){
		this.stroke = stroke;
		this.paint = paint;
		this.strokeColor = strokeColor;
	}
	
	//konstruktor kopije
	public GraphicElement (GraphicElement element) {
		this.stroke=element.stroke;
		this.paint=element.paint;
		this.strokeColor=element.strokeColor;
		this.name=element.name;
		this.description=element.description;
		//this.elementPainter=element.elementPainter;
	}
	
	
	public Shape getOriginalShape() {
		return originalShape;
	}
	
	public void setOriginalShape(Shape originalShape) {
		this.originalShape = originalShape;
	}

	public ElementPainter getElementPainter() {
		return elementPainter;
	}
	public AffineTransform getHowtoDraw() {
		return howtoDraw;
	}
	public void setHowtoDraw(AffineTransform at) {
		this.howtoDraw = at;
	}
	public void setElementPainter(ElementPainter elementPainter) {
		this.elementPainter = elementPainter;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public ElementPainter getPainter() {
		return elementPainter;
	}

	public Paint getPaint() {
		return paint;
	}

	public void setPaint(Paint paint) {
		this.paint = paint;
	}

	public SerializableStroke getStroke() {
		return stroke;
	}

	public void setStroke(SerializableStroke stroke) {
		this.stroke = stroke;
	}
	
	public Color getStrokeColor() {
		return strokeColor;
	}

	public void setStrokeColor(Color strokeColor) {
		this.strokeColor = strokeColor;
	}
	
	public Point2D getOriginalPoint() {
		return originalPoint;
	}

	public void setOriginalPoint(Point2D originalPoint2) {
		this.originalPoint = originalPoint2;
	}
	
}
