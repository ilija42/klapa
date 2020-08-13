package view.graphics.command;

import java.util.ArrayList;

import tree.page.Page;
import view.graphics.elements.GraphicDevice;

public class DeleteCommand extends AbstractCommand{
	
	Page page;
	ArrayList<GraphicDevice> elements = new ArrayList<>();
	
	
	public DeleteCommand (Page page, ArrayList <GraphicDevice> elements) {
		this.page = page;
		this.elements = elements;
	}

	@Override
	public void doCommand() {
		
		for (GraphicDevice element: elements) {
			page.removeElement(element);
		}
		page.getSelectionModel().clearSelection();
	}

	@Override
	public void undoCommand() {
		
		for (GraphicDevice element: elements) {
			page.addElement(element);
		}
		
	}

}
