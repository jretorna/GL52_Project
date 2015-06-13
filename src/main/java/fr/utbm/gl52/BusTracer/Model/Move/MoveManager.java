package fr.utbm.gl52.BusTracer.Model.Move;

import org.geotools.geometry.jts.ReferencedEnvelope;

import fr.utbm.gl52.BusTracer.Utils.Constantes;

public class MoveManager {

	public ReferencedEnvelope move(final int sens, final ReferencedEnvelope env) {
		double minX = env.getMinX();
		double maxX = env.getMaxX();
		double minY = env.getMinY();
		double maxY = env.getMaxY();
		int pas = Constantes.PAS_MOVE;

		switch (sens) {
			case Constantes.MOVE_TOP :
				env.init(minX, maxX, minY - pas, maxY - pas);
				break;
			case Constantes.MOVE_BOTTOM :
				env.init(minX, maxX, minY + pas, maxY + pas);
				break;
			case Constantes.MOVE_LEFT :
				env.init(minX + pas, maxX + pas, minY, maxY);
				break;
			case Constantes.MOVE_RIGHT :
				env.init(minX - pas, maxX - pas, minY, maxY);
				break;
			default :
				break;
		}
		return env;
	}
}
