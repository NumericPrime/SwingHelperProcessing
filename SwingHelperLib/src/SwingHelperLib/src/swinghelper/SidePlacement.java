package swinghelper;

import java.awt.*;

import java.awt.event.*;

import javax.swing.*;

import processing.core.PConstants;
/**Allows placing content to the sides of the processing-content.
 * @author NumericPrime*/
public class SidePlacement extends SwingManager {
	/**The active SidePlacement*/
	public static SidePlacement standart;
	int w,h,w2;

	public SidePlacement() {
		super.activate();
	}
	static {
		if(Settings.autoAdd)standart=new SidePlacement();
	}
	public final Container[] added=new Container[2];
	/**
	 * Adds content to the sides of the processing content
	 * @param where This method uses the container to be added and takes the location in form of an integer.
	 * It accepts LEFT and RIGHT directly typed in the Processing IDE. 
	 * */
	public void add(Container c,int where) {
		String wh="East";
		if(where==PConstants.RIGHT) wh="West";
		if(wh.contentEquals("East")) added[0]=c; else added[1]=c;
		w2=c.getPreferredSize().width;
		generated.setSize(generated.getWidth()+w2,h);
		generated.add(c,wh);
		generated.setMinimumSize(new Dimension(generated.getWidth()+w2,h));
		final Container p=c;
		generated.getContentPane().addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    int notnulls=0;
		    if(added[0]!=null) notnulls++;
		    if(added[1]!=null) notnulls++;
		    generated.setSize(
		    		generated.getWidth()
		    		,h);
		    p.setPreferredSize(new Dimension((generated.getWidth()-w)/notnulls,h));
		    }
		  }
		);/*
	    int notnulls=0;
	    if(added[0]!=null) notnulls++;
	    if(added[1]!=null) notnulls++;
	    generated.setSize(
	    		generated.getWidth()
	    		,h);
	    if(added[0]!=null)added[0].setPreferredSize(new Dimension((generated.getWidth()-w)/notnulls,h));
	    if(added[1]!=null)added[0].setPreferredSize(new Dimension((generated.getWidth()-w)/notnulls,h));
	    */
	}
	@Override
	protected void merge() {
		// TODO Auto-generated method stub
		generated=new JFrame();
		w=mainWindow.getWidth();
		h=mainWindow.getHeight();
		generated.setSize(winSize());
		generated.setMinimumSize(new Dimension(w,h));
		generated.add(contentOrg,"Center");
		generated.getContentPane().addComponentListener(new ComponentAdapter() {
		    public void componentResized(ComponentEvent e) {
		    generated.setSize(
		    		generated.getWidth()
		    		,h);
		    }
		  }
	);
	}

	@Override
	public String getManager() {
		// TODO Auto-generated method stub
		return "SidePlacement";
	}

}
