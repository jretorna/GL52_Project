package fr.utbm.gl52.BusTracer.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import org.geotools.geometry.jts.JTSFactoryFinder;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.Point;

public class DrawTrace extends JPanel {
	/*---------------------------*/
	private static final long serialVersionUID = 1L;
	private Point pt;
	/*---------------------------*/

	public DrawTrace() {

	}

	public DrawTrace(final Point _pt) {
		pt = _pt;
	}

	@Override
	public void paintComponent(final Graphics g) {
		final Graphics2D g2 = (Graphics2D) g;
		g2.setColor(Color.BLUE);
		g2.drawOval((int) pt.getX(), (int) pt.getY(), 1, 3);
		System.out.println("je dessine " + pt.getX() + "," + pt.getY());
		repaint();
	}

	public void updatePoint(final Point _pt) {
		try {
			Thread.sleep(20);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		pt = _pt;
		repaint();
	}

	public void updatePoint(final Coordinate _coord) {
		GeometryFactory geoFactory = JTSFactoryFinder.getGeometryFactory();

		pt = geoFactory.createPoint(_coord);
		try {
			Thread.sleep(2);
		} catch (InterruptedException e) {
			e.printStackTrace();
		}
		repaint();
	}
}
