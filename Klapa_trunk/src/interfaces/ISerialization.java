package interfaces;

import tree.project.Project;

public interface ISerialization {
	void save();
	void save(Project project);
	void saveAs();
	void saveAs(Project project);
	Object open();
	
}
