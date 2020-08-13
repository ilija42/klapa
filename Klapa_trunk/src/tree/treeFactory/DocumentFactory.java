package tree.treeFactory;

import observer.MyObservableNode;
import tree.document.Document;

public class DocumentFactory extends NodeFactory {

	@Override
	public MyObservableNode createNode() {
		MyObservableNode node = new Document();
		return node;
	}

}
