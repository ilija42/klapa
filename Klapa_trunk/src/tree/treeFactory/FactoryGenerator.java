package tree.treeFactory;

import observer.MyObservableNode;
import tree.document.Document;
import tree.project.Project;
import tree.workspace.WorkSpace;

public class FactoryGenerator {
	private static ProjectFactory projectFactory= new ProjectFactory();
	private static DocumentFactory documentFactory = new DocumentFactory();
	private static PageFactory pageFactory = new PageFactory();
	
	public static NodeFactory getFactory(MyObservableNode parent) {
		if (parent instanceof WorkSpace) return projectFactory;
		if (parent instanceof Project) return documentFactory;
		if (parent instanceof Document) return pageFactory;
		return null;
	}
}
