package view.graphics.state;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.AddElementCommand;

public class RectangleState extends State  implements Serializable{

	private static final long serialVersionUID = 5923902211025477406L;
	
private DocumentView mediator; 
	
	public RectangleState (DocumentView documentView) {
		mediator = documentView;
	}

	public void mousePressed(MouseEvent e) {
		
		if (e.getButton()==MouseEvent.BUTTON1){
			Point2D position = e.getPoint();
			//if (med.getDiagram().getModel().getElementAtPosition(position)==-1)
			PageView pageView = mediator.getActivePageView();
			pageView.getCommandManager().addCommand(new AddElementCommand(pageView.getPageModel(), position, PageView.RECTANGLE));

			//RectangleElement rectangle = (RectangleElement) RectangleElement.createDefault(position);
			//pageView.getPageModel().addElement(rectangle);
			
		}
	}
}
