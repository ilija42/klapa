package actions;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.GridBagConstraints;
import java.awt.Insets;
import java.awt.RenderingHints;
import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.Serializable;

import javax.swing.BorderFactory;
import javax.swing.JInternalFrame;
import javax.swing.JTextPane;
import javax.swing.border.TitledBorder;

import tree.page.Page;
import view.desktop.DocumentView;
import view.desktop.DocumentViewManager;
import view.desktop.PagePreView;
import view.desktop.PageView;
import view.frame.MainFrame;
import view.graphics.elements.GraphicDevice;
import view.graphics.elements.editor.TextEditor;

public class JinternalFrameResizeAction implements PropertyChangeListener,Serializable{

	private static final long serialVersionUID = 6335399509145536406L;

	@Override
	public void propertyChange(PropertyChangeEvent evt) {
		JInternalFrame[] jInternalFrame=MainFrame.getInstance().getDesktop().getAllFrames(); //desktop is JDesktopPane
		for(JInternalFrame interFrame : jInternalFrame) {
			if(interFrame.isMaximum()){
					DocumentViewManager dvm =  (DocumentViewManager)interFrame.getContentPane().getComponent(0);
					
					for(DocumentView dv : dvm.getAllDocumentViews()) {
						dv.getAllPageViews().clear();
						dv.getDocumentPreView().getAllPagePreViews().clear();
						int ygrida =0;	
						int ygridb =0;	

						dv.getJp().removeAll();
						dv.getDocumentPreView().getJp().removeAll();
						
						for(Page pageModel: dv.getDocumentModel().getPages()) {
							
							PageView pageView= new PageView(pageModel,dv.getStateManager(),dv) {
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
							pageView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,25), Color.BLACK));
							pageModel.addObserver(pageView);
							
							
							
					
						
						GridBagConstraints gbc = new GridBagConstraints();
				        gbc.gridwidth = GridBagConstraints.REMAINDER;
				        gbc.weightx = 1;
				        gbc.fill = GridBagConstraints.HORIZONTAL;
				        gbc.gridx = 0;
				        gbc.gridy = ygrida;
				        gbc.ipady = 650; 
				        gbc.insets = new Insets(20, 100, 0, 100);
				        ygrida++;
				        dv.setYgrid(ygrida);
				        dv.getJp().add(pageView,gbc);
						dv.getAllPageViews().add(pageView);	
						dv.updateUI();
					//ovde se zavrsava za pageview
						
						JTextPane jt=null ;
						for(GraphicDevice gd : pageModel.getElements()) {
							if(gd.getSlotType()!=null && gd.getSlotType() instanceof TextEditor) {
								jt = ((TextEditor)gd.getSlotType()).getT();
							}
						}
						PagePreView pagePreView= new PagePreView(pageModel,dv.getDocumentModel()){
	
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
					
						
								pagePreView.setBorder(null);
								pagePreView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,25), Color.BLACK));

								gbc = new GridBagConstraints();
								gbc.gridwidth = GridBagConstraints.REMAINDER;
								gbc.weightx = 1;
								gbc.fill = GridBagConstraints.HORIZONTAL;
								gbc.gridx = 0;
								gbc.gridy = ygridb;
								gbc.ipady = 150; 
								gbc.insets = new Insets(30, 50, 20, 50);
								ygridb++;
								dv.getDocumentPreView().getJp().add(pagePreView,gbc);
								dv.getDocumentPreView().getAllPagePreViews().add(pagePreView);	
								dv.getDocumentPreView().setYgrid(ygridb);
								dv.getDocumentPreView().updateUI();
					}
						dv.getJsplitPane().setDividerLocation(250);
				}
				
			}
			
			else {
				DocumentViewManager dvm =  (DocumentViewManager)interFrame.getContentPane().getComponent(0);
				
				for(DocumentView dv : dvm.getAllDocumentViews()) {
					dv.getAllPageViews().clear();
					dv.getDocumentPreView().getAllPagePreViews().clear();
					
					int ygrida =0;
					int ygridb =0;
					
					dv.getJp().removeAll();
					dv.getDocumentPreView().getJp().removeAll();		        
					
			        for(Page pageModel: dv.getDocumentModel().getPages()) {
						
			        	PageView pageView= new PageView(pageModel, dv.getStateManager(),dv) {
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
						pageView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,15), Color.BLACK));
						pageModel.addObserver(pageView);
						
					
					
					
					GridBagConstraints gbc = new GridBagConstraints();
			        gbc.gridwidth = GridBagConstraints.REMAINDER;
			        gbc.weightx = 1;
			        gbc.fill = GridBagConstraints.HORIZONTAL;
			        gbc.gridx = 0;
			        gbc.gridy = ygrida;
			        gbc.ipady = 200; 
			        gbc.insets = new Insets(30, 5, 20, 5);
			        ygrida++;
			        dv.setYgrid(ygrida);
					dv.getJp().add(pageView,gbc);
					dv.getAllPageViews().add(pageView);
					dv.updateUI();
					//ovde se zavrsava za pageview
					
					JTextPane jt=null ;
					for(GraphicDevice gd : pageModel.getElements()) {
						if(gd.getSlotType()!=null && gd.getSlotType() instanceof TextEditor) {
							jt = ((TextEditor)gd.getSlotType()).getT();
						}
					}
					PagePreView pagePreView= new PagePreView(pageModel,dv.getDocumentModel()){

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
					
					pagePreView.setBorder(null);
					pagePreView.setBorder(BorderFactory.createTitledBorder(null, pageModel.getName() , TitledBorder.CENTER, TitledBorder.BOTTOM, new Font("times new roman",Font.PLAIN,15), Color.BLACK));

					gbc = new GridBagConstraints();
					gbc.gridwidth = GridBagConstraints.REMAINDER;
					gbc.weightx = 1;
					gbc.fill = GridBagConstraints.HORIZONTAL;
					gbc.gridx = 0;
					gbc.gridy = ygridb;
					gbc.ipady = 100; 
					gbc.insets = new Insets(30, 20, 20, 20);
					ygridb++;
					
					dv.getDocumentPreView().getJp().add(pagePreView,gbc);
					dv.getDocumentPreView().getAllPagePreViews().add(pagePreView);		
					dv.getDocumentPreView().setYgrid(ygridb);
					dv.getDocumentPreView().updateUI();

					
					}
			        
					dv.getJsplitPane().setDividerLocation(150);
				}
			}
		}
        
		
	}

	

}
