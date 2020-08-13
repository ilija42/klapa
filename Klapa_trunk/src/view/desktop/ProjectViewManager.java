package view.desktop;

import java.util.ArrayList;

import observer.MyObservableClass;
import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.project.Project;
import tree.workspace.WorkSpace;
import tree.workspace.WorkSpaceModel;
import view.frame.MainFrame;



public class ProjectViewManager extends MyObservableClass implements MyObserverInterface {
	
	private static ProjectViewManager instance = null;
	private static ArrayList <ProjectView> allProjectViews = new ArrayList<>();
	
	public ProjectViewManager() {
		addObserver((MyObserverInterface) MainFrame.getInstance().getDesktop());
	}
	
	public void addProjectView(ProjectView projectView) {
		projectView.getProjectModel().addObserver((MyObserverInterface)projectView.getDocumentViewManager());
		allProjectViews.add(projectView);
		setChanged();
		notifyObservers(projectView);
	}
	
	public void removeProjectView(ProjectView projectView) {
		allProjectViews.remove(projectView);
		setChanged();
		notifyObservers(projectView);
	}
	
	public ArrayList <ProjectView> getAllProjectViews(){
		return allProjectViews;
	}
	
	public void replace (ProjectView pvOld, ProjectView pvNew) {
		if (allProjectViews.contains(pvOld))
			allProjectViews.add(allProjectViews.indexOf(pvOld), pvNew);
	}
	
	public boolean contains (ProjectView arg) {
		if(allProjectViews.contains(arg)) return true;
		return false;
	}
	
	public boolean isProjectViewOpen(Project projectModel) {
		for (ProjectView pv: allProjectViews) {
			if (pv.getProjectModel().equals(projectModel)) return true;
		}
		return false;
	}
	
	public ProjectView getProjectView (Project projectModel) {
		for (ProjectView pv: allProjectViews) {
			if (pv.getProjectModel().equals(projectModel)) return pv;
		}
		return null;
	}

	public static ProjectViewManager getInstance() {
		if (instance == null) instance = new ProjectViewManager();
		return instance;
	}

	public void update(MyObservableInterface o, Object arg) {
		
		if (o instanceof WorkSpaceModel && arg instanceof Project) {
			
			if (((WorkSpace) ((WorkSpaceModel)o).getRoot()).getProjects().contains((Project)arg)) return;
			
			ProjectView trazeni = null;
			for (ProjectView pv: getAllProjectViews()) {
				if (pv.getProjectModel().equals(arg)) 
					trazeni = pv;
			}
			if (trazeni != null)
				removeProjectView(trazeni);
			
		}
	}
}
