/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static wtg_jack.perso.Perso.Direction.BOTTOM;
import static wtg_jack.perso.Perso.Direction.LEFT;
import static wtg_jack.perso.Perso.Direction.RIGHT;
import static wtg_jack.perso.Perso.Direction.TOP;
import static wtg_jack.perso.Perso.Etat.STAY;
import static wtg_jack.perso.Perso.Etat.WALK;

/**
 * Perso.java
 *
 */
public abstract class Perso extends Sprite {

	private static final float FRAME_DURATION = 0.15f;

	protected final Animation walkTop;
	protected final Animation walkBottom;
	protected final Animation walkLeft;
	protected final Animation walkRight;

	enum Etat {

		STAY, WALK
	}

	enum Direction {

		TOP, BOTTOM, LEFT, RIGHT
	}

	private float stateTime;
	private Animation animActu;
	private Etat etat;
	private Direction direction;

	public Perso(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight) {
		walkTop = new Animation(FRAME_DURATION, walksTop);
		walkBottom = new Animation(FRAME_DURATION, walksBottom);
		walkLeft = new Animation(FRAME_DURATION, walksLeft);
		walkRight = new Animation(FRAME_DURATION, walksRight);

		etat = STAY;
		direction = null;
		changeAnimation(BOTTOM);
	}

	public void changeAnimation(Direction _direction) {
		if (direction != _direction) {
			stateTime = 0;
			switch (_direction) {
				case TOP:
					animActu = walkTop;
					break;
				case BOTTOM:
					animActu = walkBottom;
					break;
				case LEFT:
					animActu = walkLeft;
					break;
				case RIGHT:
					animActu = walkRight;
					break;
			}
		}
	}

	@Override
	public void draw(Batch batch) {
		if (etat != STAY) {
			stateTime += Gdx.graphics.getDeltaTime();
		}
		batch.draw(animActu.getKeyFrame(stateTime, true), getX(), getY());
	}

}
