package view.graphics.command;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Set;

import view.frame.MainFrame;

public class CommandManager implements Serializable {

	private static final long serialVersionUID = 1199978845208815074L;
		private ArrayList<AbstractCommand> commands = new ArrayList<AbstractCommand>();
		private int currentCommand = 0;
		
		
		//Dodaje novu komandu na stek i poziva izvršavanje komande
		public void addCommand(AbstractCommand command) {
			while(currentCommand < commands.size())
				commands.remove(currentCommand);
			commands.add(command);
			doCommand();
		}
		
		public void doCommand(){
			if(currentCommand < commands.size()) {
				commands.get(currentCommand++).doCommand();
				MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(true);
			}
			if(currentCommand==commands.size()){
				MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(false);
			}
		}
 
		public void undoCommand(){
			if(currentCommand > 0){
				MainFrame.getInstance().getActionManager().getRedoAction().setEnabled(true);
				commands.get(--currentCommand).undoCommand();
			}
			if(currentCommand==0){
				MainFrame.getInstance().getActionManager().getUndoAction().setEnabled(false);
			}
		}

}
