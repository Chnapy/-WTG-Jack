/*
 * 
 * 
 * 
 */
package wtg_jack.transition;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import static wtg_jack.Jeu.HEIGHT;
import static wtg_jack.Jeu.TILE_SIZE;
import static wtg_jack.Jeu.WIDTH;

/**
 * Fondu.java
 *
 */
public abstract class Fondu {

	private final static int TIME = 3000;
	protected final static int width = WIDTH / TILE_SIZE;
	protected final static int height = HEIGHT / TILE_SIZE;
	protected final static int total = width * height;
	private final static int TIME_FRAME = TIME / total;

	private boolean show;
	protected int index;
	protected final Array<Point> pos;
	private float stateTime;

	public Fondu() {
		this(true);
	}
	
	public Fondu(boolean _show) {
		show = _show;
		index = 0;
		pos = new Array<>();
		stateTime = 0;
	}

	public void next() {
		if(pos.size == total) {
			return;
		}
		stateTime += Gdx.graphics.getDeltaTime();
		if ((int)(stateTime * 1000) / TIME_FRAME >= index) {
			nextPos();
			index ++;
			if (pos.size == total) {
				end();
			}
		}
	}

	public abstract void nextPos();

	public abstract void end();

	public Array<Point> getPos() {
		return pos;
	}

	public boolean isShow() {
		return show;
	}

}
