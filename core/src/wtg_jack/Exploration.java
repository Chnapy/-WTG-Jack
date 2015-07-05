/*
 * 
 * 
 * 
 */
package wtg_jack;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.InputProcessor;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import java.awt.Point;
import static java.lang.Thread.sleep;
import java.util.logging.Level;
import java.util.logging.Logger;
import static wtg_jack.Jeu.FPS;
import static wtg_jack.Jeu.TILE_SIZE;
import static wtg_jack.Jeu.camera;
import wtg_jack.map.Map;
import wtg_jack.perso.Boby;
import wtg_jack.perso.Jack;
import wtg_jack.perso.Perso;
import wtg_jack.transition.Fondu;

/**
 * Exploration.java
 *
 */
public class Exploration implements Screen, InputProcessor {

	private Batch batch;
	private Batch dialogBatch;
	private ShapeRenderer shapeRen;

	public static final Map MAP = new Map();
	private static Dialogue DIALOGUE = new Dialogue() {
		@Override
		public void end() {
		}
	};
	private static Fondu FONDU = new Fondu(false) {
		@Override
		public void end() {
		}
		@Override
		public void nextPos() {
		}
	};

	public static final Jack jack = new Jack();
	private static final Array<Perso> persos = new Array<>();

	public Exploration() {

		MAP.setView(camera);

		batch = MAP.getBatch();
		dialogBatch = new SpriteBatch();
		dialogBatch.setProjectionMatrix(camera.combined);
		shapeRen = new ShapeRenderer();
		shapeRen.setProjectionMatrix(camera.combined);
		shapeRen.setColor(Color.BLACK);

		jack.setPosition(TILE_SIZE, TILE_SIZE);

		persos.addAll(
				jack,
				new Boby()
		);

		persos.get(1).setPosition(TILE_SIZE * 3, TILE_SIZE * 2);
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
		
		if(FONDU.isShow()) {
			FONDU.next();
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

		if (DIALOGUE.isShow()) {
			dialogBatch.begin();
			DIALOGUE.draw(dialogBatch);
			dialogBatch.end();
		}

		if (FONDU.isShow()) {
			shapeRen.begin(ShapeRenderer.ShapeType.Filled);
			for(Point p : FONDU.getPos()) {
				shapeRen.rect(p.x * TILE_SIZE, p.y * TILE_SIZE, TILE_SIZE, TILE_SIZE);
			}
			shapeRen.end();
		}
	}

	public static int toTile(float a) {
		return (int) a / TILE_SIZE;
	}

	public static void pauseAll() {
		for (int i = 0; i < persos.size; i++) {
			persos.get(i).pause();
		}
	}

	public static void playAll() {
		for (int i = 0; i < persos.size; i++) {
			persos.get(i).play();
		}
	}

	public static void setDialogue(Dialogue dial) {
		DIALOGUE = dial;
	}

	public static Dialogue getDialogue() {
		return DIALOGUE;
	}
	
	public static void setFondu(Fondu fon) {
		FONDU = fon;
	}
	
	public static Fondu getFondu() {
		return FONDU;
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
