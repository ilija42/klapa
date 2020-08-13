package FileHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;
import javax.swing.JOptionPane;

import fileFilters.WorkSpaceFileFilter;
import interfaces.ISerialization;
import tree.project.Project;
import tree.workspace.WorkSpace;
import view.frame.MainFrame;

public class WorkSpaceSerializationHandler implements ISerialization{
	private File lastPath = null;
	private static WorkSpaceSerializationHandler instance = null;
	
	@Override
	public void saveAs() {
		
		File wsFile;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkSpaceFileFilter());
		
		WorkSpace ws = MainFrame.getInstance().getWorkSpace();
		
		
		
		 if(jfc.showSaveDialog((MainFrame.getInstance()))==JFileChooser.APPROVE_OPTION){
		   wsFile = jfc.getSelectedFile();    
	       wsFile = new File(wsFile.getAbsolutePath()+".wsf");   	//dodajemo ekstenziju na file
	       lastPath = wsFile.getAbsoluteFile();
		 } else return;
		             
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(wsFile));
			os.writeObject(ws);
			ws.setSaved(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
	}

	@Override
	public Object open() {
		
		Object[] options = {"New Workspace","Import Workspace"};
		WorkSpace ws = null;
		while(true) {			
			if(JOptionPane.showOptionDialog(MainFrame.getInstance(),
					"Da li zelite da ucitate Workspace ili da otvorite novi?",
					"", 
					JOptionPane.YES_NO_CANCEL_OPTION,
					JOptionPane.QUESTION_MESSAGE,
					null,
					options,
					options[1]) == JOptionPane.YES_NO_CANCEL_OPTION) {
				
					JFileChooser fileChooser = new JFileChooser();
					fileChooser.setFileFilter(new WorkSpaceFileFilter());
					fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
				
					if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
					
						try(ObjectInputStream os = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))){
							try {
								ws = ((WorkSpace)os.readObject());
							}
							catch (NullPointerException e) {
								e.printStackTrace();
							}	
							catch (ClassNotFoundException e) {
								e.printStackTrace();
							}
						
						} 
						
						catch (FileNotFoundException e) {
							e.printStackTrace();
						}
						catch (IOException e) {
						e.printStackTrace();
						}
						
						return ws;	
					}
					else continue;
				}
			
			else return null; 
		}	
	}
	
	public static WorkSpaceSerializationHandler getInstance() {
		if (instance == null) {
			instance = new WorkSpaceSerializationHandler();
		}
		return instance;
	}

	@Override
	public void save() {
		File wsFile;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new WorkSpaceFileFilter());
		
		WorkSpace ws = MainFrame.getInstance().getWorkSpace();
		if(ws.isSaved()) {
			JOptionPane.showMessageDialog(MainFrame.getInstance(),"Nista se nije promenilo od prethodnog cuvanja");
			return;
		}
		
		if(lastPath == null) {
		 if(jfc.showSaveDialog((MainFrame.getInstance()))==JFileChooser.APPROVE_OPTION){
		   wsFile = jfc.getSelectedFile();    
	       wsFile = new File(wsFile.getAbsolutePath()+".wsf");   	//dodajemo ekstenziju na file
	       lastPath = wsFile.getAbsoluteFile();
	       System.out.println(wsFile);
	    } else return;
		  }               
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(lastPath));
			os.writeObject(ws);
			ws.setSaved(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}		
	}

	@Override
	public void save(Project project) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void saveAs(Project project) {
		// TODO Auto-generated method stub
		
	}
	
}
