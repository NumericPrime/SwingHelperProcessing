package swinghelper;

import java.awt.*;

import javax.swing.*;

/**This class finds the processing window and gives it to the SwingManagers.
 * @autor NumericPrime*/
public class WindowFinder {
	/**The processing window is stored here and can be used by the SwingManagers.*/
	public static JFrame mainWindow=null;
	/**Here the processing content is stored and can be used by the SwingManagers. It matches the contentPane of mainWindow. */
	public static Container contentOrg=null;
	/**Searches for the oldest active JFrame, wich is usually the processing window.*/
	public static void refresh() {
		mainWindow=null;
		java.awt.Window win[]=java.awt.Window.getWindows();
		for(int i=0;i<win.length&&mainWindow==null;i++) if(win[i] instanceof JFrame) mainWindow=(JFrame) win[i];
		contentOrg=mainWindow.getContentPane();
		
	}
	/**Returns the preferred size of the processing content.*/
	public static Dimension contSize() {
		return contentOrg.getPreferredSize();
	}

	/**Returns the size of the processing window.*/
	public static Dimension winSize() {
		return mainWindow.getSize();
	}
	static {
		refresh();
	}

}
