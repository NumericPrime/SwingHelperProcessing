package swinghelper.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Allow formatting buttons. Only works on buttons and 1d arrays of buttons.
 * @author NumericPrime*/
@Retention(RUNTIME)
@Target(FIELD)
public @interface ColorScheme {
	String background() default "";
	String text() default "";
	String border() default "";
	int  thickness() default 0;
	/**Even though it says the font size is -1 it will just keep the original font size unless a value >0 is given.*/
	int  fontSize() default -1;
}
