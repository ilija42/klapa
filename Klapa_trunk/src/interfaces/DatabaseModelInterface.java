package interfaces;

import javax.swing.tree.MutableTreeNode;

import observer.MyObservableInterface;

public interface DatabaseModelInterface extends MyObservableInterface  {
	public void removeNodeFromParent(MutableTreeNode node) ;
}
