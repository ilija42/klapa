package fileFilters;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class WorkSpaceFileFilter extends FileFilter{

	@Override
	public boolean accept(File f) {
		// TODO Auto-generated method stub
		return ( f.getName().toLowerCase().endsWith(".wsf"));
	}

	@Override
	public String getDescription() {
		// U combo boxu dijaloga postojace ovaj opis i opis All files
		 return "WorkSpace Files (*.wsf)"; 
	}

}
