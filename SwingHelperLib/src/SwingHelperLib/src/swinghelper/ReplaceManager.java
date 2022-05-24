package swinghelper;
import javax.swing.*;
/**Replaces the processing window with a custom one
 * @author NumericPrime*/
@RestrictedMerger(
		transfer_data=false,
		transfer_name=false,
		transfer_icon=false)
public class ReplaceManager extends SwingManager{

	public ReplaceManager() {
		super();
	}

	public ReplaceManager(JFrame jf) {
		super();
		copyDataMerge(jf);
	}
	@Override
	protected void merge() {
	}
	/**You can use this method to replace the original window with a cusom one*/
	public void replace(JFrame jf) {
		copyDataMerge(jf);
	}
	/**You can use this constructor to replace the original window with a cusom one*/
	
	public static ReplaceManager replaceOld(JFrame jf) {
		return new ReplaceManager(jf);
	}
}
