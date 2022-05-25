package swinghelper;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Allows to prevent the SwingManager copying certain properties from the dummy JFrame to the processing window 
 * or turn off the icon or title of the original window to be kept.
 * @author NumericPrime*/
@Retention(RUNTIME)
@Target({TYPE,METHOD})
public @interface RestrictedMerger {
	public boolean transfer_icon() default true;
	public boolean transfer_name() default true;
	public boolean transfer_size() default true;
	/**When set to false:<br>This prevents the dummy JFrame being overridden by the processing window after tranfering the data*/
	public boolean override_generated() default true;
	/**This prevents automaticly copying the dummy-window to the processing window.
	 * When set to false:<br>You however can still do it manually by calling copyDataMerge()*/
	public boolean transfer_data() default true;
	/**When set to false:<br>This prevents the dummy JFrame to be deleted*/
	public boolean delete_old() default true;
}
