package fr.utbm.gl52.BusTracer.Utils;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

/**
 * @version 01.00.00
 *
 * @author Jeremy - V01.00.00
 * @date 29 avr. 2015<br>
 *
 */
public class ResizableImage extends JPanel {

	private static final long serialVersionUID = 1L;
	private BufferedImage image;

	public ResizableImage(final File file) {
		try {
			this.image = ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("capture exception");
			e.printStackTrace();
		}

	}

	@Override
	public void paintComponent(final Graphics g) {
		Graphics2D g2D = (Graphics2D) g;
		Dimension dim = getPreferredSize();
		g2D.drawImage(image, 0, 0, dim.width, dim.height, this);

	}
	public void setZoom(final double zoom) {
		int w = (int) (zoom * this.getSize().getWidth());
		int h = (int) (zoom * this.getSize().getHeight());

		setPreferredSize(new Dimension(w, h));

		revalidate();
		repaint();
	}

}
