package fr.utbm.gl52.BusTracer.View;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.io.FilenameFilter;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.plaf.basic.BasicArrowButton;

import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.map.MapContent;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.geotools.swing.JMapFrame;
import org.geotools.swing.data.JFileDataStoreChooser;

/**
 *
 */
public class ShapeViewControl {

	private int fileCount;
	private File[] files;
	private File file;
	private FileDataStore store;
	private SimpleFeatureSource featureSource;
	private MapContent map;
	private JMapFrame jmf;
	private JButton lJB, rJB;

	/**
	 * 
	 */
	public ShapeViewControl() {
	}

	/**
	 * @throws IOException
	 * 
	 */
	public void fileChooser() throws IOException {
		this.file = JFileDataStoreChooser.showOpenFile("shp", null); //$NON-NLS-1$
		if (this.file == null) {
			System.exit(0);
		}
		this.files = this.file.getParentFile().listFiles(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return (name.endsWith(".shp")); //$NON-NLS-1$
			}
		});
		setAddLayer();
		mapTitle();
		initMap();
		displayMap();
	}

	private void setAddLayer() throws IOException {
		this.store = null;
		this.store = FileDataStoreFinder.getDataStore(this.file);
		this.featureSource = this.store.getFeatureSource();
		Style style = SLD.createSimpleStyle(this.featureSource.getSchema());
		Layer layer = new FeatureLayer(this.featureSource, style);
		this.map = new MapContent();
		this.map.addLayer(layer);

	}

	private void mapTitle() {
		this.map.setTitle(this.file.getName());

	}

	private void initMap() {
		this.jmf = new JMapFrame();
		this.jmf.setSize(800, 600);
		// jmf.enableLayerTable(true);
		this.jmf.enableStatusBar(true);
		this.jmf.enableToolBar(true);
		enableButtons();
	}

	private void displayMap() {

		this.jmf.setMapContent(this.map);
		this.jmf.setTitle("ShapeView - " + this.file.getName()); //$NON-NLS-1$
		this.jmf.setVisible(true);
		this.jmf.initComponents();

	}

	private void enableButtons() {

		this.lJB = new BasicArrowButton(SwingConstants.WEST);
		this.rJB = new BasicArrowButton(SwingConstants.EAST);
		this.lJB.addActionListener(new ActionListener() {

			@Override
			public void actionPerformed(final ActionEvent e) {
				try {
					navigateFolder();
				} catch (IOException ex) {
					Logger.getLogger(ShapeViewControl.class.getName()).log(
							Level.SEVERE, null, ex);
				}
			}

		});

		this.jmf.getToolBar().add(this.lJB);
		this.jmf.getToolBar().add(this.rJB);
	}

	/**
	 * @param string
	 * @throws IOException
	 * 
	 */
	public void setFile(final String string) throws IOException {
		this.file = new File(string);
		this.files = this.file.getParentFile().listFiles(new FilenameFilter() {

			@Override
			public boolean accept(final File dir, final String name) {
				return (name.endsWith(".shp")); //$NON-NLS-1$
			}
		});
		setAddLayer();
		mapTitle();
		displayMap();
	}

	private void nextFile(final File nextFile) throws IOException {
		this.file = nextFile;
		this.map.dispose();
		setAddLayer();
		mapTitle();
		displayMap();
	}

	private void navigateFolder() throws IOException {
		if (this.fileCount == 0) {
			this.fileCount = 1;
			if (this.fileCount < this.files.length) {
				nextFile(this.files[this.fileCount]);
				this.fileCount++;

			}
		} else {
			if (this.fileCount < this.files.length) {
				nextFile(this.files[this.fileCount]);
				this.fileCount++;
			}
		}

	}
}