package fr.utbm.gl52.BusTracer.DataCoordinate;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;
import java.net.MalformedURLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.geotools.data.DataStore;
import org.geotools.data.DataStoreFinder;
import org.geotools.data.FeatureSource;
import org.geotools.feature.FeatureCollection;
import org.geotools.feature.FeatureIterator;
import org.opengis.feature.simple.SimpleFeature;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.Point;

public class DataCoordinateManager {
	/*--------------------*/

	/*--------------------*/

	public DataCoordinateManager() {
		// do nothing
	}

	@SuppressWarnings("rawtypes")
	public List<Coordinate> getCoordinateFromFile(final String uri) {
		System.out.println("Import GPS datas : " + uri); //$NON-NLS-1$
		Map<String, Serializable> map = new HashMap<>();
		try {
			map.put("url", new File(uri).toURI().toURL());
			final DataStore dataStore = DataStoreFinder.getDataStore(map);
			String typeName = dataStore.getTypeNames()[0];

			FeatureSource source = dataStore.getFeatureSource(typeName);
			FeatureCollection collection = source.getFeatures();
			FeatureIterator<SimpleFeature> results = collection.features();
			List<Coordinate> coords = new ArrayList<Coordinate>();
			while (results.hasNext()) {
				SimpleFeature feature = results.next();
				feature.getAttributes();
				List<Object> code = feature.getAttributes();
				Point pt = (Point) code.get(0);
				coords.add(new Coordinate(pt.getX(), pt.getY()));
				System.out.println("x : " + pt.getX() + " | y : " + pt.getY());
			}
			dataStore.dispose();
			results.close();
			return coords;
		} catch (MalformedURLException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return null;
	}
}
