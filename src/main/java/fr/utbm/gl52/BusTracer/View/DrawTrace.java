package fr.utbm.gl52.BusTracer.View;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

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
		pt = _pt;
		repaint();
	}
}
