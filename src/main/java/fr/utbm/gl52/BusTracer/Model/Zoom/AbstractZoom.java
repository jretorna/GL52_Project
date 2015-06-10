package fr.utbm.gl52.BusTracer.Model.Zoom;

public abstract class AbstractZoom implements IZoom {
	final int maximumZoom = 10;
	final int minimumZoom = -10;
	int iteration = 0;
}
