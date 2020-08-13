package tree.treeFactory;

import observer.MyObservableNode;
import tree.project.Project;

public class ProjectFactory extends NodeFactory{

	@Override
	public MyObservableNode createNode() {
		MyObservableNode node = new Project();
		return node;
	}

}
