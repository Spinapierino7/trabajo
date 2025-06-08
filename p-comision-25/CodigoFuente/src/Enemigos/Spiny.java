package Enemigos;

import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Juego.Nivel;
import Jugador.Jugador;
import Plataformas.BloqueFinal;
import Plataformas.Plataforma;
import Visitor.Visitor;

public class Spiny extends Enemigo {

	private int velocidad;
	protected Jugador jugador;
	protected Observer observer;
	protected Hitbox hitbox;
	protected int velocidadCaida = 2;
	protected boolean cayendo;

	public Spiny(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 3;
        cayendo=true;
        
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
    }
	public boolean cayendo() {
		return cayendo;
	}
	public void colisionPlataforma(Plataforma plataforma) {	
		if(!plataforma.isFinal() && !plataforma.isTransparente()) {
			this.setCayendo(false);
			this.setX(plataforma.getX());
			this.setY(plataforma.getY()+32);
		}
	}
	
    public void mover() {
    	
    	if(!cayendo) {    	
    		x += velocidad * direccionDeEntidad ;
    	}else {
    		y -= velocidadCaida *1.4;
    	}
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
    }
    public void setCayendo(boolean x) {
    	cayendo=x;
    }
   
    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

    
    public Hitbox getHitbox() {
        return hitbox;
    }


  	public void recibirGolpe() {
  		desaparecer();
  		direccionDeEntidad =0;
  		y += 32*50;
  		sesion.incrementarPuntaje(60);
  	}

  	public void recibirGolpePorBolaDefuego() {
  		
  		recibirGolpe();
  	}
  	
    //patron Visitor redefinido en clases especificas
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    
	
}