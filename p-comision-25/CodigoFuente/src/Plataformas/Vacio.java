package Plataformas;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class Vacio extends Plataforma{

	public Vacio(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	public void mover() {
		// no necesita moverse
	}
	public boolean isTransparente() {
		return true;
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
