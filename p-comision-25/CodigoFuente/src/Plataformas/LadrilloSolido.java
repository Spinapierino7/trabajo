package Plataformas;

import java.util.Random;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Juego.Nivel;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class LadrilloSolido extends Plataforma {

	protected Hitbox hitbox;
	private boolean ladrilloRoto = false;
	private Nivel nivel;

	public LadrilloSolido(Sprite sprite, int x, int y) {
		super(sprite, x, y);

		this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
	}

	public Hitbox getHitbox() {
		return this.hitbox;
	}


	@Override
	public void recibirGolpe() {

		if (!ladrilloRoto) {
			nivel.intercambiarPorTransparente(this);
			x += 20*32;
			y += 20*32;
			this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
			sprite = fabrica.getBloqueTransparente();
			observer.actualizar();
		}
		ladrilloRoto = true;			
	}
	
	public void setNivel(Nivel nivel) {
		this.nivel = nivel;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitar(this); 
	}

}
