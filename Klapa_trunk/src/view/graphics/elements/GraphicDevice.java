package view.graphics.elements;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Paint;
import java.awt.Shape;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JOptionPane;
import javax.swing.text.AttributeSet;
import javax.swing.text.Style;


import interfaces.IEditor;
import view.desktop.PagePreView;
import view.frame.MainFrame;
import view.graphics.elements.editor.ImageEditor;
import view.graphics.elements.editor.TextEditor;
import view.graphics.painters.SerializableStroke;

public abstract class GraphicDevice extends GraphicElement  implements Serializable {
	
	private static final long serialVersionUID = 1497570793932846660L;
	protected Dimension size;
	protected Point2D position;
	private Shape shape;
	private double angle;
	private Style sd;
	private String slotText;
	private IEditor slotType;
	
	public GraphicDevice(Point2D position, Dimension size, SerializableStroke stroke, Paint paint, Color strokeColor){
		super(stroke, paint,strokeColor);
		this.size = size;
		position.setLocation(position.getX()-size.getWidth()/2,position.getY()-size.getHeight()/2);
		this.position = position;
	}
	
	public IEditor getSlotType() {
		return slotType;
	}
	
	//konstruktor kopije
	public GraphicDevice(GraphicDevice device){
		super(device);
		this.size=device.size;
		this.position=device.position;	
	}	
	
	public Point2D getPosition() {
		return position;
	}
	
	public void setPosition(Point2D position) {
		this.position = position;
	}

	public Dimension getSize() {
		return size;
	}

	public void setSize(Dimension size) {
		this.size = size;
	}

	public Shape getShape() {
		return shape;
	}
	
	public void setSlotType(PagePreView ppv) {
		int option;
		if(slotType==null) {
			Object options[] = {"Tekstualni","Multimedijalni"};
			option = JOptionPane.showOptionDialog(MainFrame.getInstance(),
    				"Da li zelite tekstualni ili multimedijalni slot? ",
    				"", 
    				JOptionPane.YES_NO_CANCEL_OPTION,
    				JOptionPane.QUESTION_MESSAGE,
    				null,
    				options,
    				options[1]);
			
			if(option == JOptionPane.YES_OPTION) {
				slotType = new TextEditor(this);
				
				((TextEditor)slotType).addObserver(ppv);
			}
			else {
				slotType = new ImageEditor(this);
			}
		}
		if(slotType!=null) {
			slotType.show();
			
		}	
	}
	
	public void setShape(Shape shape) {
		this.shape = shape;
	}

	public double getAngle() {
		return angle;
	}

	public void setAngle(double currAngle) {
		this.angle = currAngle;
	}

	public Style getSd() {
		return sd;
	}

	public void setSd(Style sd) {
		this.sd = sd;
	}

	public String getSlotText() {
		return slotText;
	}

	public void setSlotText(String slotText) {
		this.slotText = slotText;
	}

}
