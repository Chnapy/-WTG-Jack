/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import static wtg_jack.Exploration.toTile;
import wtg_jack.perso.Enum.Direction;
import static wtg_jack.perso.Enum.Direction.BOTTOM;
import static wtg_jack.perso.Enum.Direction.LEFT;
import static wtg_jack.perso.Enum.Direction.RIGHT;
import static wtg_jack.perso.Enum.Direction.TOP;

/**
 * RandomMove.java
 *
 */
public class RandomMove extends IAmove {

	public RandomMove(Point[] path, int interval) {
		super(path, interval);
	}

	@Override
	protected Direction move(PNJ pnj) {
		Point posActu = new Point(toTile(pnj.getX()) - pnj.getFirstPosition().x, toTile(pnj.getY()) - pnj.getFirstPosition().y);
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
			return null;
		}
		if (directionsDispo.size == 1) {
			return directionsDispo.first();
		} else {
			return directionsDispo.random();
		}
	}

}
