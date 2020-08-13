package view.desktop;

import javax.swing.JInternalFrame;

import actions.ProjectViewListener;
import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.project.Project;
import view.frame.MainFrame;

public class ProjectView extends JInternalFrame implements MyObserverInterface{

	private static int projectCount = 0;
	private static final long serialVersionUID = 1L;
	private Project projectModel;
	private String title;
	private DocumentViewManager documentViewManager;
	static final int xOffset = 30, yOffset = 30;

	public ProjectView(Project projectModel)
	{	
		super("" , true,true, true, true);
		
		this.projectModel = projectModel;
		this.title = projectModel.getName();
		documentViewManager = new DocumentViewManager();
		initializeTabs();
		add(documentViewManager);
		projectCount++;
		this.addInternalFrameListener(new ProjectViewListener());
		this.addPropertyChangeListener(MainFrame.getInstance().getActionManager().getJinternalFrameResizeAction());
		setSize(375,475);
		setIconifiable(true);
		setClosable(true);
		setVisible(true);
		setResizable(false);
		setLocation(projectCount*xOffset,projectCount*yOffset);
		setTitle(title);
		setDefaultCloseOperation(HIDE_ON_CLOSE);
	}
	
	public void initializeTabs() {
		
		for (Document documentModel: projectModel.getDocuments()) {
			
			documentViewManager.addDocumentView(documentModel);
			}
	 }
	
	public Project getProjectModel() {
		return projectModel;
	}
	
	public DocumentViewManager getDocumentViewManager() {
		return documentViewManager;
	}
	
	
	@Override
	public String toString() {
		return getProjectModel().getName();
	}
	

	@Override
	public void update(MyObservableInterface o, Object arg) {
		
		if(o instanceof Project && arg instanceof String) {
			setTitle((String)arg);
			updateUI();
		}
	}
}
