/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input.Keys;
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
	
	private static final float FRAME_DURATION = 0.12f;
	
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
	protected Etat etat;
	protected Direction direction;
	
	public Perso(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight) {
		walkTop = new Animation(FRAME_DURATION, walksTop);
		walkBottom = new Animation(FRAME_DURATION, walksBottom);
		walkLeft = new Animation(FRAME_DURATION, walksLeft);
		walkRight = new Animation(FRAME_DURATION, walksRight);
		setSize(16, 16);
		
		stateTime = 0;
		animActu = null;
		etat = STAY;
		direction = null;
		changeDirection(BOTTOM);
	}
	
	public void update() {
		if (etat != STAY) {
			switch (direction) {
				case LEFT:
					setX(getX() - 1);
					break;
				case RIGHT:
					setX(getX() + 1);
					break;
				case TOP:
					setY(getY() + 1);
					break;
				case BOTTOM:
					setY(getY() - 1);
					break;
			}
			if (getX() % 16 == 0 && getY() % 16 == 0) {
				etat = STAY;
			}
		}
	}
	
	public void changeDirection(Direction _direction) {
		direction = _direction;
		switch (_direction) {
			case TOP:
				if (!walkTop.equals(animActu)) {
					animActu = walkTop;
				}
				break;
			case BOTTOM:
				if (!walkBottom.equals(animActu)) {
					animActu = walkBottom;
				}
				break;
			case LEFT:
				if (!walkLeft.equals(animActu)) {
					animActu = walkLeft;
				}
				break;
			case RIGHT:
				if (!walkRight.equals(animActu)) {
					animActu = walkRight;
				}
				break;
		}
	}
	
	public void move(Direction _direction) {
		changeDirection(_direction);
		etat = WALK;
	}
	
	@Override
	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animActu.getKeyFrame(etat == STAY ? 0 : stateTime, true), getX(), getY());
	}
	
}
