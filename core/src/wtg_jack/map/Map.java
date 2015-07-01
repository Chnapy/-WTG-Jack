/*
 * 
 * 
 * 
 */
package wtg_jack.map;

import com.badlogic.gdx.maps.tiled.TiledMap;
import com.badlogic.gdx.maps.tiled.TmxMapLoader;
import com.badlogic.gdx.maps.tiled.renderers.OrthogonalTiledMapRenderer;

/**
 * Map.java
 * 
 */
public class Map extends OrthogonalTiledMapRenderer {
	
	private static final TiledMap MAP = new TmxMapLoader().load("tilemap/tilemap.tmx");
	
	public Map() {
		super(MAP);
	}

}
