package FileHandling;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.JFileChooser;

import fileFilters.ProjectFileFilter;
import interfaces.ISerialization;
import tree.project.Project;
import view.frame.MainFrame;

public class ProjectSerializationHandler implements ISerialization{
	
	
	private static ProjectSerializationHandler instance = null;

	@Override
	public void saveAs(Project project) {
		
		File projectFile;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ProjectFileFilter());
		
		//if(!project.isChanged()) {
		//	JOptionPane.showMessageDialog(MainFrame.getInstance(),"Nista se nije promenilo od prethodnog cuvanja");
		//	return;
		//} dodati proveru
		 
		if(jfc.showSaveDialog((MainFrame.getInstance()))==JFileChooser.APPROVE_OPTION){
		   projectFile = jfc.getSelectedFile();    
	       projectFile = new File(projectFile.getAbsolutePath()+".prj");  
	       project.setLastpath(projectFile.getAbsoluteFile());
	    } else return; 
		                 
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(projectFile));
			os.writeObject(project);
			//project.setSaved(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}	
	}

	@Override
	public Object open() {
		
		Project project = null;
		JFileChooser fileChooser = new JFileChooser();
		fileChooser.setFileFilter(new ProjectFileFilter());
		fileChooser.setCurrentDirectory(new File(System.getProperty("user.home")));
			
		if (fileChooser.showOpenDialog(MainFrame.getInstance()) == JFileChooser.APPROVE_OPTION) {
					
			try(ObjectInputStream os = new ObjectInputStream(new FileInputStream(fileChooser.getSelectedFile()))){
				try {
					project = ((Project)os.readObject());
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
			return project;	
		}
		else return null;			
	}
	
	public static ProjectSerializationHandler getInstance() {
		if (instance == null) {
			instance = new ProjectSerializationHandler();
		}
		return instance;
	}

	@Override
	public void save() {
		
	}

	@Override
	public void saveAs() {
		
	}

	@Override
	public void save(Project project) {
		File projectFile;
		JFileChooser jfc = new JFileChooser();
		jfc.setFileFilter(new ProjectFileFilter());
		
		//if(!project.isChanged()) {
		//	JOptionPane.showMessageDialog(MainFrame.getInstance(),"Nista se nije promenilo od prethodnog cuvanja");
		//	return;
		//} dodati proveru
		if(project.getLastpath() == null) {
			if(jfc.showSaveDialog((MainFrame.getInstance()))==JFileChooser.APPROVE_OPTION){
		   projectFile = jfc.getSelectedFile();    
	       projectFile = new File(projectFile.getAbsolutePath()+".prj"); 
	       project.setLastpath(projectFile.getAbsoluteFile());
			} else return; 
		}
		
		                 
	    ObjectOutputStream os;
		try {
			os = new ObjectOutputStream(new FileOutputStream(project.getLastpath()));
			os.writeObject(project);
			//project.setSaved(true);
		} catch (FileNotFoundException e1) {
			e1.printStackTrace();
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		
	}

}
