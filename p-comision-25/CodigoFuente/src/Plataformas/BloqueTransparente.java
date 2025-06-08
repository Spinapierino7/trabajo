package Plataformas;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class BloqueTransparente extends Plataforma {

	public BloqueTransparente(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		
	}

	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}
	public boolean isTransparente() {
		return true;
	}
	
	public boolean puedeInteractuar() {
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
