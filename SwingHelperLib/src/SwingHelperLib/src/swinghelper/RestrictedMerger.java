package swinghelper;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Allows to prevent the SwingManager copying certain properties from the dummy JFrame to the processing window 
 * or turn off the icon or title of the original window to be kept.*/
@Retention(RUNTIME)
@Target({TYPE,METHOD})
public @interface RestrictedMerger {
	public boolean transfer_icon() default true;
	public boolean transfer_name() default true;
	public boolean transfer_size() default true;
	public boolean override_generated() default true;
	public boolean transfer_data() default true;
	public boolean delete_old() default true;
}
