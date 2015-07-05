/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import wtg_jack.Actionable;
import wtg_jack.Dialogue;
import wtg_jack.Exploration;
import static wtg_jack.Exploration.MAP;
import static wtg_jack.Exploration.setDialogue;
import static wtg_jack.Exploration.setFondu;
import static wtg_jack.Exploration.toTile;
import wtg_jack.perso.Jack;
import wtg_jack.transition.Escargot;

/**
 * Dresseur.java
 *
 */
public abstract class Dresseur extends PNJ {

	private static final Texture popup = new Texture(Gdx.files.internal("perso/popup.png"));
	private static final int popup_time = 1000;

	private String fin;
	private final int portee;
	private boolean action;
	private float actionTime;

	public Dresseur(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight,
			String _nom, IAmove iamove,
			String intro, String fin,
			int portee) {
		super(walksTop, walksBottom, walksLeft, walksRight, _nom, iamove, intro);
		this.fin = fin;
		this.portee = portee;
		action = false;
		actionTime = 0;
	}

	@Override
	public void update() {
		super.update();
		if (action && actionTime * 1000 >= popup_time) {
			boolean move = move(direction);
//			System.out.println(move);
			if (!move) {
				action = false;
				setDialogue(new Dialogue(getIntro()) {
					@Override
					public void end() {
						setFondu(new Escargot() {
							@Override
							public void end() {
							}
						});
					}
				});

			}
		}
	}

	@Override
	protected void onStop() {
		super.onStop();
		int x = 0;
		int y = 0;
		switch (direction) {
			case TOP:
				y++;
				break;
			case BOTTOM:
				y--;
				break;
			case LEFT:
				x--;
				break;
			case RIGHT:
				x++;
				break;
		}
		for (int i = 1; i < portee; i++) {
//			System.out.println(toTile((getX()) + x * i) + " " + (toTile(getY()) + y * i));
			if (!MAP.isCollision(toTile(getX()) + x * i, toTile(getY()) + y * i)) {
//				System.out.println("OBSTACLE");
				break;
			}
			Actionable act = MAP.isBusy(toTile(getX()) + x * i, toTile(getY()) + y * i);
			if (act == null) {
				continue;
			}
			if (!(act instanceof Jack)) {
//				System.out.println("PAS JACK");
				break;
			}
			action = true;
			Exploration.pauseAll();

			break;
		}
	}

	@Override
	public void draw(Batch batch) {
		super.draw(batch);
		if (action) {
			actionTime += Gdx.graphics.getDeltaTime();
			if (actionTime * 1000 < popup_time) {
				batch.draw(popup, getX(), getY() + getHeight());
			}
		}
	}

}
