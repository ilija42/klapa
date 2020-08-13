package tree.treeFactory;

import observer.MyObservableNode;

public abstract class NodeFactory {
	
	public abstract MyObservableNode createNode();
	
	public MyObservableNode factory (MyObservableNode parent) {
		MyObservableNode node = createNode();
		parent.add(node);
		return node;
	}
	
}
