package view.graphics.state;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.AddElementCommand;

public class CircleState extends State implements Serializable{
	
	private static final long serialVersionUID = -4151576105801594399L;

private DocumentView mediator; 
	
	public CircleState (DocumentView documentView) {
      	mediator = documentView;
	}

	public void mousePressed(MouseEvent e) {
		
		if (e.getButton()==MouseEvent.BUTTON1){
			Point2D position = (Point2D) e.getPoint();
			PageView pageView = mediator.getActivePageView();

			pageView.getCommandManager().addCommand(new AddElementCommand(pageView.getPageModel(), position, PageView.CIRCLE));

			//CircleElement circle = (CircleElement) CircleElement.createDefault(position);
			//pageView.getPageModel().addElement(circle);
		}
	}
}
