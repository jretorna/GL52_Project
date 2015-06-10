package fr.utbm.gl52.BusTracer.Model;

import java.util.EventListener;
import java.util.List;

import org.geotools.geometry.jts.ReferencedEnvelope;
import org.geotools.map.Layer;

import com.vividsolutions.jts.geom.Coordinate;

/**
 * @version 01.00.00
 * 
 * @author Jeremy RETORNAZ - V01.00.00
 * @date 17 avr. 2015<br>
 * 
 */
public interface GestionnaireSuiviListener extends EventListener {

	/* Launch acquisition */
	void play(final Layer layer);
	/* stop acquisition */
	void stop();
	/* pause acquisition */
	void pause();
	/* Load Gps Datas */
	void updateGpsDatas(final List<Coordinate> coords);
	/* Load Map */
	void updateMap(final Layer layer);
	/* Error */
	void error(int _id, String _msg, String _state);
	/* Zoom In or Out */
	void zoomInOrOut(ReferencedEnvelope env);

}
