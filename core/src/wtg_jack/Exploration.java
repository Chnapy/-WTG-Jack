/*
 * 
 * 
 * 
 */
package wtg_jack;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Batch;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import static wtg_jack.Main.camera;
import wtg_jack.map.Map;
import wtg_jack.perso.Jack;

/**
 * Exploration.java
 * 
 */
public class Exploration implements Screen {
	
	private Batch batch;
	
	private Map map;
	
	private Jack jack;
	
	public Exploration() {
		
		map = new Map();
		map.setView(camera);
		
		batch = map.getBatch();
		
		jack = new Jack();
		jack.setPosition(10, 10);
	}

	@Override
	public void show() {
	}

	@Override
	public void render(float delta) {
		jack.update();
		
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

}
