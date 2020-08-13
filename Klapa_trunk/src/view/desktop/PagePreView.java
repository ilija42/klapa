package view.desktop;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.text.BadLocationException;
import javax.swing.text.Element;
import javax.swing.text.ElementIterator;

import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.page.Page;
import view.frame.MainFrame;

public class PagePreView extends JPanel  implements MyObserverInterface{

	private static final long serialVersionUID = -6600725626229253654L;
	private Page pageModel;
	JTextPane jTPane;
	//private Document mediator;
	
	 public PagePreView(Page pageModel,Document mediator) {
		// this.mediator = mediator;
		 this.setPageModel(pageModel);
		 this.setBackground(Color.WHITE);
		MouseController mcntr = new MouseController(pageModel);
		addMouseListener(mcntr);
		addMouseMotionListener(mcntr);
		jTPane = new JTextPane();
		this.add(jTPane);
	 }
	 
	 private class MouseController extends MouseAdapter implements MouseMotionListener{

			private Page pageModel;
			
			
					
			public MouseController(Page pageModel) {
				this.pageModel = pageModel;
			}

			public void mousePressed(MouseEvent e) {
				ProjectView pv =  (ProjectView)MainFrame.getInstance().getDesktop().getSelectedFrame();
				JTabbedPane tabs = pv.getDocumentViewManager();
				DocumentView doc =  (DocumentView) tabs.getSelectedComponent();
						
				for(PageView p: doc.getAllPageViews()) {
					if(p.getPageModel() == pageModel) {
						doc.getScroll().getVerticalScrollBar().setValue((int) p.getLocation().getY());
					}
				}

			}

		}

	@Override
	public void update(MyObservableInterface o, Object arg) {
		//if(JTPane!=null)remove(jTPane);
		//jTPane = ((JTextPane)arg);
        javax.swing.text.Document dest = jTPane.getDocument();
         javax.swing.text.Document source= ((JTextPane)arg).getDocument();
try {
  dest.remove(0, dest.getLength());

  ElementIterator iterator = new ElementIterator(source);
  Element element;
  while ((element = iterator.next()) != null) {
      if (element.isLeaf()) {
          int start = element.getStartOffset();
          int end = element.getEndOffset();
          String text = source.getText(start, end - start);
          dest.insertString(dest.getLength(), text, 
              element.getAttributes());
      }
  }
} catch (BadLocationException e) {
  throw new RuntimeException(e);
}
		add(jTPane);
	}

	public Page getPageModel() {
		return pageModel;
	}

	public void setPageModel(Page pageModel) {
		this.pageModel = pageModel;
	}
}
