package wtg_jack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Main extends Game {

	public static int RATIO_SIZE = 3;
	private static int oldWidth = -1;
	private static int oldHeight = -1;
	
	public static final OrthographicCamera camera = new OrthographicCamera();
	
	static {
	}
	
	private Exploration exploration;

	@Override
	public void create() {
		camera.setToOrtho(false, 160, 128);
		camera.update();
		
		exploration = new Exploration();
		
		this.setScreen(exploration);

	}

	@Override
	public void render() {
		Gdx.gl.glClearColor(0, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
		
		camera.update();
		
		super.render();
	}

	@Override
	public void resize(int width, int height) {
		if (oldWidth != -1) {
			if (width > oldWidth || height > oldHeight) {
				RATIO_SIZE++;
			} else if ((width < oldWidth || height < oldHeight) && RATIO_SIZE > 1) {
				RATIO_SIZE--;
			}
		}
		oldWidth = width;
		oldHeight = height;
		
//		super.resize(160 * RATIO_SIZE, 128 * RATIO_SIZE);
	}
}
