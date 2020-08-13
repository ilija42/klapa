package view.desktop;

import java.awt.AlphaComposite;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Shape;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;
import java.awt.geom.Dimension2D;
import java.awt.geom.Point2D;
import java.awt.geom.Rectangle2D;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;

import observer.EObserver;
import observer.MyObservableInterface;
import observer.MyObserverInterface;
import tree.page.Page;
import view.graphics.command.CommandManager;
import view.graphics.elements.GraphicDevice;
import view.graphics.painters.ElementPainter;
import view.graphics.state.ResizeState;
import view.graphics.state.SelectState;
import view.graphics.state.StateManager;

public class PageView extends JPanel implements MyObserverInterface {

	private ArrayList <MyObserverInterface> observers = new ArrayList<>();
	boolean changed = false;
	private static final long serialVersionUID = 1L;	
	private Page pageModel;
	private String title;
	JLabel textInput;
	private StateManager stateManager;
	private DocumentView documentView;
	Graphics2D g2;
	private CommandManager commandManager=new CommandManager();
	private Shape currentTransformation=null;
	static final int handleSize = 7; 
	
	public static final int RECTANGLE=1;
	public static final int TRIANGLE=2;
	public static final int CIRCLE=3;

	
	public PageView(Page pageModel, StateManager stateManager, DocumentView dv) {
		this.pageModel = pageModel; 
		title = pageModel.getName();
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		setBackground(Color.WHITE);
		MouseController mcntr = new MouseController(this);
		addMouseListener(mcntr);
		addMouseMotionListener(mcntr);
		this.stateManager = stateManager;
		this.documentView = dv;
	}
	
	public void setPageModel(Page pageModel){
		this.pageModel = pageModel; 
		this.setName(pageModel.getName());
	}
	
	public Page getPageModel() {
		return pageModel;
	}
	
	public void setText(String s) {
		textInput.setText(s);
	}
	
	
	@Override
	public void update(MyObservableInterface o, Object arg) {	
		if (o instanceof Page && arg instanceof String) {
			this.title = (String)arg;
			setText(title);
			this.updateUI();
		}
		
		else if  (arg == EObserver.NEWELEMENT || arg instanceof GraphicDevice) 
		{
			repaint();
			this.updateUI();
		}
			
		
		else if (arg instanceof GraphicDevice) {
			//TODO
		}
	}

	public ArrayList <MyObserverInterface> getObservers() {
		return observers;
	}

	public void setObservers(ArrayList <MyObserverInterface> observers) {
		this.observers = observers;
		
	}
	
	private class MouseController extends MouseAdapter implements MouseMotionListener{

		private PageView pageView;
		public MouseController(PageView pageView) {
			this.pageView = pageView;
		}

		public void mousePressed(MouseEvent e) {
			
		
			documentView.setActivePageView(pageView);
			if(stateManager.getCurrentState() == null)return;
			   stateManager.getCurrentState().mousePressed(e);
			
			
		}

		public void mouseReleased(MouseEvent e) {
		//	getPageModel().getSelectionModel().clearSelection();
			documentView.setActivePageView(pageView);
			if(stateManager.getCurrentState() == null)return;
			   stateManager.getCurrentState().mouseReleased(e);
		}
		
		public void mouseDragged(MouseEvent e ){
			if(! getPageModel().getSelectionModel().getSelectedElements().isEmpty()) {
				if(stateManager.getCurrentState() == null) return;
				stateManager.getCurrentState().mouseDragged(e);
				}
			else if(stateManager.getCurrentState() instanceof SelectState) {
				stateManager.getCurrentState().mouseDragged(e);
			}
				documentView.setActivePageView(pageView);
			
			
		}
		
	}

		
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g2 = (Graphics2D) g;
		g2.setComposite(AlphaComposite.getInstance(AlphaComposite.SRC_OVER, 0.5f)); //providnost elemenata prilikom preklapanja
		Iterator <GraphicDevice> it = getPageModel().getElementsIterator();
		
