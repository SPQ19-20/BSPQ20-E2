package es.deusto.BSPQ20_E2.Netflix.client;

import java.net.URLClassLoader;
import java.util.ResourceBundle;

/**
 * This is the class containing the Internalization resources, in order to access them in an easy way.
 * The class saves instances such as the tags of the languages available, the selected language (resourceBundle) 
 * and the path of the folder which contains the files of the languages (loader)
 * @category Internationalization
 * @author Inigo Orue
 */

public class Internationalization {
	public static String[] Idiomas = { "en", "es", "eus", "fr", "de" };
	public static ResourceBundle resourceBundle;
	public static URLClassLoader loader;

}
