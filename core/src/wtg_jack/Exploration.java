/*
 * 
 * 
 * 
 */
package wtg_jack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static wtg_jack.Main.FPS;
import static wtg_jack.Main.TILE_SIZE;
import static wtg_jack.Main.camera;
import wtg_jack.map.Map;
import wtg_jack.perso.Boby;
import wtg_jack.perso.Jack;
import wtg_jack.perso.Perso;

/**
 * Exploration.java
 *
 */
public class Exploration implements Screen, InputProcessor {

	private Batch batch;
	private Batch dialogBatch;

	public static final Map MAP = new Map();
	public static final Dialogue DIALOGUE = new Dialogue();

	private Jack jack;
	private Array<Perso> persos;

	public Exploration() {

		MAP.setView(camera);

		batch = MAP.getBatch();
		dialogBatch = new SpriteBatch();
		dialogBatch.setProjectionMatrix(camera.combined);

		jack = new Jack();
		jack.setPosition(TILE_SIZE, TILE_SIZE);

		persos = new Array<>();
		persos.addAll(
				jack,
				new Boby()
		);

		persos.get(1).setPosition(TILE_SIZE * 3, TILE_SIZE * 2);
		DIALOGUE.show("Bienvenue jeune dresseur ! Es-tu prêt à vivre la grande avanture ?");
	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		update();
//		temporize();
		renderer();
	}

	private void update() {
		//update
		for (Perso perso : persos) {
			perso.update();
		}

		camera.position.set(jack.getX(), jack.getY(), 0);
		camera.update();
		MAP.setView(camera);
	}

	private void temporize() {
		try {
			sleep(1000 / FPS);
		} catch (InterruptedException ex) {
			Logger.getLogger(Exploration.class.getName()).log(Level.SEVERE, null, ex);
		}
	}

	private void renderer() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

		//render
		MAP.render();
		batch.begin();
		for (Perso perso : persos) {
			perso.draw(batch);
		}
		batch.end();
		dialogBatch.begin();
		DIALOGUE.draw(dialogBatch);
		dialogBatch.end();
	}
	
	public static int toTile(float a) {
		return (int) a/ TILE_SIZE;
	}

	@Override
	public void resize(int width, int height) {
	}

	@Override
	public void pause() {
	}

	@Override
	public void resume() {
	}

	@Override
	public void hide() {
	}

	@Override
	public void dispose() {
	}

	@Override
	public boolean keyDown(int keycode) {
		jack.setKey(keycode);
		jack.action();
		return false;
	}

	@Override
	public boolean keyUp(int keycode) {
		return false;
	}

	@Override
	public boolean keyTyped(char character) {
		return false;
	}

	@Override
	public boolean touchDown(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchUp(int screenX, int screenY, int pointer, int button) {
		return false;
	}

	@Override
	public boolean touchDragged(int screenX, int screenY, int pointer) {
		return false;
	}

	@Override
	public boolean mouseMoved(int screenX, int screenY) {
		return false;
	}

	@Override
	public boolean scrolled(int amount) {
		return false;
	}

}
