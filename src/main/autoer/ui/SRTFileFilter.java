package autoer.ui;

import java.io.File;

import javax.swing.filechooser.FileFilter;

public class SRTFileFilter extends FileFilter {

	public boolean accept(File f) {
		return f.getName().toLowerCase().endsWith(".srt") || f.isDirectory();
	}

	public String getDescription() {
		return "Macro Scripts";
	}
}
