package view.graphics.elements.editor;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.Serializable;
import java.util.ArrayList;

import javax.swing.JColorChooser;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.JTextPane;
import javax.swing.text.Style;
import javax.swing.text.StyleConstants;
import javax.swing.text.StyledDocument;

import interfaces.IEditor;
import observer.MyObservableClass;
import observer.MyObservableInterface;
import observer.MyObserverInterface;
import view.graphics.elements.GraphicDevice;

public class TextEditor  extends MyObservableClass implements IEditor,ActionListener,Serializable{
	
	private static final long serialVersionUID = -3965503208492607106L;
		JTextPane t;
	    JFrame f; 
	    GraphicDevice g;
	    ArrayList<MyObserverInterface> observers = new ArrayList<>();

		public TextEditor(GraphicDevice g){ 
	        this.g  = g;
	        f = new JFrame("editor"); 
	 
	        t = new JTextPane();
	        
	        class close implements WindowListener{
	             
	        @Override 	
	        	public void windowClosing(WindowEvent e) {
	        		 
	        	Object options[] = {"Da","Ne"};
	    		int option = JOptionPane.showOptionDialog(f,
	    				"Da li zelite da sacuvate tekst? ",
	    				"", 
	    				JOptionPane.YES_NO_CANCEL_OPTION,
	    				JOptionPane.QUESTION_MESSAGE,
	    				null,
	    				options,
	    				options[1]);
	    			if(option == JOptionPane.YES_OPTION) {
	    			
	    			}
	    			else t.setText("");   
	    			notifyObservers(t);
	        	}

				@Override
				public void windowActivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowClosed(WindowEvent e) {
				
				}

				@Override
				public void windowDeactivated(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowDeiconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowIconified(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}

				@Override
				public void windowOpened(WindowEvent e) {
					// TODO Auto-generated method stub
					
				}
	      }
	      
	        f.addWindowListener(new close());

	        JMenuBar mb = new JMenuBar(); 
	  	  
	        JMenuItem mi4 = new JMenuItem("cut"); 
	        JMenuItem mi5 = new JMenuItem("copy"); 
	        JMenuItem mi6 = new JMenuItem("paste"); 
	        JMenuItem mi7 = new JMenuItem("bold"); 
	        JMenuItem mi8 = new JMenuItem("italic"); 
	        JMenuItem mi9 = new JMenuItem("change color"); 
	        JMenuItem mi10 = new JMenuItem("underline"); 
	        JMenu mi11 = new JMenu("change font");
	        JMenuItem mi12 = new JMenuItem("Serif");
	        JMenuItem mi13 = new JMenuItem("TimesRoman");
	        JMenuItem mi14 = new JMenuItem("MONOSPACED");
	        int [] sizes = {1,2,3,4,5,6,7,8,9,10,11,12,13,14,15,16,17,18,19,20,21,22,23,24,25,26,27,28,29,30,31,32,33,34,35,36,37,38,39,40,41
	        		,42,43,44,45,46,47,48,49,50};
	      //  JComboBox sizeList = JComboBox(sizes);
	        mi4.addActionListener(this); 
	        mi5.addActionListener(this); 
	        mi6.addActionListener(this); 
	        mi7.addActionListener(this); 
	        mi8.addActionListener(this); 
	        mi9.addActionListener(this);
	        mi10.addActionListener(this);
	        mi12.addActionListener(this);
	        mi13.addActionListener(this);
	        mi14.addActionListener(this);
	        
	        mb.add(mi4); 
	        mb.add(mi5); 
	        mb.add(mi6); 
	        mb.add(mi7);
	        mb.add(mi8);
	        mb.add(mi9);
	        mb.add(mi10);	   
	        mb.add(mi11);
	        mi11.add(mi12);
	        mi11.add(mi13);
	        mi11.add(mi14);
	        f.setJMenuBar(mb); 
	        f.add(t); 
	        f.setSize(700, 700); 
	        f.setVisible(true);
	    		
	    } 
	  
	    public void show() {
	    	f.setVisible(true);
	    }

	    public void actionPerformed(ActionEvent e) 
	    { 
	        String s = e.getActionCommand(); 
	  
	        if (s.equals("cut")) { 
	            t.cut(); 
	        } 
	        else if (s.equals("copy")) { 
	            t.copy(); 
	        } 
	        else if (s.equals("paste")) { 
	            t.paste(); 
	        } 	        	 
	        
	        else if(s.equals("bold")) {
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument(); 
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setBold(style, true);
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        else if(s.equals("italic")) {
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument();
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setItalic(style, true);
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        else if(s.equals("change color")) {
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	JColorChooser jc = new JColorChooser();
        		class colorChooser implements ActionListener{	        		
        		public void actionPerformed(ActionEvent e) {
        			Color c = jc.getColor();
        			StyledDocument doc = t.getStyledDocument();
        			Style style = t.addStyle("Stil", null);
        			StyleConstants.setForeground(style, c);
        			doc.setCharacterAttributes(from, to-from, style, false);
        		}
        			}
        		JDialog co = JColorChooser.createDialog(null, "Izaberi boju", true, jc, new colorChooser(), null);
        		jc.setColor(new Color(154,51,52));
        		co.setVisible(true);
	        }
	        else if(s.equals("underline")) {
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument();
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setUnderline(style, true);
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        else if(s.equals("Serif")){
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument();
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setFontFamily(style,"Serif");
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        else if(s.equals("MONOSPACED")){
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument();
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setFontFamily(style,"MONOSPACED");
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        else if(s.equals("TimesRoman")){
	        	int from = t.getSelectionStart();
	        	int to = t.getSelectionEnd();
	        	StyledDocument doc = t.getStyledDocument();
    			Style style = t.addStyle("Stil", null);
    			StyleConstants.setFontFamily(style,"TimesRoman");
    			doc.setCharacterAttributes(from, to-from, style, false);
	        }
	        notifyObservers(t);
	      
	        
	    }

		@Override
		public void addObserver(MyObserverInterface o) {
			observers.add(o);
		}

		@Override
		public void deleteObserver(MyObserverInterface o) {
			observers.remove(o);
			
		}
		
		public ArrayList<MyObserverInterface> getObservers() {
				return observers;
		}
		
		@Override
		public void notifyObservers() {
			for(MyObserverInterface ob : observers) {
				ob.notify();
			}
		}

		@Override
		public void notifyObservers(Object o) {
			for(MyObserverInterface ob : observers) {
				ob.update(this, o);
			}			
		}
		
		public JTextPane getT() {
			return t;
		}
	
	  
	   
	
}
