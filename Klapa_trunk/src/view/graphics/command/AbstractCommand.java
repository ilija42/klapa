package view.graphics.command;

import java.io.Serializable;

public abstract class AbstractCommand implements Serializable{
	
	private static final long serialVersionUID = 7571594180374456612L;
	public abstract void doCommand();
	public abstract void undoCommand();
}
