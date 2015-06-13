package fr.utbm.gl52.BusTracer.Model.Zoom;

import org.geotools.geometry.jts.ReferencedEnvelope;

import com.vividsolutions.jts.geom.Coordinate;

public interface IZoom {

	public ReferencedEnvelope ZoomPlusOrMoins(	final int _sign,
												final ReferencedEnvelope env);
	public ReferencedEnvelope setOverZoom(	ReferencedEnvelope env,
											final double widthMap,
											final double heightMap);

	public ReferencedEnvelope zoomOnBus(ReferencedEnvelope env,
										Coordinate coordA,
										Coordinate coordB);
}
