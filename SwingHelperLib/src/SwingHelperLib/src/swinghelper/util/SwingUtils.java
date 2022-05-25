package swinghelper.util;

import java.awt.Container;
import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.AbstractButton;
import javax.swing.BoxLayout;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import java.lang.reflect.*;

/**A utility class to make constructing containers to add to the processing window easier.
 * @author NumericPrime
 * */
public class SwingUtils {
	public static Object ref=null;
	public static int offset=15;
	public static ActionListener callMethodOnClick(String method,Object refe) {
		final String parse=method;
		final Object parse2=refe;
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Method met=parse2.getClass().getDeclaredMethod(parse, new Class[] {});
					met.setAccessible(true);
					met.invoke(parse2, new Object[] {});
				}catch(Exception ex) {ex.printStackTrace();}
			}};
		
	}
	public static ActionListener callMethodOnClick(String method,Object refe,final int param) {
		final String parse=method;
		final Object parse2=refe;
		return new ActionListener() {

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					Method met=parse2.getClass().getDeclaredMethod(parse, new Class[] {int.class});
					met.setAccessible(true);
					met.invoke(parse2, new Object[] {param});
				}catch(Exception ex) {ex.printStackTrace();}
			}};
		
	}
	/**Calls a certain method if the inputed button is pressed.
	 * @param button  Button that calls the method when pressed
	 * @param method name of the method (mustn't take any inputs)*/
	public static void callMethodOnClick(AbstractButton button,String method) {
		button.addActionListener(callMethodOnClick(method,ref));
	}/***Calls a certain method if the inputed button is pressed.
	* @param button  Button that calls the method when pressed
	* @param method  name of the method must take one int as input
	* @param param   integer that gets put into the method if the button is pressed.
	*/
	public static void callMethodOnClick(AbstractButton button,String method,int param) {
		button.addActionListener(callMethodOnClick(method,ref,param));
	}
	/**Sets the reference where the methods should be looked up.*/
	public static void setReference(Object ref) {
		SwingUtils.ref=ref;
	}
	/**Calls a certain method if the inputed button is pressed. The object where the method can be found will be taken as input
	 * @param button  Button that calls the method when pressed
	 * @param method name of the method (mustn't take any inputs)
	 * @param refe object where the method is searched*/
	public static void callMethodOnClick(AbstractButton button,String method,Object refe) {
		button.addActionListener(callMethodOnClick(method,refe));
	}/***Calls a certain method if the inputed button is pressed. The object where the method can be found will be taken as input
	* @param button  Button that calls the method when pressed
	* @param method  name of the method must take one int as input
	* @param refe  object where the method is searched
	* @param param   integer that gets put into the method if the button is pressed.
	*/
	public static void callMethodOnClick(AbstractButton button,String method,Object refe,int param) {
		button.addActionListener(callMethodOnClick(method,refe,param));
	}
	/**Puts containers in one collumn.*/
	public static JPanel list_col(Container... c) {
		JPanel ret=new JPanel();
		//ret.setLayout(new BoxLayout(ret, BoxLayout.X_AXIS));
		int w=0;
		int h=0;
		for (Container i : c) {
			ret.add(i);
			Dimension d=i.getPreferredSize();
			w+=d.width+offset;
			h=Math.max(h, d.height);
		}
		h=offset;
		ret.setPreferredSize(new Dimension(w, h));
		return ret;
	}
	/**Puts containers in one row.*/
	public static JPanel list_row(Container... c) {
		JPanel ret=new JPanel();
		ret.setLayout(new BoxLayout(ret, BoxLayout.Y_AXIS));
		int w=0;
		int h=0;
		for (Container i : c) {
			ret.add(i);
			Dimension d=i.getPreferredSize();
			h+=d.height+offset;
			w=Math.max(h, d.width);
		}
		ret.setPreferredSize(new Dimension(w, h));
		return ret;
	}
	/**Puts content into a JScrollPane*/
	public static JScrollPane scrollable(Container content) {
		return new JScrollPane(content);
	}
	/**Puts content into a JScrollPane with specified Dimensions*/
	public static JScrollPane scrollable(Container content, int w, int h) {
		JScrollPane ret=new JScrollPane(content);
		ret.setPreferredSize(new Dimension(w, h));
		return ret;
	}
}
