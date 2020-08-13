package view.desktop;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JSplitPane;
import javax.swing.border.TitledBorder;

import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.page.Page;
import view.frame.MainFrame;
import view.graphics.state.StateManager;

public class DocumentView extends JPanel implements MyObserverInterface, MyObservableInterface{
	
	private ArrayList <MyObserverInterface> observers = new ArrayList<>();
	boolean changed = false;
	private static final long serialVersionUID = 1L;
	private JScrollPane scroll;
	private JPanel jp;
	private GridBagConstraints gbc;
	private int ygrid;
	private Document documentModel;
	private String title;
	private ArrayList<PageView> allPageViews = new ArrayList<>();
	private DocumentPreView documentPreView;
	
	public DocumentPreView getDocumentPreView() {
		return documentPreView;
	}

	public void setDocumentPreView(DocumentPreView documentPreView) {
		this.documentPreView = documentPreView;
	}

	private StateManager stateManager = new StateManager(this);
	private PageView activePageView;
	private JSplitPane jsplitPane;
	
	public DocumentView(Document documentModel)
	{
		this.documentModel = documentModel;
		documentPreView = new DocumentPreView(documentModel);
		title = documentModel.getName();
		ygrid = 0;
		setLayout(new BorderLayout());
		jp = new JPanel();
		scroll = new JScrollPane(jp);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setUnitIncrement(25);		
		//add(scroll, BorderLayout.CENTER);
		initialise();
		addPages();
		
	}
	
	public ArrayList<PageView> getAllPageViews() {
		return allPageViews;
	}
	
	public JScrollPane getScroll() {
		return scroll;
	}
	
	public JPanel getJp() {
		return jp;
	}
	public JSplitPane getJsplitPane() {
		return jsplitPane;
	}
	
	public void addPages() {
		for(Page pageModel: documentModel.getPages()) {
			addPage(pageModel);
		}
		jsplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,documentPreView,scroll); 
		add(jsplitPane);
		jsplitPane.setDividerLocation(150);

	}
	
	public void addPage(Page pageModel) {
		PageView pageView= new PageView(pageModel, stateManager, this) {
				private static final long serialVersionUID = 1L;

				protected void paintComponent(Graphics g) {
			        super.paintComponent(g);
			        Dimension arcs = new Dimension(15,15); //Border corners arcs {width,height}, change this to whatever you want
			        int width = getWidth();
			        int height = getHeight();
			        Graphics2D graphics = (Graphics2D) g;
			        graphics.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
			        graphics.setColor(getBackground());
			        graphics.fillRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint background
			        graphics.setColor(getForeground());
			        graphics.drawRoundRect(0, 0, width-1, height-1, arcs.width, arcs.height);//paint border
			     }
			  };
			pageView.setOpaque(false);
			pageModel.addObserver(pageView);
			
		
		//GroupLayout gp = new GroupLayout(this);
			JInternalFrame[] jInternalFrame=MainFrame.getInstance().getDesktop().getAllFrames(); //desktop is JDesktopPane
			for(JInternalFrame interFrame : jInternalFrame) {
				if(interFrame.isMaximum()){
					pageView.setBorder(null);
					pageView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,25), Color.BLACK));

					gbc = new GridBagConstraints();
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.weightx = 1;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.gridx = 0;
					gbc.gridy = ygrid;
			        gbc.ipady = 650; 
					gbc.insets = new Insets(20, 100, 0, 100);
					ygrid++;
					jp.add(pageView,gbc);
					allPageViews.add(pageView);	
					return;
				}
			}
			pageView.setBorder(null);
			pageView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,15), Color.BLACK));

			gbc = new GridBagConstraints();
			gbc.gridwidth = GridBagConstraints.REMAINDER;
			gbc.weightx = 1;
			gbc.fill = GridBagConstraints.HORIZONTAL;
			gbc.gridx = 0;
			gbc.gridy = ygrid;
			gbc.ipady = 200; 
			gbc.insets = new Insets(30, 5, 20, 5);
			ygrid++;
			jp.add(pageView,gbc);
			allPageViews.add(pageView);	
	}
	
	public void removePage(Page pageModel) {
		PageView temp = null;
		for (PageView pv: allPageViews) {
			if (pv.getPageModel().equals(pageModel)) {
				temp = pv;
			}
		}
		if (temp == null) return;
		jp.remove(temp);
		allPageViews.remove(temp);
		updateUI();
	}
	

	public void initialise() {
		
		jp.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();		
	}
	
	public Document getDocumentModel() {
		return documentModel;
	}

	@Override
	public void update(MyObservableInterface o, Object arg) {
		if (o instanceof Document && arg instanceof String) {
			this.setTitle((String) arg);
			setChanged();
			notifyObservers();
		}
		
		if (o instanceof Document && arg instanceof Page) {
			if (((Document)o).getPages().contains((Page)arg)) {
				addPage((Page)arg);
			}
			else {
				removePage((Page)arg);
			}
		}
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public void addObserver(MyObserverInterface o) {
		observers.add(o);
	}
	
	public void deleteObserver(MyObserverInterface o) {
		observers.remove(o);
	}
	
	public void notifyObservers() {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, null);
			}
			clearChanged();
		}
	}
	
	public void notifyObservers(Object arg) {
		if (changed) {
			for (MyObserverInterface o: observers) {
				o.update(this, arg);
			}
			clearChanged();
		}
	}
	
	public boolean hasChanged() {
		return changed;
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public void clearChanged() {
		changed = false;
	}
	
	@Override
	public String toString() {
		return getDocumentModel().getName();
	}
	
	public void setYgrid(int ygrid) {
		this.ygrid = ygrid;
	}
	
	public StateManager getStateManager() {
		return stateManager;
	}
	
	public void setActivePageView(PageView pv) {
		this.activePageView = pv;
		for (PageView curr: allPageViews) {
			if (curr == pv) continue;
			curr.getPageModel().getSelectionModel().clearSelection();
		}
	}
	
	public PageView getActivePageView() {
		return activePageView;
	}
}
