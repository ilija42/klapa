package view.dialog;

import java.awt.Dialog;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Image;
import java.awt.Insets;
import java.awt.Label;

import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class AboutDialog extends JDialog {


	private static final long serialVersionUID = 2565893621812438865L;
	public AboutDialog()  {
		
	
		JLabel	jlabel1 = new JLabel();
		jlabel1.setSize(350, 300);
		ImageIcon imageIcon1 = new ImageIcon("resources/Nikolina.jpg");
		Image pic1 = imageIcon1.getImage().getScaledInstance(jlabel1.getWidth(), jlabel1.getHeight(), Image.SCALE_FAST);
		imageIcon1.setImage(pic1);
		jlabel1.setIcon(imageIcon1);
		
		JLabel	jlabel2 = new JLabel();
		jlabel2.setSize(350, 300);
		ImageIcon imageIcon2 = new ImageIcon("resources/ilija.png");
		Image pic2 = imageIcon2.getImage().getScaledInstance(jlabel2.getWidth(), jlabel2.getHeight(), Image.SCALE_FAST);
		imageIcon2.setImage(pic2);
		jlabel2.setIcon(imageIcon2);
		
		
		Label lbl2 = new Label("Ilija Pavlovic RN 54/18 - menadzer tima");
		Label lbl1 = new Label("Nikolina Vranes RN 32/18 - clan tima");	
		
		JPanel centre = new JPanel();
		GridBagLayout gb = new GridBagLayout();
		GridBagConstraints gbc = new GridBagConstraints();
	
		gbc.insets = new Insets(0, 30, 0, 30);
		gbc.gridx = 0;
		gbc.gridy = 0;
		centre.setLayout(gb);
		centre.add(jlabel2,gbc);
		
		gbc.gridy =1;
		centre.add(lbl2,gbc);
		
		
		
		gbc.gridx = 2;
		gbc.gridy = 0;
		
		centre.add(jlabel1,gbc);
		
		
		
		gbc.gridy = 1;
		centre.add(lbl1,gbc);
		add(centre);
		
		setLocationByPlatform(true);  
		setDefaultCloseOperation(AboutDialog.DISPOSE_ON_CLOSE);
		setTitle("Tim Klapa");
		setModalityType(Dialog.ModalityType.APPLICATION_MODAL);
		//setSize(new Dimension(700,400));
		pack();
		setLocationRelativeTo(null);
		pack();
		
		
	}
	

	
	
}
