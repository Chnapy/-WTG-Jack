/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import com.badlogic.gdx.graphics.g2d.TextureRegion;
import java.awt.Point;
import static wtg_jack.Exploration.toTile;
import wtg_jack.perso.Perso;
import wtg_jack.perso.Enum.Direction;
import wtg_jack.perso.Jack;

/**
 * PNJ.java
 *
 */
public abstract class PNJ extends Perso {

	private Point firstPosition;
	protected final IAmove iamove;
	
	private String intro;

	public PNJ(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight,
			String _nom, IAmove iamove, String intro) {
		super(walksTop, walksBottom, walksLeft, walksRight, _nom);
		this.iamove = iamove;
		this.intro = intro;
	}

	@Override
	public void update() {
		autoMove();
		super.update();
	}

	@Override
	public void setPosition(float x, float y) {
		super.setPosition(x, y);
		firstPosition = new Point(toTile(x), toTile(y));
	}

	public Point getFirstPosition() {
		return firstPosition;
	}

	public void autoMove() {
		Direction d = iamove.IAmove(this);
		if(d != null) {
			move(d);
		}
	}

	@Override
	public void interaction(Jack jack) {
		System.out.println("OK");
	}
}
