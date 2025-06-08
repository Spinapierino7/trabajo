package Enemigos;

import Fabricas.Sprite;
import Juego.Entidad;
import Juego.SesionDeJuego;
import Plataformas.Plataforma;
import Visitor.Visitor;

public abstract class Enemigo extends Entidad{
	protected SesionDeJuego sesion;
	public Enemigo(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		direccionDeEntidad = -1;
	}

	
	public abstract void mover();
    
	
	public void cambiarDireccion() {
		direccionDeEntidad = direccionDeEntidad * (-1);
		
	}

	public abstract void recibirGolpePorBolaDefuego();
	
    public void setSesion(SesionDeJuego sesion){
    	this.sesion = sesion;
    }
    //patron Visitor redefinido en clases especificas
	public void accept(Visitor v) {
	    v.visitar(this);
	}

	public void actualizar(long deltaTiempo) {
	}

	public void setCayendo(boolean x) {
	}
	
	public void colisionPlataforma(Plataforma plataforma) {
	}
}