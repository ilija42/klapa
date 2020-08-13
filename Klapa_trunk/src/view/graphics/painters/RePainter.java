package view.graphics.painters;

import java.awt.geom.AffineTransform;

import view.graphics.elements.GraphicDevice;
import view.graphics.elements.GraphicElement;

public class RePainter extends DevicePainter{

	private static final long serialVersionUID = 2019436000389715154L;
	
	AffineTransform at;
	GraphicElement element;
	
	public RePainter(GraphicElement element){
		super(element);
		this.element = element;	
		setShape(((GraphicDevice)element).getShape());
	}

	
	public void setAt(AffineTransform at) {
		this.at = at;
		element.setHowtoDraw(at);
		setShape(((GraphicDevice)element).getShape());
		}
}
