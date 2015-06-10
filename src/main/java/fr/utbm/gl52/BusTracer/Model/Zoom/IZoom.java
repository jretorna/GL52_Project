package fr.utbm.gl52.BusTracer.Model.Zoom;

import org.geotools.geometry.jts.ReferencedEnvelope;

public interface IZoom {

	public ReferencedEnvelope ZoomPlusOrMoins(	final int _sign,
												final ReferencedEnvelope env);
	public void setOverZoom();
}
