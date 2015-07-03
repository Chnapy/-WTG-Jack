package wtg_jack;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.graphics.OrthographicCamera;

public class Main extends Game {

	public static final int FPS = 30;
	public static final int TILE_SIZE = 16;
	public static int RATIO_SIZE = 3;
	private static int oldWidth = -1;
	private static int oldHeight = -1;
	
	public static final OrthographicCamera camera = new OrthographicCamera();
	
	private Exploration exploration;

	@Override
	public void create() {
		camera.setToOrtho(false, 160, 128);
		camera.update();
		
		exploration = new Exploration();
		
		this.setScreen(exploration);
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
