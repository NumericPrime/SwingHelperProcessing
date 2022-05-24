package swinghelper;
import javax.swing.*;
/**
 * This SwingManager allows free placement of the components simular to the <b>null</b> manager.
 * @author NumericPrime
 * */
public class FreePlacement extends SwingManager{
	/**
	 * When using this SwingManager you call this static to get the current one.
	 * */
	public static FreePlacement standart;
	public FreePlacement() {
		super.activate();
		// TODO Auto-generated constructor stub
	}
	static {
		if(Settings.autoAdd)standart=new FreePlacement();
	}
	@Override
	protected void merge() {
		// TODO Auto-generated method stub
		generated=new JFrame();
		generated.setSize(mainWindow.getWidth(),mainWindow.getHeight());
		generated.getContentPane().setLayout(null);
		generated.add(contentOrg, null);
		contentOrg.setBounds(new java.awt.Rectangle(0,0, contSize().width, contSize().height));
	}
	@Override
	public String getManager() {
		return "FreePlacement";
	}
	/**
	 * Adds a Container at the specified coordinates with the preferred size as bounds.
	 * */
	public void add(java.awt.Container cont, int x, int y) {
		  generated.add(cont, null);
		  cont.setBounds(new java.awt.Rectangle(x, y, cont.getPreferredSize().width, cont.getPreferredSize().height));
		  generated.add(contentOrg, null);
		}

	/**
	 * Adds a Container at the specified coordinates with custom bounds.
	 * */
	public void add(java.awt.Container cont, int x, int y, int w, int h) {
	  generated.add(cont, null);
	  cont.setBounds(new java.awt.Rectangle(x, y, w, h));
	  generated.add(contentOrg, null);
	}
	/**
	 * Moves a Container to the specified coordinates with the preferred size as bounds.
	 * */
	public void move(java.awt.Container cont, int x, int y) {
		  cont.setBounds(new java.awt.Rectangle(x, y, cont.getPreferredSize().width, cont.getPreferredSize().height));
		}
	/**
	 * Moves a Container to the specified coordinates with custom bounds.
	 * */
	public void move(java.awt.Container cont, int x, int y,int w,int h) {
		  cont.setBounds(new java.awt.Rectangle(x, y, w,h));
		}

}
