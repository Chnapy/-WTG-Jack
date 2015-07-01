/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import static wtg_jack.Main.TILE_SIZE;

/**
 * PNJ.java
 *
 */
public abstract class PNJ extends Perso {

	private Point firstPosition;
	protected final Array<Point> path;
	protected int interval;

	public PNJ(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight) {
		super(walksTop, walksBottom, walksLeft, walksRight);
		path = new Array<>();
		interval = 0;
	}

	@Override
	public void update() {
		autoMove();
		super.update();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		firstPosition = new Point((int) x / TILE_SIZE, (int) y / TILE_SIZE);
	}

	public Point getFirstPosition() {
		return firstPosition;
	}

	public Array<Point> getPath() {
		return path;
	}

	public abstract void autoMove();
}
