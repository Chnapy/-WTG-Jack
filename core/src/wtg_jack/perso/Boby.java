/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import static wtg_jack.Main.TILE_SIZE;
import static wtg_jack.perso.Perso.Direction.BOTTOM;
import static wtg_jack.perso.Perso.Direction.LEFT;
import static wtg_jack.perso.Perso.Direction.RIGHT;
import static wtg_jack.perso.Perso.Direction.TOP;
import static wtg_jack.perso.Perso.Etat.STAY;

/**
 * Boby.java
 *
 */
public class Boby extends PNJ {

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
	private static final int INTERVAL = 3000;

	public Boby() {
		super(WALK_TOP, WALK_BOTTOM, WALK_LEFT, WALK_RIGHT);
		path.addAll(new Point[] {
			new Point(0, 0),
			new Point(-1, 0),
			new Point(1, 0),
			new Point(0, -1),
			new Point(0, 1),
			new Point(-1, 1),
			new Point(2, 0)
		});
	}

	@Override
	public void autoMove() {
		System.out.println(Gdx.graphics.getDeltaTime());
		if (etat != STAY || path.size == 0) {
			return;
		}
		interval += Gdx.graphics.getDeltaTime() * 1000;
		if(interval < INTERVAL) {
			return;
		} else {
			interval = 0;
		}
		Point posActu = new Point((int) (getX() / TILE_SIZE) - getFirstPosition().x, (int) (getY() / TILE_SIZE) - getFirstPosition().y);
		Array<Direction> directionsDispo = new Array<>();
		for (Point pos : path) {
			if (pos.x == posActu.x - 1 && pos.y == posActu.y) {
				directionsDispo.add(LEFT);
			} else if (pos.x == posActu.x + 1 && pos.y == posActu.y) {
				directionsDispo.add(RIGHT);
			} else if (pos.x == posActu.x && pos.y == posActu.y - 1) {
				directionsDispo.add(BOTTOM);
			} else if (pos.x == posActu.x && pos.y == posActu.y + 1) {
				directionsDispo.add(TOP);
			}
			if (directionsDispo.size == 4) {
				break;
			}
		}
		if (directionsDispo.size == 0) {
			return;
		}
		if (directionsDispo.size == 1) {
			move(directionsDispo.first());
		} else {
			move(directionsDispo.random());
		}
	}

}
