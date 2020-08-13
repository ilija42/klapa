package tree.workspace;


import java.io.Serializable;
import java.util.LinkedList;

import javax.swing.tree.MutableTreeNode;

import observer.MyObservableNode;
import tree.project.Project;

public class WorkSpace extends MyObservableNode implements Serializable {
	
	private static final long serialVersionUID = 7241750518688685796L;
	int num = 1;
	
	public WorkSpace() {
		name = "Workspace";
	}
	
	public void addProject(Project p){
		if (p.getName() == null) p.setName("Project " + num);
		children.add(p);
		p.setParent(this);
		p.addParent(this);
		this.registerChange();
		setChanged();
		notifyObservers();
		num++;
	}
	
	public void add(MutableTreeNode newChild) {
		addProject((Project)newChild);
		this.registerChange();
	}
	
	public Project getProject(int index) {
		return (Project) children.get(index);
	}	

	public LinkedList<Project> getProjects() {
		LinkedList <Project> projects = new LinkedList<>();
		for (MyObservableNode n: children) {
			projects.add((Project)n);
		}
		return projects;
	}
	
}
