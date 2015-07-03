/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import wtg_jack.perso.Enum.Direction;
import static wtg_jack.perso.Enum.Etat.STAY;

/**
 * IAmove.java
 * 
 */
public abstract class IAmove {
	
	protected final Array<Point> path;
	protected final int interval;
	protected int timeActu;
	
	public IAmove(Point[] path, int interval) {
		this.path = new Array<>(path);
		this.interval = interval;
	}
	
	public Direction IAmove(PNJ pnj) {
		if (pnj.getEtat() != STAY || path.size == 0) {
			return null;
		}
		timeActu += Gdx.graphics.getDeltaTime() * 1000;
		if (timeActu < interval) {
			return null;
		} else {
			timeActu = 0;
		}
		return move(pnj);
	}
	
	protected abstract Direction move(PNJ pnj);

}
