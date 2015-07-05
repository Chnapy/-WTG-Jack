/*
 * 
 * 
 * 
 */
package wtg_jack.perso;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import wtg_jack.Actionable;
import static wtg_jack.Exploration.MAP;
import static wtg_jack.Exploration.toTile;
import static wtg_jack.Jeu.TILE_SIZE;
import wtg_jack.perso.Enum.Direction;
import static wtg_jack.perso.Enum.Direction.BOTTOM;
import static wtg_jack.perso.Enum.Direction.LEFT;
import static wtg_jack.perso.Enum.Direction.RIGHT;
import static wtg_jack.perso.Enum.Direction.TOP;
import wtg_jack.perso.Enum.Etat;
import static wtg_jack.perso.Enum.Etat.STAY;
import static wtg_jack.perso.Enum.Etat.WALK;

/**
 * Perso.java
 *
 */
public abstract class Perso extends Sprite implements Actionable {

	private static final float FRAME_DURATION = 0.12f;

	protected final Animation walkTop;
	protected final Animation walkBottom;
	protected final Animation walkLeft;
	protected final Animation walkRight;

	//Deplacement et affichage
	private float stateTime;
	private Animation animActu;
	protected Etat etat;
	protected Direction direction;
	protected Point oldPosition;

	//Dialogue
	private final String nom;
	
	protected boolean play;

	public Perso(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight,
			String _nom) {
		walkTop = new Animation(FRAME_DURATION, walksTop);
		walkBottom = new Animation(FRAME_DURATION, walksBottom);
		walkLeft = new Animation(FRAME_DURATION, walksLeft);
		walkRight = new Animation(FRAME_DURATION, walksRight);
		setSize(TILE_SIZE, TILE_SIZE);

		nom = _nom;
		stateTime = 0;
		animActu = null;
		etat = STAY;
		direction = null;
		changeDirection(BOTTOM);
		play = true;
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
			if (getX() % TILE_SIZE == 0 && getY() % TILE_SIZE == 0) {
				onStop();
			}
		}
	}

	protected void onStop() {
		MAP.removeBusy(oldPosition);
		oldPosition.setLocation(toTile(getX()), toTile(getY()));
		etat = STAY;
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

	public boolean move(Direction _direction) {
		changeDirection(_direction);
		if (etat == STAY) {
			int x = toTile(getX());
			int y = toTile(getY());
			switch (direction) {
				case LEFT:
					x--;
					break;
				case RIGHT:
					x++;
					break;
				case TOP:
					y++;
					break;
				case BOTTOM:
					y--;
					break;
			}
			if (MAP.isFree(x, y)) {
				MAP.setBusy(x, y, this);
				etat = WALK;
				return true;
			}
			return false;
		}
		return true;
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		oldPosition = new Point(toTile(x), toTile(y));
		MAP.setBusy(toTile(x), toTile(y), this);
	}

	public void setEtat(Etat etat) {
		this.etat = etat;
	}

	public void setDirection(Direction direction) {
		this.direction = direction;
	}

	@Override
	public void draw(Batch batch) {
		stateTime += Gdx.graphics.getDeltaTime();
		batch.draw(animActu.getKeyFrame(etat == STAY ? 0 : stateTime, true), getX(), getY());
	}

	public Etat getEtat() {
		return etat;
	}

	public Direction getDirection() {
		return direction;
	}
	
	public void pause() {
		play = false;
	}
	
	public void play() {
		play = true;
	}

}
