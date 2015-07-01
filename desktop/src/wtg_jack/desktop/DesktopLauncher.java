package wtg_jack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wtg_jack.Main;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplication application = new LwjglApplication(new Main(), getConfig());
	}

	private static LwjglApplicationConfiguration getConfig() {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Les aventures de Jack à la recherche du mythe de la pomme de pain magique";
		config.useGL30 = false;
		config.width = 160 * Main.RATIO_SIZE;
		config.height = 128 * Main.RATIO_SIZE;
		config.resizable = false;
		config.fullscreen = false;

		return config;
	}
}
