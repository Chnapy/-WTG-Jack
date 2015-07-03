/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import java.awt.Point;
import static wtg_jack.Exploration.toTile;
import static wtg_jack.Main.TILE_SIZE;
import wtg_jack.perso.Enum;
import static wtg_jack.perso.Enum.Direction.BOTTOM;
import static wtg_jack.perso.Enum.Direction.LEFT;
import static wtg_jack.perso.Enum.Direction.RIGHT;
import static wtg_jack.perso.Enum.Direction.TOP;

/**
 * PathMove.java
 *
 */
public class PathMove extends IAmove {

	private int index;
	private boolean sens;

	public PathMove(Point[] path, int interval) {
		super(path, interval);
		index = -1;
		sens = true;
	}

	@Override
	protected Enum.Direction move(PNJ pnj) {
		Point pos, posActu = new Point(toTile(pnj.getX()) - pnj.getFirstPosition().x, toTile(pnj.getY()) - pnj.getFirstPosition().y);
		try {
			pos = path.get(sens ? index + 1 : index - 1);
		} catch (IndexOutOfBoundsException e) {
			sens = !sens;
			pos = path.get(sens ? index + 1 : index - 1);
		}
		index = sens ? index + 1 : index - 1;
		if (pos.x == posActu.x - 1 && pos.y == posActu.y) {
			return LEFT;
		} else if (pos.x == posActu.x + 1 && pos.y == posActu.y) {
			return RIGHT;
		} else if (pos.x == posActu.x && pos.y == posActu.y - 1) {
			return BOTTOM;
		} else if (pos.x == posActu.x && pos.y == posActu.y + 1) {
			return TOP;
		}
		return null;
	}

}
