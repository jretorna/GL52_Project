package fr.utbm.gl52.BusTracer.Model;

import java.util.EventListener;

import org.geotools.map.Layer;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 17 avr. 2015<br>
 * 
 */
public interface GestionnaireSuiviListener extends EventListener {

	/* Launch acquisition */
	void play();
	/* stop acquisition */
	void stop();
	/* pause acquisition */
	void pause();
	/* Load Gps Datas */
	void updateGpsDatas(final String uri);
	/* Load Map */
	void updateMap(final Layer layer);
	/* Error */
	void error(int _id, String _msg, String _state);

}
