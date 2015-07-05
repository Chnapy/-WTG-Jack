/*
 * 
 * 
 * 
 */
package wtg_jack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static wtg_jack.Jeu.TILE_SIZE;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Dialogue.java
 *
 */
public abstract class Dialogue extends Sprite {

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dialogue/dialogue.txt"));
	private static final TextureRegion[] ELEMENTS = new TextureRegion[]{
		atlas.findRegion("dialog", 0),
		atlas.findRegion("dialog", 1),
		atlas.findRegion("dialog", 2),
		atlas.findRegion("dialog", 3),
		atlas.findRegion("dialog", 4),
		atlas.findRegion("dialog", 5),
		atlas.findRegion("dialog", 6),
		atlas.findRegion("dialog", 7),
		atlas.findRegion("dialog", 8),
		atlas.findRegion("dialog", 9)
	};
	private static final float FRAME_DURATION = 0.5f;
	private static final float CHAR_DURATION = 0.05f;
	private static final BitmapFont font;

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Pokemon GB.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 8;
		parameter.color = Color.BLACK;
		font = generator.generateFont(parameter);
	}

	private boolean show;
	private String[] text;
	private int index;
	private float stateTime;

	public Dialogue() {
		show = false;
	}

	public Dialogue(String texte) {
		Exploration.pauseAll();
		show = true;
		text = texte.split("\n");
		index = Math.min(text.length - 1, 1);
		stateTime = 0;
	}

	public void next() {
		if (index < text.length - 1) {
			index += 2;
			stateTime = 0;
		} else {
			show = false;
			end();
		}
	}
	
	public abstract void end();

	public boolean isShow() {
		return show;
	}

	@Override
	public void draw(Batch batch) {
		if (!show) {
			return;
		}

		batch.draw(ELEMENTS[1], 0, TILE_SIZE * 3 - 8);
		batch.draw(ELEMENTS[2], TILE_SIZE * 10 - 8, TILE_SIZE * 3 - 8);
		batch.draw(ELEMENTS[3], TILE_SIZE * 10 - 8, 0);
		batch.draw(ELEMENTS[4], 0, 0);
		batch.draw(ELEMENTS[5], 8, TILE_SIZE * 3 - 8, TILE_SIZE * 10 - 8 * 2, 8);
		batch.draw(ELEMENTS[5], 8, 0, TILE_SIZE * 10 - 8 * 2, 8);
		batch.draw(ELEMENTS[6], 0, 8, 8, TILE_SIZE * 3 - 8 * 2);
		batch.draw(ELEMENTS[6], TILE_SIZE * 10 - 8, 8, 8, TILE_SIZE * 3 - 8 * 2);
		batch.draw(ELEMENTS[0], 8, 8, TILE_SIZE * 10 - 8 * 2, TILE_SIZE * 3 - 8 * 2);
		stateTime += Gdx.graphics.getDeltaTime();
		
		int length1 = Math.min(text[index - 1].length(), (int) (stateTime / CHAR_DURATION));
		font.draw(batch, text[index - 1].substring(0, length1), TILE_SIZE / 2, TILE_SIZE * 2);
		if (index < text.length) {
			int length2 = Math.min(text[index].length(), (int) (stateTime / CHAR_DURATION) - length1);
			font.draw(batch, text[index].substring(0, length2), TILE_SIZE / 2, TILE_SIZE);
		}

		if (index < text.length - 1 && (int) (stateTime / FRAME_DURATION) % 2 == 0) {
			batch.draw(ELEMENTS[7], TILE_SIZE * 9, TILE_SIZE / 2);
		}
	}

}
