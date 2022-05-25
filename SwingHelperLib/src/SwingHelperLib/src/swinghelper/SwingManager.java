package swinghelper;


import java.lang.reflect.Method;

import javax.swing.JFrame;

import swinghelper.util.ButtonManager;

/**
 * Main class of the library
 * All merger-classes extend this class. It will do most of the work regarding adding swing components to Processing.
 * @author NumericPrime
 * @since 1.0
 */
public abstract class SwingManager extends WindowFinder{
	/**
	 * Dummy JFrame you edit 
	 * This is the JFrame you edit in the merge method. Icon and title will be provided from the mainWindow. Everything else will be copied from this JFrame to the mainWindow.
	 * @author NumericPrime
	 * */
	public JFrame generated;
	public SwingManager(){
		activate();
		ButtonManager.readSettings(this);
	}
	/**If a SwingManager hasn't be initialized because of e.g. not having a custom constructor you can call this method to apply the SwingManager.*/
	public void load() {
		activate();
		ButtonManager.readSettings(this);		
	}
	RestrictedMerger rm=null;
	public static String activeManager=null;
	public void activate() {
		if(activeManager==null) {
			try {
				if(getClass().isAnnotationPresent(RestrictedMerger.class))
				rm=getClass().getAnnotation(RestrictedMerger.class);
				Method m=this.getClass().getDeclaredMethod("merge");
				m.setAccessible(true);
				if(m.isAnnotationPresent(RestrictedMerger.class)) {
					rm=m.getAnnotation(RestrictedMerger.class);
				}
				if(m.isAnnotationPresent(GenerationSettings.class)) {
					GenerationSettings gs=m.getAnnotation(GenerationSettings.class) ;
					genWindowAnnotation(gs);
				}
				else if(getClass().isAnnotationPresent(GenerationSettings.class)) {
					GenerationSettings gs=getClass().getAnnotation(GenerationSettings.class) ;
					genWindowAnnotation(gs);
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			merge();
			if(rm==null)copyDataMerge(generated); else 
				if(rm.transfer_data())copyDataMerge(generated);
			activeManager=getManager();
		}
	}
	/**This method processes the annotation for GenerationSettings*/
	public void genWindowAnnotation(GenerationSettings gs) {
		int w=mainWindow.getWidth();
		int h=mainWindow.getHeight();
		if(gs.width()>=0) w=gs.width();
		if(gs.height()>=0) h=gs.height();
		w+=gs.offsetWidth();
		h+=gs.offsetHeight();
		w+=gs.widthOffset();
		h+=gs.heightOffset();
		genWindow(w,h);
		generated.setTitle(gs.name());
		generated.setResizable(gs.resizable());
	}
	/**
	 * @deprecated this method was used in an old version of the merger wich replaced the old window with the new one.
	 * */
	@Deprecated
	public void copyData(JFrame target) {
		target.setIconImage(mainWindow.getIconImage());
		target.setTitle(mainWindow.getTitle());
		target.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
		target.setVisible(true);
		mainWindow.setVisible(false);
	}
	/**
	 * This method does all the work processing the dummy JFrame into the new the new JFrame.
	 * */
	public void copyDataMerge(JFrame target) {
		if(rm==null) {
			target.setLocation(-2*target.getWidth(),-2*target.getHeight());
			target.setVisible(true);
			target.setVisible(false);
			target.setIconImage(mainWindow.getIconImage());
			target.setTitle(mainWindow.getTitle());
			target.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			mainWindow.setContentPane(target.getContentPane());
			mainWindow.setMinimumSize(target.getMinimumSize());
			mainWindow.setMaximumSize(target.getMaximumSize());
			mainWindow.setResizable(target.isResizable());
			mainWindow.setJMenuBar(target.getJMenuBar());
			mainWindow.setSize(target.getSize());
			target.dispose();
			generated=mainWindow;
		}else {
			target.setLocation(-1000,-1000);
			target.setVisible(true);
			target.setVisible(false);
			if(rm.transfer_icon())target.setIconImage(mainWindow.getIconImage());
			else mainWindow.setIconImage(target.getIconImage());
			if(rm.transfer_name())target.setTitle(mainWindow.getTitle());
			else mainWindow.setTitle(target.getTitle());
			target.setDefaultCloseOperation(javax.swing.JFrame.EXIT_ON_CLOSE);
			mainWindow.setContentPane(target.getContentPane());
			mainWindow.setJMenuBar(target.getJMenuBar());
			if(rm.transfer_size()) {
				mainWindow.setMinimumSize(target.getMinimumSize());
				mainWindow.setMaximumSize(target.getMaximumSize());
				mainWindow.setResizable(target.isResizable());
				mainWindow.setSize(target.getSize());
			}
			if(rm.override_generated()) generated=mainWindow;
			if(rm.delete_old())target.dispose();
		}
	}
	/**
	 * This method generates the dummy JFrame with a certain size and the processing content in the middle
	 * */
	public void genWindow(int width,int height) {
		generated=new JFrame();
		generated.setSize(width,height);
		generated.add(contentOrg);
	}
	/**
	 * This method also generates the dummy JFrame based of the size of the processing window.
	 * */
	public void genWindowRelOld(int offsetWidth,int offsetHidth) {
		generated=new JFrame();
		int w=generated.getWidth();
		int h=generated.getHeight();
		generated.setSize(w+offsetWidth,h+offsetHidth);
		generated.add(contentOrg);
	}
	/**This method let's you specify a custom JFrame as the mainWindow*/
	public void reset(JFrame jf) {
		mainWindow=jf;
		contentOrg=mainWindow.getContentPane();
		activeManager=null;
	}
	public String getManager() {
		return getClass().getName();
	}/**
	 * Here the content to be added to the Processing window is specified.
	 * Here you can edit a dummy-JFrame <b>generated</b>.<br>
	 * Name and icon will be copied from the processing window.<br>
	 * You can however view and use the original window <b>mainWindow</b><br>
	 * Don't forget adding the processing-content back at some point.
	 * It has been put in <b>contentOrg</b>
	 * @author NumericPrime
	 * */
	protected abstract void merge();
}