		while(it.hasNext()){
			GraphicDevice d = (GraphicDevice) it.next();	
			ElementPainter painter =  d.getPainter();
			painter.paint(g2, d);
		
		}
		if(!pageModel.getSelectionModel().getSelectedElements().isEmpty() && stateManager.getCurrentState() instanceof ResizeState) 
			paintSelectionHandles();
			g2=null;
	}
	
	public enum Handle {
		North, South, East, West, SouthEast, SouthWest, NorthEast, NorthWest
		} 
	
	public void paintSelectionHandles() {
		if (g2 == null) return;

		for(GraphicDevice selected :pageModel.getSelectionModel().getSelectedElements()) {
			
		g2.setStroke(new BasicStroke((float)1, BasicStroke.CAP_SQUARE,
		 BasicStroke.JOIN_BEVEL, 1f, new float[]{3f, 6f}, 0 ));
		 g2.setPaint(Color.BLACK);
		 
		// Iscrtavanje pravougaonika sa isprekidanom linijom 
		 g2.drawRect((int)selected.getPosition().getX(), (int)selected.getPosition().getY(), (int)selected.getSize().getWidth(), (int)selected.getSize().getHeight());
		
		 // Iscrtavanje hendlova
		for(Handle e: Handle.values()){
		//ova metoda iscrtava pun kvadrat na poziciji handle-a
			paintSelectionHandle(g2, getHandlePoint(selected.getPosition(),  selected.getSize(), e));
			}
		}
		 
	}
		 
		private Point2D getHandlePoint(Point2D topLeft, Dimension2D size, Handle handlePosition ){
			double x=0, y=0;
			
			if(handlePosition == Handle.NorthWest || handlePosition == Handle.North || handlePosition == Handle.NorthEast){
				y = topLeft.getY();
			}
			
			if(handlePosition == Handle.East || handlePosition == Handle.West){
				y = topLeft.getY()+size.getHeight()/2;
			}
			
			if(handlePosition == Handle.SouthWest || handlePosition == Handle.South || handlePosition == Handle.SouthEast){
				y = topLeft.getY() + size.getHeight();
			}
			
			if(handlePosition == Handle.NorthWest || handlePosition == Handle.West || handlePosition == Handle.SouthWest){
				x = topLeft.getX(); //levi
			}
		
			if(handlePosition == Handle.North || handlePosition == Handle.South){
				x = topLeft.getX() + size.getWidth()/2; //centralni po x
			}
			
			if(handlePosition == Handle.NorthEast || handlePosition == Handle.East || handlePosition == Handle.SouthEast){
				x = topLeft.getX() + size.getWidth(); //desni
			}

			return new Point2D.Double(x,y);
		}
		
		private void paintSelectionHandle(Graphics2D g2, Point2D position){
			double size = handleSize;
			g2.fill(new Rectangle2D.Double(position.getX()-size/2, position.getY()-size/2, 
					size, size));	
		}
		
		private boolean isPointInHandle(GraphicDevice element, Point2D point, Handle handle){
			if (element instanceof GraphicDevice){
				
				GraphicDevice device=(GraphicDevice)element;

				Point2D handleCenter = getHandlePoint(device.getPosition(), device.getSize(), handle);
				return ( (Math.abs(point.getX()-handleCenter.getX())<=(double)handleSize+20) &&
						(Math.abs(point.getY()-handleCenter.getY())<=(double)handleSize+20) );
			}else {
				return false;
			}
				
		}
		
		private Handle getHandleForPoint(GraphicDevice element, Point2D point){
			for(Handle h: Handle.values()){
				if(isPointInHandle(element, point, h)){
					
					return h;
				}
			}
		;

			return null;
		}
		
		public Handle getDeviceAndHandleForPoint(Point2D point){
			Handle handle = null;
			for(GraphicDevice element : pageModel.getSelectionModel().getSelectedElements()) {
			handle = getHandleForPoint(element, point);
			if(handle!=null)break;
				}		
			return handle;		
		}
		
		public CommandManager getCommandManager() {
			return commandManager;
		}

		public void setCommandManager(CommandManager commandManager) {
			this.commandManager = commandManager;
		}
		
		public Shape getCurrentTransformation() {
			return currentTransformation;
		}
		
		public void setCurrentTransformation(Shape currentTransformation) {
			this.currentTransformation = currentTransformation;
		}

}