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
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static wtg_jack.Main.camera;
import wtg_jack.map.Map;
import wtg_jack.perso.Jack;

/**
 * Exploration.java
 *
 */
public class Exploration implements Screen, InputProcessor {

	protected static final int FPS = 30;

	private Batch batch;

	private Map map;

	private Jack jack;

	public Exploration() {

		map = new Map();
		map.setView(camera);

		batch = map.getBatch();

		jack = new Jack();
		jack.setPosition(16, 16);

	}

	@Override
	public void show() {
		Gdx.input.setInputProcessor(this);
	}

	@Override
	public void render(float delta) {
		update();
		temporize();
		renderer();
	}

	private void update() {
		//update
		jack.update();

		camera.position.set(jack.getX(), jack.getY(), 0);
		camera.update();
		batch.setProjectionMatrix(camera.combined);
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
		map.render();
		batch.begin();
		jack.draw(batch);
		batch.end();
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
