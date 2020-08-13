package view.graphics.state;

import java.io.Serializable;

import view.desktop.DocumentView;

public class StateManager implements Serializable{
	
	private static final long serialVersionUID = -8951899731704330809L;

	private State currentState;
	
	private CircleState circleState; 
	private TriangleState triangleState;
	private RectangleState rectangleState;
	private RotateState rotateState;
	private ResizeState resizeState;
	private SelectState selectState;
	private DeleteState deleteState;
	
	public StateManager(DocumentView documentView)
	{
		circleState = new CircleState(documentView); 
		triangleState = new TriangleState(documentView); 
		rectangleState=new RectangleState(documentView);
		rotateState=new RotateState(documentView);
		resizeState = new ResizeState(documentView);
		selectState = new SelectState(documentView);
		deleteState = new DeleteState(documentView);
     	currentState = null;
	}

	public void setSelectState() {
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
			currentState = selectState;
	}

	public void setCircleState() {
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
		
		currentState = circleState; 
	}
	
	public void setTriangleState() { 
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
		currentState = triangleState; 
	}
	
	public void setRectangleState() {
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
		currentState = rectangleState; 
	}
	
	public void setRotateState() { 
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
		currentState = rotateState; 
	}
	
	public void setResizeState() { currentState = resizeState; }
	
	public void setDeleteState() { 
		if(currentState instanceof ResizeState) {
			((ResizeState)currentState).updateHandle();
		}
		currentState = deleteState;
		deleteState.work();
	}
	
	public State getCurrentState() {
		return currentState;
	}
}
