package view.graphics.painters;

import java.awt.BasicStroke;
import java.awt.Shape;
import java.io.Serializable;

public class SerializableStroke extends BasicStroke implements Serializable{

	private static final long serialVersionUID = -8221692713446880711L;
	
	public SerializableStroke(float f, int capSquare, int joinBevel) {
		super(f,capSquare,joinBevel);
	}
	@Override
	public Shape createStrokedShape(Shape arg0) {
		return null;
	}

}
