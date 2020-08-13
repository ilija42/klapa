package actions;
import javax.swing.AbstractAction;
import javax.swing.Icon;
import javax.swing.ImageIcon;


public abstract class MyAbstractAction extends AbstractAction{
	
	
	private static final long serialVersionUID = 1L;

	/**
	 * 
	 * Ukoliko nasledimo klasu AbstractAction mi kreiramo Listener-a 
	 * kojeg mozemo prikljuciti komponentama. 
	 * Klasa je apstraktna jer ne implementira metodu actionPerformed()
	 * Ovu klasu nasledjuju sve akcije sa menija i toolbara 
	 * 
	 */
	
	  // Kreira ikonu na osnovu zadatog imena
	     
		public Icon loadIcon(String fileName){
			
			ImageIcon icon = new ImageIcon(fileName);

			return icon;
		}

	

}
