/*
 * 
 * 
 * 
 */
package wtg_jack.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TiledMapTileLayer;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;
import java.awt.Point;
import java.util.HashMap;
import wtg_jack.Actionable;

/**
 * Map.java
 *
 */
public class Map extends OrthogonalTiledMapRenderer {

	private static final TiledMap MAP = new TmxMapLoader().load("tilemap/tilemap.tmx");
	private final TiledMapTileLayer layerCollision;
	private final HashMap<Point, Actionable> tileBusy;

	public Map() {
		super(MAP);
		layerCollision = (TiledMapTileLayer) MAP.getLayers().get("Collision");
		tileBusy = new HashMap<>();
	}

	public boolean isFree(int x, int y) {
		return layerCollision.getCell(x * 2, y * 2) == null && isBusy(x, y) == null;
	}

	public void setBusy(int x, int y, Actionable perso) {
		tileBusy.put(new Point(x, y), perso);
	}

	public void removeBusy(int x, int y) {
		removeBusy(new Point(x, y));
	}

	public void removeBusy(Point p) {
		tileBusy.remove(p);
	}

	public Actionable isBusy(int x, int y) {
		return tileBusy.get(new Point(x, y));
	}

}
