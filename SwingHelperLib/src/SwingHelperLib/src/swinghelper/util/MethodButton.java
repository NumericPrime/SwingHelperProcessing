package swinghelper.util;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Allows adding methods to be executed when a button is pressed.
 * The method has to be in the same class as the annotation.<br>
 * When applied on a single button the method must take no inputs.<br>
 * When applied on a array of buttons the method must take one int input in wich the index of the button in the array will be put in.
 * */
@Retention(RUNTIME)
@Target(FIELD)
public @interface MethodButton {
	public String method();
}
