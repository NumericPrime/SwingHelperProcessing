package swinghelper;

import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.RUNTIME;

import java.lang.annotation.Retention;
import java.lang.annotation.Target;

/**Allows to easily generate an JFrame with the specified properties as <b>Generated</b>
 * <br>You can only attach this to a class or the merge method of a SwingMerger.
 * @autor NumericPrime
 * */
@Retention(RUNTIME)
@Target({METHOD,TYPE})
public @interface GenerationSettings {
	/**
	 * Width relative to the processing windows width
	 * */
	public int offsetWidth() default 0;
	/**
	 * Height relative to the processing windows height
	 * */
	public int offsetHeight() default 0;
	public int heightOffset() default 0;
	public int widthOffset() default 0;
	/**
	 * When unedited it uses the processing windows width
	 * */
	public int width() default -1;
	/**
	 * When unedited it uses the processing windows height
	 * */
	public int height() default -1;
	public String name() default "";
	public boolean resizable() default true;
}
