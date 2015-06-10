package fr.utbm.gl52.BusTracer.Map;

import java.awt.Color;
import java.io.File;
import java.io.IOException;

import org.geotools.data.DataUtilities;
import org.geotools.data.FileDataStore;
import org.geotools.data.FileDataStoreFinder;
import org.geotools.data.simple.SimpleFeatureCollection;
import org.geotools.data.simple.SimpleFeatureSource;
import org.geotools.feature.DefaultFeatureCollection;
import org.geotools.feature.FeatureCollections;
import org.geotools.feature.SchemaException;
import org.geotools.feature.simple.SimpleFeatureBuilder;
import org.geotools.geometry.jts.JTSFactoryFinder;
import org.geotools.map.FeatureLayer;
import org.geotools.map.Layer;
import org.geotools.styling.SLD;
import org.geotools.styling.Style;
import org.opengis.feature.simple.SimpleFeature;
import org.opengis.feature.simple.SimpleFeatureType;

import com.vividsolutions.jts.geom.Coordinate;
import com.vividsolutions.jts.geom.GeometryFactory;
import com.vividsolutions.jts.geom.LineString;

@SuppressWarnings("deprecation")
public class MapManager {

	public Layer setAddLayer(final File file) throws IOException {
		FileDataStore store = null;
		SimpleFeatureSource featureSource;
		store = FileDataStoreFinder.getDataStore(file);
		featureSource = store.getFeatureSource();
		Style style = SLD.createSimpleStyle(featureSource.getSchema());
		return new FeatureLayer(featureSource, style);
	}

	public Layer setPointDplctFromList(final Coordinate[] listOfPoints)
			throws SchemaException {
		SimpleFeatureType lineType = DataUtilities.createType("LINE",
				"geom:LineString,name:String");
		SimpleFeatureBuilder featureBuilderLines = new SimpleFeatureBuilder(
				lineType);
		SimpleFeatureCollection collectionLines = FeatureCollections.newCollection();
		GeometryFactory geoFactory = JTSFactoryFinder.getGeometryFactory();
		LineString line = geoFactory.createLineString(listOfPoints);
		featureBuilderLines.add(line);
		SimpleFeature featureLine = featureBuilderLines.buildFeature(null);
		((DefaultFeatureCollection) collectionLines).add(featureLine);
		Style lineStyle = SLD.createLineStyle(Color.RED, 2.0f);
		return new FeatureLayer(collectionLines, lineStyle);
	}
}
