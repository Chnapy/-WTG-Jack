package wtg_jack.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import wtg_jack.Jeu;

public class DesktopLauncher {

	public static void main(String[] arg) {
		LwjglApplication application = new LwjglApplication(new Jeu(), getConfig());
	}

	private static LwjglApplicationConfiguration getConfig() {

		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Les aventures de Jack à la recherche du mythe de la pomme de pain magique";
		config.useGL30 = false;
		config.width = Jeu.WIDTH * Jeu.RATIO_SIZE;
		config.height = Jeu.HEIGHT * Jeu.RATIO_SIZE;
		config.resizable = false;
		config.fullscreen = false;
//		config.vSyncEnabled = true;

		return config;
	}
}
