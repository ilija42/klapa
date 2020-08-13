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
import javax.swing.JTabbedPane;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.document.Document;
import tree.page.Page;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.editor.TextEditor;

public class DocumentPreView extends JPanel implements MyObserverInterface, MyObservableInterface {

	private static final long serialVersionUID = -2168876012179014231L;
	private Document documentModel;
	private JScrollPane scroll;
	private JPanel jp;
	
	public JPanel getJp() {
		return jp;
	}

	public void setJp(JPanel jp) {
		this.jp = jp;
	}
	
	public void setYgrid(int ygrid) {
		this.ygrid = ygrid;
	}
	
	private GridBagConstraints gbc;
	private int ygrid;
	private ArrayList<PagePreView> allPagePreViews = new ArrayList<>();
	
	public ArrayList<PagePreView> getAllPagePreViews() {
		return allPagePreViews;
	}

	public void setAllPagePreViews(ArrayList<PagePreView> allPagePreViews) {
		this.allPagePreViews = allPagePreViews;
	}

	boolean changed = false;
	
	public DocumentPreView(Document documentModel) {
		this.documentModel = documentModel;
		ygrid = 0;
		setLayout(new BorderLayout());
		jp = new JPanel();
		scroll = new JScrollPane(jp);
		scroll.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		scroll.getVerticalScrollBar().setUnitIncrement(25);		
		add(scroll, BorderLayout.CENTER);
		initialise();
		addPages();
		documentModel.addObserver(this);
	}
	
	public void addPages() {
		for(Page pageModel: documentModel.getPages()) {
			addPage(pageModel);
		}
	}
	
	public void addPage(Page pageModel) {
		JTextPane jt=null ;
		for(GraphicDevice gd : pageModel.getElements()) {
			if(gd.getSlotType()!=null && gd.getSlotType() instanceof TextEditor) {
				jt = ((TextEditor)gd.getSlotType()).getT();
			}
		}
		PagePreView pageView= new PagePreView(pageModel,documentModel){
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
					gbc.ipady = 150; 
					gbc.insets = new Insets(30, 50, 20, 50);
					ygrid++;
					jp.add(pageView,gbc);
					allPagePreViews.add(pageView);	
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
			gbc.ipady = 100; 
			gbc.insets = new Insets(30, 20, 20, 20);
			ygrid++;
			jp.add(pageView,gbc);
			allPagePreViews.add(pageView);	
	}
	
	public void initialise() {	
		jp.setLayout(new GridBagLayout());
		gbc = new GridBagConstraints();		
	}
	public void removePage(Page pageModel) {
		PagePreView temp = null;
		for (PagePreView pv: allPagePreViews) {
			if (pv.getPageModel().equals(pageModel)) {
				temp = pv;
			}
		}
		if (temp == null) return;
		jp.remove(temp);
		allPagePreViews.remove(temp);
		updateUI();
	}
	
	@Override
	public void addObserver(MyObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void deleteObserver(MyObserverInterface o) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void notifyObservers(Object o) {
		// TODO Auto-generated method stub
		
	}


	@Override
	public void update(MyObservableInterface o, Object arg) {
		if (o instanceof Document && arg instanceof String) {
			//this.setTitle((String) arg);
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
	public boolean hasChanged() {
		return changed;
	}
	
	public void setChanged() {
		changed = true;
	}
	
	public void clearChanged() {
		changed = false;
	}
}
