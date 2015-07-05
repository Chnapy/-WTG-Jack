/*
 * 
 * 
 * 
 */
package wtg_jack.transition;

import java.awt.Point;
import static wtg_jack.Jeu.HEIGHT;
import static wtg_jack.Jeu.TILE_SIZE;
import static wtg_jack.Jeu.WIDTH;
import wtg_jack.perso.Enum.Direction;
import static wtg_jack.perso.Enum.Direction.BOTTOM;
import static wtg_jack.perso.Enum.Direction.LEFT;
import static wtg_jack.perso.Enum.Direction.RIGHT;
import static wtg_jack.perso.Enum.Direction.TOP;

/**
 * Escargot.java
 *
 */
public abstract class Escargot extends Fondu {

	private Direction dir;
	private Point vel;

	public Escargot() {
		vel = new Point();
		setDirection(RIGHT);
	}

	@Override
	public void nextPos() {
		Point last;
		if (pos.size > 0) {
			last = pos.peek();
			last = new Point(last.x + vel.x, last.y + vel.y);
			if (last.x >= width || last.x < 0 || last.y >= height || last.y < 0 || pos.contains(last, false)) {
				nextDirection();
				nextPos();
			} else {
				pos.add(last);
			}
		} else {
			last = new Point(0, 0);
			pos.add(last);
		}
	}

	private void setDirection(Direction direction) {
		dir = direction;
		switch (dir) {
			case RIGHT:
				vel.setLocation(1, 0);
				break;
			case BOTTOM:
				vel.setLocation(0, -1);
				break;
			case LEFT:
				vel.setLocation(-1, 0);
				break;
			case TOP:
				vel.setLocation(0, 1);
				break;
		}
	}

	private void nextDirection() {
		switch (dir) {
			case RIGHT:
				setDirection(BOTTOM);
				break;
			case BOTTOM:
				setDirection(LEFT);
				break;
			case LEFT:
				setDirection(TOP);
				break;
			case TOP:
				setDirection(RIGHT);
				break;
		}
	}

}
