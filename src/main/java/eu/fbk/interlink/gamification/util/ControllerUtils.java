package eu.fbk.interlink.gamification.util;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

public class ControllerUtils {

    public static String decodePathVariable(String variable) {
        try {
            variable = URLDecoder.decode(variable, "UTF-8");
            return variable;
        } catch (UnsupportedEncodingException e) {
            throw new IllegalArgumentException(String.format("%s is not UTF-8 encoded", variable));
        }
    }
    
	public static boolean isEmpty(String value) {
		boolean result = true;
		if ((value != null) && (!value.isEmpty())) {
			result = false;
		}
		return result;
	}
}
