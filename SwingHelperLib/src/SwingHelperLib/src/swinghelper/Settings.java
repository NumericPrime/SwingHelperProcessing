package swinghelper;

/**This class will be packed with new settings until now it only has two
 * @author NumericPrime*/
public class Settings {
	public static boolean autoAdd=true;
	public static String tabName="Result";
	/**
	 * Turns auto applying SwingManagers on/off.
	 * This applies to the FreePlacement, SidePlacement and TabPlacement manager.
	 * */
	public static void autoMerge(boolean val) {
		autoAdd=val;
	}
	/**Pins the tab name of the processing content when using the TabPlacement manager.*/
	public static void tabName(String val) {
		tabName=val;
	}
}
