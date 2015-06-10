package fr.utbm.gl52.BusTracer.Model.Zoom;

import org.geotools.geometry.jts.ReferencedEnvelope;

public class ZoomManager extends AbstractZoom {

	@Override
	public ReferencedEnvelope ZoomPlusOrMoins(	final int _sign,
												final ReferencedEnvelope env) {
		double zoom = 0.1;
		int sign = _sign;
		iteration += -sign;
		if (iteration < maximumZoom && iteration > minimumZoom) {
			double width = env.getWidth();
			double height = env.getHeight();
			double expX = width * zoom * sign;
			double expY = height * zoom * sign;
			env.expandBy(expX, expY);
		} else {
			if (iteration > maximumZoom)
				iteration = 10;
			if (iteration < minimumZoom)
				iteration = -10;
		}
		return env;
	}
	@Override
	public void setOverZoom() {
		iteration = 0;

	}

}
