package tree.page;

import java.awt.Color;
import java.awt.datatransfer.Clipboard;
import java.awt.datatransfer.ClipboardOwner;
import java.awt.datatransfer.DataFlavor;
import java.awt.datatransfer.Transferable;
import java.awt.datatransfer.UnsupportedFlavorException;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;

import view.graphics.elements.GraphicDevice;

public class PageSelectionModel implements ClipboardOwner, Transferable, Serializable{
	
	private static final long serialVersionUID = 1036357457946579241L;
	private ArrayList <GraphicDevice> selectedElements; 
	static public DataFlavor elementsFlavor; 
	private DataFlavor [] supportedFlavors = {elementsFlavor}; 
	
	public PageSelectionModel(){
		selectedElements = new ArrayList<>();
		try {
			elementsFlavor =
			new DataFlavor (Class.forName ("java.util.ArrayList"),"Elements");
		 }
		catch (ClassNotFoundException e) {
			 e.printStackTrace ();
		 }
	}
	
	public void clearSelection() {
		for (GraphicDevice element: selectedElements) {
			element.setPaint(Color.WHITE);
		}
		selectedElements.clear();
	}

	public ArrayList<GraphicDevice> getSelectedElements() {
		return selectedElements;
	}
	
	public void setSelectedElements(ArrayList<GraphicDevice> selectedElements) {
		this.selectedElements = selectedElements;
	}
	
	public void addSelectedElement (GraphicDevice element) {
		selectedElements.add(element);
	}

	@Override
	public DataFlavor[] getTransferDataFlavors() {
		return supportedFlavors;
	}

	@Override
	public boolean isDataFlavorSupported(DataFlavor flavor) {
		return (flavor.equals (elementsFlavor)); 
	}

	@Override
	public Object getTransferData(DataFlavor flavor) throws UnsupportedFlavorException, IOException {
		if (flavor.equals (elementsFlavor))
			 return selectedElements;
	    else
			 throw new UnsupportedFlavorException (elementsFlavor);
	}

	@Override 
	public void lostOwnership(Clipboard clipboard, Transferable contents) {

	}
}
