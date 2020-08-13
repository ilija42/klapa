package tree.treeFactory;

import observer.MyObservableNode;
import tree.page.Page;

public class PageFactory extends NodeFactory{

	@Override
	public MyObservableNode createNode() {
		MyObservableNode node = new Page();
		return node;
	}

}
