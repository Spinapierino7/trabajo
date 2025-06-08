package Plataformas;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Juego.Hitbox;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class BloqueSuelo extends Plataforma{
	
	protected Hitbox hitbox;

	public BloqueSuelo(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		
		this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
	}
	
	public Hitbox getHitbox() {
		return this.hitbox;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitar(this); // Llama al método visitar() del Visitor
	}
	
	@Override
	public void recibirGolpe() {
		// TODO Auto-generated method stub
		
	}
}