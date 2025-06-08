package Plataformas;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Juego.Hitbox;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class BloqueFinal extends Plataforma{
	
	protected Hitbox hitbox;

	public BloqueFinal(Sprite sprite, int x, int y) {
		super(sprite, x-84, y+288);
		
		this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
	}
	
	public Hitbox getHitbox() {
		return this.hitbox;
	}

	public void accept(Visitor visitor) {
		visitor.visitar(this); // Llama al método visitar() del Visitor
	}
	public void recibirGolpe() {
	}
	
	public boolean isFinal() {
		return true;
	}

}
