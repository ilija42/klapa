package view.graphics.command;

import java.awt.Point;
import java.awt.Shape;
import java.util.ArrayList;
import java.util.HashMap;

import view.desktop.PageView.Handle;
import view.graphics.elements.GraphicDevice;
import view.graphics.state.ResizeState;

public class ResizeCommand extends AbstractCommand {
	
	ResizeState state;
	GraphicDevice selected;
	ArrayList<GraphicDevice> elements;
	Point originalPoint;
	Point finalPoint;
	Handle handle;
	boolean work = false;
	
	public ResizeCommand(ResizeState state, GraphicDevice selected, ArrayList<GraphicDevice> elements, Point originalPoint, Point finalPoint, Handle handle) {
		this.state = state;
		this.selected = selected;
		this.elements = elements;
		this.originalPoint = originalPoint;
		this.finalPoint = finalPoint;
		this.handle = handle;
	}

	@Override
	public void doCommand() {
		if (!work) {
			work = true;
			return;
		}
			state.updateElement(finalPoint, originalPoint, selected, elements, handle);
	}

	@Override
	public void undoCommand() {
			state.revertResize(finalPoint, originalPoint, selected, elements, handle);
	}

}
