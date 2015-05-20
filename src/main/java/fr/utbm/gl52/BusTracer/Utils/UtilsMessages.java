package fr.utbm.gl52.BusTracer.Utils;

import java.util.MissingResourceException;
import java.util.ResourceBundle;

public class UtilsMessages {
	private static final String BUNDLE_NAME = "fr.utbm.gl52.BusTracer.Utils.utils"; //$NON-NLS-1$

	private static final ResourceBundle RESOURCE_BUNDLE = ResourceBundle.getBundle(BUNDLE_NAME);

	private UtilsMessages() {
	}
	public static String getString(final String key) {
		try {
			return RESOURCE_BUNDLE.getString(key);
		} catch (MissingResourceException e) {
			return '!' + key + '!';
		}
	}
}
