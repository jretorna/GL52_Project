package fr.utbm.gl52.BusTracer.Model;

import java.io.File;
import java.io.IOException;

import javax.swing.JFileChooser;
import javax.swing.event.EventListenerList;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;

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
	/* ---------------------------- */

	/**
	 * Constructeur par d�faut
	 */
	public GestionnaireSuivi() {
		if (this.eventListener != null) {
			this.eventListener = null;
		}
		this.eventListener = new EventListenerList();

	}

	public void chooseGpsDataFile() {
		JFileChooser choix = new JFileChooser();
		int retour = choix.showOpenDialog(null);
		if (retour == JFileChooser.APPROVE_OPTION) {
			// TODO JR retirer ce com
			System.out.println(choix.getSelectedFile().getAbsolutePath());
			// TODO JR - modifier l'extension
			String[] ext = {"png"};
			if (TraitementFichier.hasCorrectExtension(
					choix.getSelectedFile().getAbsolutePath().toString(), ext)) {
				fireGpsDatas(choix.getSelectedFile().getAbsolutePath());
			} else {
				fireError(Constantes.ID_GPS, Constantes.EXTENSION_GPS_NOT_OK,
						Constantes.STATE_GPS_NOT_LOADING);
			}
		} else {
			fireGpsDatas(Constantes.STATE_GPS_NOT_LOADING);
		}
	}
	public void chooseMapFile() {
		JFileChooser choix = new JFileChooser();
		int retour = choix.showOpenDialog(null);
		if (retour == JFileChooser.APPROVE_OPTION) {
			// TODO JR retirer ce com
			System.out.println(choix.getSelectedFile().getAbsolutePath());
			String[] ext = {"shp", "SHP"};
			// if correct, we load the map and send a fire to actualise to the
			// FramePrincipale
			if (TraitementFichier.hasCorrectExtension(
					choix.getSelectedFile().getAbsolutePath().toString(), ext)) {
				File file = new File(
						choix.getSelectedFile().getAbsolutePath().toString());
				try {
					fireMap(setAddLayer(file));
				} catch (IOException e) {
					fireMap(null);
				}
			} else {
				fireError(Constantes.ID_MAP, Constantes.EXTENSION_MAP_NOT_OK,
						Constantes.STATE_MAP_NOT_LOADING);
			}
		}
		/*-----------------*/
		/*-------------------*/
		/*---------------------*/
		// EventQueue.invokeLater(new Runnable() {
		// public void run() {
		// try {
		// ShapeViewControl svc = new ShapeViewControl();
		// svc.fileChooser();
		// } catch (IOException ex) {
		// Logger.getLogger(Main.class.getName()).log(Level.SEVERE,
		// null, ex);
		// JOptionPane.showMessageDialog(null,
		// ex.getLocalizedMessage(), "ShapeView - Error",
		// JOptionPane.ERROR_MESSAGE);
		// }
		// }
		// });

	}
	private Layer setAddLayer(final File file) throws IOException {
		FileDataStore store = null;
		SimpleFeatureSource featureSource;
		store = FileDataStoreFinder.getDataStore(file);
		featureSource = store.getFeatureSource();
		Style style = SLD.createSimpleStyle(featureSource.getSchema());
		return new FeatureLayer(featureSource, style);
	}

	public void launchAcquisition() {
		firePlay();
	}

	public void stopAcquisition() {
		fireStop();
	}

	public void pauseAcquisition() {
		firePause();
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

	private void fireGpsDatas(final String uri) {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.updateGpsDatas(uri);
		}
	}

	private void firePlay() {
		GestionnaireSuiviListener[] listenerList = this.eventListener.getListeners(GestionnaireSuiviListener.class);
		for (GestionnaireSuiviListener listener : listenerList) {
			listener.play();
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
}
