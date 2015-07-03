/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import wtg_jack.perso.pnj.Dresseur;
import wtg_jack.perso.pnj.PathMove;

/**
 * Boby.java
 *
 */
public class Boby extends Dresseur {

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
	private static final int INTERVAL = 300;

	public Boby() {
		super(WALK_TOP, WALK_BOTTOM, WALK_LEFT, WALK_RIGHT,
				"Boby", new PathMove(
						new Point[]{
							new Point(0, 0),
							new Point(-1, 0),
							new Point(-2, 0),
							new Point(-2, -1),
							new Point(-1, -1),
							new Point(0, -1),
							new Point(0, 0)
						}, INTERVAL),
				"C'est moi Boby le génie !", "Tu m'as vaincu comment t'es fort !"
		);
	}

}
