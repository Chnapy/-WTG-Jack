/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import static wtg_jack.perso.Enum.Direction.BOTTOM;
import static wtg_jack.perso.Enum.Direction.LEFT;
import static wtg_jack.perso.Enum.Direction.RIGHT;
import static wtg_jack.perso.Enum.Direction.TOP;

/**
 * Enum.java
 *
 */
public class Enum {

	public enum Etat {

		STAY, WALK
	}

	public enum Direction {

		TOP, BOTTOM, LEFT, RIGHT
	}
	
	public static Direction getReverse(Direction d) {
		switch(d) {
			case TOP:
				return BOTTOM;
			case BOTTOM:
				return TOP;
			case LEFT:
				return RIGHT;
			case RIGHT:
				return LEFT;
		}
		return null;
	}

}
