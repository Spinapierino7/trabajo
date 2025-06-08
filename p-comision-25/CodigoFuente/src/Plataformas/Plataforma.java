package Plataformas;

import Fabricas.Sprite;
import Juego.Entidad;
import Juego.Nivel;

public abstract class Plataforma extends Entidad{

	public Plataforma(Sprite sprite, int x, int y) {
		super(sprite, x, y);
	}

	@Override
	public void mover() {
	}
	public boolean isTransparente() {
		return false;
	}
	
	public boolean isFinal() {
		return false;
	}
	@Override
	public void cambiarDireccion() {		
	}
	public void setearNivel(Nivel nivelActual){
	}
}