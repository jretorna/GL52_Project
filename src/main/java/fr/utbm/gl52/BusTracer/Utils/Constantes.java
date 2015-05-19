package fr.utbm.gl52.BusTracer.Utils;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 11 avr. 2015<br>
 * 
 */
public class Constantes {
	public static String TITLE_FRAME_PRINCIPALE = Messages.getString("Utils.titleFrame"); //$NON-NLS-1$

	public static int widthMap;
	public static int heightMap;

	public static String ERROR_MSG = Messages.getString("Utils.errorMsg"); //$NON-NLS-1$

	public static String STATE_MAP_FIX = Messages.getString("Utils.mapStateFix"); //$NON-NLS-1$
	public static String STATE_GPS_FIX = Messages.getString("Utils.gpsStateFix"); //$NON-NLS-1$
	public static String STATE_RUN_FIX = Messages.getString("Utils.workingStatusFix"); //$NON-NLS-1$

	public static String STATE_MAP_NOT_LOADING = Messages.getString("Utils.mapNotLoaded"); //$NON-NLS-1$
	public static String STATE_MAP_LOADING = Messages.getString("Utils.mapLoaded"); //$NON-NLS-1$
	public static String STATE_MAP_ERROR = Messages.getString("Utils.mapError"); //$NON-NLS-1$
	public static String EXTENSION_MAP_NOT_OK = Messages.getString("Utils.mapExtensionErrorMsg"); //$NON-NLS-1$

	public static String STATE_GPS_NOT_LOADING = Messages.getString("Utils.gpsNotLoaded"); //$NON-NLS-1$
	public static String STATE_GPS_LOADING = Messages.getString("Utils.gpsLoaded"); //$NON-NLS-1$
	public static String STATE_GPS_ERROR = Messages.getString("Utils.gpsError"); //$NON-NLS-1$
	public static String EXTENSION_GPS_NOT_OK = Messages.getString("Utils.gpsExtensionErrorMsg"); //$NON-NLS-1$

	public static String STATE_WORKING_STOPPED = Messages.getString("Utils.workingStopped"); //$NON-NLS-1$
	public static String STATE_WORKING_RUNNING = Messages.getString("Utils.workingRunning"); //$NON-NLS-1$
	public static String STATE_WORKING_PAUSE = Messages.getString("Utils.workingPause"); //$NON-NLS-1$

	public static final int ID_MAP = 1;
	public static final int ID_GPS = 2;
}
