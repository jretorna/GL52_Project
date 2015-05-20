package fr.utbm.gl52.BusTracer.Utils;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 11 avr. 2015<br>
 * 
 */
public class Constantes {
	public static String TITLE_FRAME_PRINCIPALE = UtilsMessages.getString("Utils.titleFrame"); //$NON-NLS-1$

	public static int widthMap;
	public static int heightMap;

	public static String ERROR_MSG = UtilsMessages.getString("Utils.errorMsg"); //$NON-NLS-1$

	public static String STATE_MAP_FIX = UtilsMessages.getString("Utils.mapStateFix"); //$NON-NLS-1$
	public static String STATE_GPS_FIX = UtilsMessages.getString("Utils.gpsStateFix"); //$NON-NLS-1$
	public static String STATE_RUN_FIX = UtilsMessages.getString("Utils.workingStatusFix"); //$NON-NLS-1$

	public static String STATE_MAP_NOT_LOADING = UtilsMessages.getString("Utils.mapNotLoaded"); //$NON-NLS-1$
	public static String STATE_MAP_LOADING = UtilsMessages.getString("Utils.mapLoaded"); //$NON-NLS-1$
	public static String STATE_MAP_ERROR = UtilsMessages.getString("Utils.mapError"); //$NON-NLS-1$
	public static String EXTENSION_MAP_NOT_OK = UtilsMessages.getString("Utils.mapExtensionErrorMsg"); //$NON-NLS-1$

	public static String STATE_GPS_NOT_LOADING = UtilsMessages.getString("Utils.gpsNotLoaded"); //$NON-NLS-1$
	public static String STATE_GPS_LOADING = UtilsMessages.getString("Utils.gpsLoaded"); //$NON-NLS-1$
	public static String STATE_GPS_ERROR = UtilsMessages.getString("Utils.gpsError"); //$NON-NLS-1$
	public static String EXTENSION_GPS_NOT_OK = UtilsMessages.getString("Utils.gpsExtensionErrorMsg"); //$NON-NLS-1$

	public static String STATE_WORKING_STOPPED = UtilsMessages.getString("Utils.workingStopped"); //$NON-NLS-1$
	public static String STATE_WORKING_RUNNING = UtilsMessages.getString("Utils.workingRunning"); //$NON-NLS-1$
	public static String STATE_WORKING_PAUSE = UtilsMessages.getString("Utils.workingPause"); //$NON-NLS-1$

	public static final int ID_MAP = 1;
	public static final int ID_GPS = 2;
}
