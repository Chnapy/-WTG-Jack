/*
 * 
 * 
 * 
 */
package wtg_jack.perso.pnj;

import com.badlogic.gdx.graphics.g2d.TextureRegion;

/**
 * Dresseur.java
 * 
 */
public abstract class Dresseur extends PNJ {
	
	private String fin;

	public Dresseur(TextureRegion[] walksTop, TextureRegion[] walksBottom, TextureRegion[] walksLeft, TextureRegion[] walksRight,
			String _nom, IAmove iamove,
			String intro, String fin) {
		super(walksTop, walksBottom, walksLeft, walksRight, _nom, iamove, intro);
		this.fin = fin;
	}

}
