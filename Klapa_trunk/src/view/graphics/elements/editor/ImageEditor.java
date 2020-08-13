package view.graphics.elements.editor;

import java.awt.Dialog;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.LayoutManager;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.io.Serializable;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JFileChooser;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

import errorHandling.ErrorHandler;
import interfaces.IEditor;
import view.dialog.AboutDialog;
import view.graphics.elements.GraphicDevice;

public class ImageEditor  implements IEditor,Serializable,ActionListener{
	
	private static final long serialVersionUID = 1L;
	GraphicDevice gd;
	private JDialog jd;
	private ImageIcon image;
	JLabel	jlabel1;
	boolean saveClicked = false;
	public ImageEditor(GraphicDevice graphicDevice) {
		gd = graphicDevice;
		jd = new JDialog();
		jlabel1 = new JLabel();
		jlabel1.setSize(350, 400);
		jlabel1.setIcon(new ImageIcon());
		JButton importbtn = new JButton("Import");
		JButton savebtn = new JButton("Save");
		

		JPanel centre = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
		
		gbc.insets = new Insets(0, 30, 0, 30);
		gbc.gridx = 1;
		gbc.gridy = 0;
		centre.setLayout(gb);
		centre.add(jlabel1,gbc);
		
		gbc.gridy =1;
		gbc.gridx = 1;

		JPanel jl = new JPanel();
		jl.setLayout(new FlowLayout(0, 10, 0));
		jl.add(savebtn);
		jl.add(importbtn);
		centre.add(jl,gbc);
		
		savebtn.setActionCommand("save");
		importbtn.setActionCommand("import");
		savebtn.addActionListener(this);
		importbtn.addActionListener(this);
		
		jd.add(centre);
		
		jd.setLocationByPlatform(true);  
		jd.setDefaultCloseOperation(AboutDialog.DISPOSE_ON_CLOSE);
		jd.setTitle("Prikaz multimedijalnog slota");
		jd.setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		jd.setSize(new Dimension(400,200));
		jd.pack();
		jd.setLocationRelativeTo(null);
		jd.pack();
		class close implements WindowListener{
            
	        @Override 	
	        	public void windowClosing(WindowEvent e) {
	        		 if(saveClicked == true) return;
	        	Object options[] = {"Da","Ne"};
	    		int option = JOptionPane.showOptionDialog(jd,
	    				"Da li zelite da sacuvate sliku? ",
	    				"", 
	    				JOptionPane.YES_NO_CANCEL_OPTION,
	    				JOptionPane.QUESTION_MESSAGE,
	    				null,
	    				options,
	    				options[1]);
	    			if(option == JOptionPane.YES_OPTION) {
	    				
	    			}
	    			else{
	    				jlabel1.setIcon(new ImageIcon());  
	    				image = null;
	    			}
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
	      
	        jd.addWindowListener(new close());
	}

	@Override
	public void show() {
		jd.setVisible(true);
		saveClicked = false;
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		 
		String s = e.getActionCommand(); 
		  
	        if (s.equals("save")) { 
	           if(jlabel1.getIcon()!=null) {
	        	   image = (ImageIcon) jlabel1.getIcon();
	        	   saveClicked = true;
	           }
	        } 
	        else if (s.equals("import")) { 
	        	saveClicked = false;
	        	JFileChooser fc = new JFileChooser();
                int result = fc.showOpenDialog(null);
                if (result == JFileChooser.APPROVE_OPTION) {
                    File file = fc.getSelectedFile();
                    try {
                    	if(!file.exists()) {
                    		ErrorHandler.getInstance().BadInputException("File doesnt exist");
                    		return;
                    	}
                    	BufferedImage newImage = ImageIO.read(file);
                        jlabel1.setIcon(new ImageIcon(newImage));
                    } catch (IOException b) {
                        b.printStackTrace();
                    }
                    jd.pack();
	        } 
		
	}

}
	}
