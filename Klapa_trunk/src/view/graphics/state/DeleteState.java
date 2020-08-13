package view.graphics.state;

import java.io.Serializable;
import java.util.ArrayList;

import tree.page.Page;
import view.desktop.DocumentView;
import view.desktop.PageView;
import view.graphics.command.DeleteCommand;
import view.graphics.elements.GraphicDevice;

public class DeleteState extends State implements Serializable{
	
	private static final long serialVersionUID = 4169478962837342257L;
	private DocumentView mediator;
	
	public DeleteState (DocumentView mediator) {
		this.mediator = mediator;
	}
	
	public void work() {
		PageView pv = mediator.getActivePageView();
		Page page = pv.getPageModel();
		ArrayList<GraphicDevice> elements = new ArrayList<>();
		elements.addAll(page.getSelectionModel().getSelectedElements());
				
		pv.getCommandManager().addCommand(new DeleteCommand(page, elements));
		mediator.getStateManager().setSelectState();
	}
}

	
