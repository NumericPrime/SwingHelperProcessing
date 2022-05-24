package swinghelper;

import java.awt.*;

import javax.swing.*;

/**Adds the processing content to a tab as well as other content to other tabs.
 * The tab name of the tab with the processing content can be altered in Settings.
 * @author NumericPrime*/
public class TabPlacement extends SwingManager {
	public static TabPlacement standart;
	public TabPlacement() {
		super.activate();
	}
	/**You can use this constructor to add a custom name for the tab with the processing content. If autoMerge from Settings is turned off.*/
	public TabPlacement(String st) {
		stn=st;
		super.activate();
	}
	static {
		
		if(Settings.autoAdd) standart=new TabPlacement(Settings.tabName);
		//if(Settings.autoAdd) standart.stn="Result";
	}
	public String stn="Result";
	public JTabbedPane jtp=null;
	@Override
	public void merge() {
		this.jtp=new JTabbedPane();
		generated=new JFrame();
		if(stn==null) stn=Settings.tabName;
		if(stn==null) stn="Result";
		/**/
		System.out.println(jtp);
		generated.setSize(mainWindow.getWidth(),
		mainWindow.getHeight()+20);
		jtp.setPreferredSize(new Dimension(mainWindow.getWidth(),
		mainWindow.getHeight()+20));
		generated.add(jtp,"Center");
		jtp.addTab(stn, contentOrg);
	}
	public void rebuild(String n) {
		stn=n;
		merge();
	}
	/**
	 * Adds the content to a new tab with the specified name.
	 * */
	public void add(Component c,String w) {
		System.out.println(c);
		System.out.println(w);
		jtp=(JTabbedPane) generated.getContentPane().getComponent(0);
		jtp.addTab(w, c);
	}
	public JTabbedPane getTabbedPane() {
		return (JTabbedPane) generated.getContentPane().getComponent(0);
	}
	@Override
	public String getManager() {
		return "TabPlacement";
	}

}
