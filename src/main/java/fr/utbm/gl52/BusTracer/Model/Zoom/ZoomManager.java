package fr.utbm.gl52.BusTracer.Model.Zoom;

import org.geotools.geometry.jts.ReferencedEnvelope;

import com.vividsolutions.jts.geom.Coordinate;

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
	public ReferencedEnvelope setOverZoom(	final ReferencedEnvelope env,
											final double widthMap,
											final double heightMap) {
		iteration = 0;
		env.expandBy(widthMap, heightMap);
		return env;
	}

	@Override
	public ReferencedEnvelope zoomOnBus(final ReferencedEnvelope env,
										final Coordinate coordA,
										final Coordinate coordB) {
		if (coordA.x <= coordB.x && coordA.y <= coordB.y) {
			env.init(coordA.x, coordB.x, coordA.y, coordB.y);
		}
		if (coordA.x <= coordB.x && coordA.y >= coordB.y) {
			env.init(coordA.x, coordB.x, coordB.y, coordA.y);
		}
		if (coordA.x >= coordB.x && coordA.y <= coordB.y) {
			env.init(coordB.x, coordA.x, coordA.y, coordB.y);
		}
		if (coordA.x >= coordB.x && coordA.y <= coordB.y) {
			env.init(coordA.x, coordB.x, coordA.y, coordB.y);
		}
		return env;
	}

}
