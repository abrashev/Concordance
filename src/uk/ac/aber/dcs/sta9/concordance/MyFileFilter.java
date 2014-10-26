package uk.ac.aber.dcs.sta9.concordance;

import java.io.File;
import javax.swing.filechooser.FileFilter;


public class MyFileFilter extends FileFilter {

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#accept(java.io.File)
	 */
	public boolean accept(File f) {
		if (f.isDirectory()) return true;
		String name=f.getName();
		int dotloc=name.lastIndexOf(".");
		if (dotloc<0) return false;
		String ext=name.substring(dotloc);
		if (ext.equals(".txt")) return true;
		return false;
	}

	/* (non-Javadoc)
	 * @see javax.swing.filechooser.FileFilter#getDescription()
	 */
	public String getDescription() {
		return "Plain text file .txt file";
	}

}
