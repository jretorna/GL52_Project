package fr.utbm.gl52.BusTracer.Model;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.swing.event.EventListenerList;

import org.geotools.feature.SchemaException;
import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.Layer;

import com.vividsolutions.jts.geom.Coordinate;

import fr.utbm.gl52.BusTracer.DataCoordinate.DataCoordinateManager;
import fr.utbm.gl52.BusTracer.Map.MapManager;
import fr.utbm.gl52.BusTracer.Model.Zoom.ZoomManager;
import fr.utbm.gl52.BusTracer.Utils.Constantes;
import fr.utbm.gl52.BusTracer.Utils.TraitementFichier;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 17 avr. 2015<br>
 * 
 */
public class GestionnaireSuivi {
	/* ---------------------------- */
	EventListenerList eventListener = null;
	DataCoordinateManager dataManager = null;
	MapManager mapManager = null;
	TraitementFichier treatFile = null;
	ZoomManager zoomManager = null;
	/* ---------------------------- */

	/**
	 * Constructeur par defaut
	 */
	public GestionnaireSuivi() {
		if (this.eventListener != null) {
			this.eventListener = null;
		}
		this.eventListener = new EventListenerList();
		dataManager = new DataCoordinateManager();
		treatFile = new TraitementFichier();
		mapManager = new MapManager();
		zoomManager = new ZoomManager();
	}

	public void loadDataCoordinate() {
		String[] ext = {"shp", "SHP"};
		String selectedFile = treatFile.getFile(ext);
		if (selectedFile != null) {
			fireGpsDatas(dataManager.getCoordinateFromFile(selectedFile));
		} else {
			fireError(Constantes.ID_GPS, Constantes.EXTENSION_GPS_NOT_OK,
					Constantes.STATE_GPS_NOT_LOADING);
		}
	}

	public void chooseMapFile() {
		String[] ext = {"shp", "SHP"};
		String selectedFile = treatFile.getFile(ext);
		if (selectedFile != null) {
			try {
				fireMap(mapManager.setAddLayer(new File(selectedFile)));
			} catch (IOException e) {
				fireMap(null);
			}
		} else {
			fireError(Constantes.ID_MAP, Constantes.EXTENSION_MAP_NOT_OK,
					Constantes.STATE_MAP_NOT_LOADING);
		}
	}
	public void launchAcquisition(final List<Coordinate> list) {
		try {
			firePlay(mapManager.setPointDplctFromList(convertListToTab(list)));
		} catch (SchemaException e) {
			fireError(0, Constantes.PROBLEM_ON_RUN_LAUNCHER, null);
		}
	}

	public void stopAcquisition() {
		fireStop();
	}

	public void pauseAcquisition() {
		firePause();
	}

	/*------------ Zoom methods -----------------*/

	public void zoomInOrOut(final int _sign, final ReferencedEnvelope env) {
		fireZoomInOrOut(zoomManager.ZoomPlusOrMoins(_sign, env));
	}

	/*-------- Private methods ----------------*/

	private Coordinate[] convertListToTab(final List<Coordinate> coords) {
		Coordinate[] coordsTab = new Coordinate[coords.size()];
		int i = 0;
		for (Coordinate c : coords) {
			coordsTab[i] = c;
			i++;
		}
		return coordsTab;
	}

	/*------ Listeners ------*/

	/**
	 * @param listener
	 * 
	 */
	public void addGestionnaireSuiviListener(final GestionnaireSuiviListener listener) {
		this.eventListener.add(GestionnaireSuiviListener.class, listener);
	}
	/**
	 * @param listener
	 * 
	 */
	public void removeGestionnaireSuiviListener(final GestionnaireSuiviListener listener) {
		this.eventListener.remove(GestionnaireSuiviListener.class, listener);
	}

	/*------ GETTERS & SETTERS ------*/

	/*------ FIRES -----*/

	private void fireGpsDatas(final List<Coordinate> coords) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.updateGpsDatas(coords);
		}
	}

	private void firePlay(final Layer layer) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.play(layer);
		}
	}

	private void fireStop() {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.stop();
		}
	}

	private void firePause() {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.pause();
		}
	}

	private void fireMap(final Layer layer) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.updateMap(layer);
		}
	}

	private void fireError(final int _id, final String _msg, final String _state) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.error(_id, _msg, _state);
		}
	}

	/*--------- Zoom Methods ----------*/

	private void fireZoomInOrOut(final ReferencedEnvelope env) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.zoomInOrOut(env);
		}
	}
}
