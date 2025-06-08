package Juego;

import Fabricas.Sprite;

public interface EntidadLogica {
	
	public Sprite getSprite();
	public int getX();
	public int getY();
	public void mover();
	public void cambiarDireccion();
	public void recibirGolpe();

}
