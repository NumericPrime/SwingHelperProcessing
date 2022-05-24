package swinghelper.util;

import java.awt.Color;
import java.awt.Font;
import java.lang.reflect.*;

import javax.swing.AbstractButton;
import javax.swing.BorderFactory;

/**Processes the annotations for the JButtons.
 * @autor NumericPrime*/
public class ButtonManager {
	/**Processes the MethodButton annotation*/
	public static void actionList(Object obj) {
		Field[] fields=obj.getClass().getDeclaredFields();
		Class<MethodButton> mkButton=MethodButton.class;
		for(Field f:fields) 
			if(f.isAnnotationPresent(mkButton))
				try {
					f.setAccessible(true);
					MethodButton mbtn=f.getAnnotation(mkButton);
					if(f.get(obj) instanceof AbstractButton) 
					{
						SwingUtils.callMethodOnClick((AbstractButton)f.get(obj), mbtn.method(),obj);
					}else if(f.get(obj) instanceof AbstractButton[]) {
						AbstractButton[] btns=(AbstractButton[]) f.get(obj);
						for(int i=0;i<btns.length;i++) {
							SwingUtils.callMethodOnClick(btns[i], mbtn.method(),obj,i);
						}
					}
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				}
	}
	/**Processes the annotations MethodButton and ColorScheme
	 * @param It takes the object in wich the annotations are used. In most cases inputing <b>this</b> will suffice.*/
	public static void readSettings(Object obj) {
		colorScheme(obj);
		actionList(obj);
	}
	/**Processes the ColorScheme annotation*/
	public static void colorScheme(Object obj) {
	Field[] fields=obj.getClass().getDeclaredFields();
	Class<ColorScheme> mkButton=swinghelper.util.ColorScheme.class;
	for(Field f:fields) 
		if(f.isAnnotationPresent(mkButton))
			try {
				f.setAccessible(true);
				swinghelper.util.ColorScheme mbtn=f.getAnnotation(mkButton);
				if(f.get(obj) instanceof AbstractButton) 
				{
					applyColorSheme((AbstractButton)f.get(obj),mbtn);
				}else if(f.get(obj) instanceof AbstractButton[]) {
					AbstractButton[] btns=(AbstractButton[]) f.get(obj);
					for(int i=0;i<btns.length;i++) {
						applyColorSheme(btns[i],mbtn);
						}
				}
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}		
	}
	public static void applyColorSheme(AbstractButton b,swinghelper.util.ColorScheme sc) {
		if(!sc.background().matches(""))b.setBackground(getColorStr(sc.background()));
		if(!sc.text().matches(""))b.setForeground(getColorStr(sc.text()));
		if(sc.thickness()!=0) {
			Color providecolor=Color.black;
			if(!sc.border().matches("")) providecolor=getColorStr(sc.border());
			b.setBorder(BorderFactory.createLineBorder(providecolor,sc.thickness()));
		}
		if(sc.fontSize()>0) b.setFont(new Font("Arial",Font.PLAIN,sc.fontSize()));
	}
	public static Color getColorStr(String n) {
		float[] t=processColor(n);
		return new Color(t[0],t[1],t[2]);
	}
	public static float[] processColor(String n) {
		String step1[]=n.split(" |,");
		if(step1.length==3) {
			return new float[] {Float.parseFloat(step1[0])/255
					,Float.parseFloat(step1[1])/255,Float.parseFloat(step1[2])/255};
		}else if(n.matches("(?i)red")) return getColor(Color.RED);
		else if(n.matches("(?i)blue")) return getColor(Color.BLUE);
		else if(n.matches("(?i)green")) return getColor(Color.GREEN);
		else if(n.matches("(?i)white")) return getColor(Color.WHITE);
		else if(n.matches("(?i)black")) return getColor(Color.BLACK);
		else if(n.matches("(?i)DARK_GRAY")) return getColor(Color.DARK_GRAY);
		else if(n.matches("(?i)cyan")) return getColor(Color.CYAN);
		else if(n.matches("(?i)MAGENTA")) return getColor(Color.MAGENTA);
		else if(n.matches("(?i)GRAY")) return getColor(Color.GRAY);
		else if(n.matches("(?i)ORANGE")) return getColor(Color.ORANGE);
		else if(n.matches("(?i)YELLOW")) return getColor(Color.YELLOW);
		else if(n.length()>1)
		return new float[] {Float.parseFloat(step1[0])/255
				,Float.parseFloat(step1[0])/255,Float.parseFloat(step1[0])/255};
		
		return new float[] {0,0,0};
	}
	public static float[] getColor(Color c) {
		return new float[] {c.getRed()/255,c.getGreen()/255,c.getBlue()/255};
	}
}
