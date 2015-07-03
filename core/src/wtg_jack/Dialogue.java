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
import com.badlogic.gdx.graphics.g2d.BitmapFont.BitmapFontData;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.badlogic.gdx.graphics.g2d.TextureRegion;
import static wtg_jack.Main.TILE_SIZE;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;

/**
 * Dialogue.java
 *
 */
public class Dialogue extends Sprite {

	private static final TextureAtlas atlas = new TextureAtlas(Gdx.files.internal("dialogue/dialogue.txt"));
	private static final TextureRegion[] ELEMENTS = new TextureRegion[]{
		atlas.findRegion("dialog", 6),
		atlas.findRegion("dialog", 0),
		atlas.findRegion("dialog", 2),
		atlas.findRegion("dialog", 4),
		atlas.findRegion("dialog", 5),
		atlas.findRegion("dialog", 1),
		atlas.findRegion("dialog", 3)
	};
	private static final BitmapFont font;

	static {
		FreeTypeFontGenerator generator = new FreeTypeFontGenerator(Gdx.files.internal("font/Pokemon GB.ttf"));
		FreeTypeFontParameter parameter = new FreeTypeFontParameter();
		parameter.size = 8;
		parameter.color = Color.BLACK;
		font = generator.generateFont(parameter);
	}

	private boolean show;
	private String text;

	public Dialogue() {
		show = false;
	}

	public void show(String text) {
		show = true;
		this.text = text.substring(0, 36);
	}

	@Override
	public void draw(Batch batch) {
		if(!show) {
			return;
		}
		
		batch.draw(ELEMENTS[1], 0, TILE_SIZE * 2 - 8);
		batch.draw(ELEMENTS[2], TILE_SIZE * 10 - 8, TILE_SIZE * 2 - 8);
		batch.draw(ELEMENTS[3], TILE_SIZE * 10 - 8, 0);
		batch.draw(ELEMENTS[4], 0, 0);
		batch.draw(ELEMENTS[5], 8, TILE_SIZE * 2 - 8, TILE_SIZE * 10 - 8 * 2, 8);
		batch.draw(ELEMENTS[5], 8, 0, TILE_SIZE * 10 - 8 * 2, 8);
		batch.draw(ELEMENTS[6], 0, 8, 8, TILE_SIZE * 2 - 8 * 2);
		batch.draw(ELEMENTS[6], TILE_SIZE * 10 - 8, 8, 8, TILE_SIZE * 2 - 8 * 2);
		batch.draw(ELEMENTS[0], 8, 8, TILE_SIZE * 10 - 8 * 2, TILE_SIZE * 2 - 8 * 2);
		
		font.drawWrapped(batch, text, TILE_SIZE / 2, TILE_SIZE + 8, TILE_SIZE * 9);
	}

}
