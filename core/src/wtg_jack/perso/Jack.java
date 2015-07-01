/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Jack.java
 *
 */
public class Jack extends Perso {

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("perso/jack/jack.txt"));
	private static final TextureRegion[] WALK_TOP = new TextureRegion[]{
		atlas.findRegion("top", 0),
		atlas.findRegion("top", 1),
		atlas.findRegion("top", 0),
		atlas.findRegion("top", 2)
	};
	private static final TextureRegion[] WALK_BOTTOM = new TextureRegion[]{
		atlas.findRegion("bottom", 0),
		atlas.findRegion("bottom", 1),
		atlas.findRegion("bottom", 0),
		atlas.findRegion("bottom", 2)
	};
	private static final TextureRegion[] WALK_LEFT = new TextureRegion[]{
		atlas.findRegion("left", 0),
		atlas.findRegion("left", 1)
	};
	private static final TextureRegion[] WALK_RIGHT = new TextureRegion[]{
		atlas.findRegion("right", 0),
		atlas.findRegion("right", 1)
	};

	public Jack() {
		super(WALK_TOP, WALK_BOTTOM, WALK_LEFT, WALK_RIGHT);
	}
	
	public void update() {
	}

}
