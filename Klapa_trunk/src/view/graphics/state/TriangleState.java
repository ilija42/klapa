package view.graphics.state;

import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;
import java.io.Serializable;

import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.AddElementCommand;

public class TriangleState extends State  implements Serializable{
	
	private static final long serialVersionUID = -7402683844323943886L;
	private DocumentView mediator; 
	
	public TriangleState (DocumentView documentView) {
		mediator = documentView;
	}

	public void mousePressed(MouseEvent e) {
		
		if (e.getButton()==MouseEvent.BUTTON1){
			Point2D position = e.getPoint();
			PageView pageView = mediator.getActivePageView();
			
			pageView.getCommandManager().addCommand(new AddElementCommand(pageView.getPageModel(), position, PageView.TRIANGLE));

			//TriangleElement triangle = (TriangleElement) TriangleElement.createDefault(position);
			//pageView.getPageModel().addElement(triangle);
		}
	}
}
