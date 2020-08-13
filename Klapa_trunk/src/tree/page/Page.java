package tree.page;
import java.awt.geom.Point2D;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Iterator;

import observer.EObserver;
import observer.MyObservableNode;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.GraphicElement;

public class Page extends MyObservableNode implements Serializable{
	
	private static final long serialVersionUID = 8757925239960752710L;
	private ArrayList <GraphicDevice> elements = new ArrayList<>();
	private int num = 0;
	private PageSelectionModel selectionModel = new PageSelectionModel();
	
	public Page (Page p) {
		this.num = p.num;
		this.setName(p.getName() + " copy");
		this.paste(p.getElements());
	}
	
	public Page() {
		
	}
	
	public void addElement(GraphicDevice e) {
		elements.add(e);
		num++;
		e.setName("Element " + num);
		setChanged();
		notifyObservers(EObserver.NEWELEMENT); //TODO
	}
	
	public ArrayList<GraphicDevice> getElements() {
		return elements;
	}
	
	public void removeElement(GraphicDevice e) {
		num--;
		elements.remove(e);
		setChanged();
		notifyObservers(e); //TODO
	}
	
	public Iterator<GraphicDevice> getElementsIterator(){
		return elements.iterator();
	}
	
	public GraphicDevice getElementAtPosition(Point2D point) {
		for(GraphicDevice g: elements){
			System.out.println(g +"zivoteeeee");
			if(g.getPainter().elementAt((GraphicElement)g, point)){
				return g;
			}
		}
		return null;
	}	
	
	public PageSelectionModel getSelectionModel() {
		return selectionModel;
	}
	
	public void paste (ArrayList<GraphicDevice> tempElements) {
		
		try {
			GraphicDevice device = null;			 		
			for (int i=0;i<tempElements.size();i++) {
	 			
	 			device=(GraphicDevice) tempElements.get(i).clone();
		 		Point2D newLocation=(Point2D) device.getPosition().clone();
		 		newLocation.setLocation(device.getPosition().getX()+40,device.getPosition().getY()+40);
				device.setPosition(newLocation);
		 	/*		
		 		if (device instanceof CircleElement){
		 			device.setElementPainter(new CirclePainter(device));
		 		}
		 		else if (device instanceof RectangleElement){
		 			device.setElementPainter(new RectanglePainter(device));
		 		}
		 		else if (device instanceof TriangleElement) {
		 			device.setElementPainter(new TrianglePainter(device));
		 		}
		 	*/
		 		addElement(device);
	 		
	 		}
		}
		
		catch (Exception e) {
	 		e.printStackTrace ();
		}

}

}
