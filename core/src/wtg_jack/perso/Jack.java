/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static wtg_jack.perso.Perso.Direction.BOTTOM;
import static wtg_jack.perso.Perso.Direction.LEFT;
import static wtg_jack.perso.Perso.Direction.RIGHT;
import static wtg_jack.perso.Perso.Direction.TOP;
import static wtg_jack.perso.Perso.Etat.STAY;

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

	private int key;

	public Jack() {
		super(WALK_TOP, WALK_BOTTOM, WALK_LEFT, WALK_RIGHT);
		key = -1;
	}

	public void action() {
		if (etat != STAY) {
			return;
		}
		switch (key) {
			case Keys.A:
				a();
				break;
			case Keys.B:
				b();
				break;
			case Keys.LEFT:
				left();
				break;
			case Keys.RIGHT:
				right();
				break;
			case Keys.UP:
				up();
				break;
			case Keys.DOWN:
				down();
				break;
			case Keys.ENTER:
				start();
				break;
			case Keys.DEL:
				select();
				break;
		}
	}

	public void a() {

	}

	public void b() {

	}

	public void left() {
		move(LEFT);
	}

	public void right() {
		move(RIGHT);
	}

	public void up() {
		move(TOP);
	}

	public void down() {
		move(BOTTOM);
	}

	public void start() {

	}

	public void select() {

	}

	@Override
	public void update() {
		if (etat != STAY) {
			int keypressed = -1;
			switch (direction) {
				case LEFT:
					keypressed = Keys.LEFT;
					setX(getX() - 1);
					break;
				case RIGHT:
					keypressed = Keys.RIGHT;
					setX(getX() + 1);
					break;
				case TOP:
					keypressed = Keys.UP;
					setY(getY() + 1);
					break;
				case BOTTOM:
					keypressed = Keys.DOWN;
					setY(getY() - 1);
					break;
			}
//			System.out.println(getX() + " " + getY());
			if (getX() % 16 == 0 && getY() % 16 == 0) {
				etat = STAY;
				if (isMoveKeysPressed()) {
					action();
				}
			}
		}
	}

	private boolean isMoveKeysPressed() {
		if (Gdx.input.isKeyPressed(Keys.LEFT)) {
			key = Keys.LEFT;
			return true;
		}
		if (Gdx.input.isKeyPressed(Keys.RIGHT)) {
			key = Keys.RIGHT;
			return true;
		}
		if (Gdx.input.isKeyPressed(Keys.UP)) {
			key = Keys.UP;
			return true;
		}
		if (Gdx.input.isKeyPressed(Keys.DOWN)) {
			key = Keys.DOWN;
			return true;
		}
		return false;
	}

	public void setKey(int key) {
		this.key = key;
	}

}
