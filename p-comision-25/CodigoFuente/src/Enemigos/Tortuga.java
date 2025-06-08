package Enemigos;

import Fabricas.Sprite;
import Visitor.Visitor;

public class Tortuga extends Enemigo {
	private int velocidad = 5;
    protected  EstadoTortuga estadoActual;

    public Tortuga(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        this.estadoActual = new KoopaTroopa(this); // Inicialización con estado KoopaTroopa
    }
    public void cambiarvelocidad (int factor) {
    	velocidad = factor;
    };
  
    public Sprite getSprite() {
		return estadoActual.getTortugaDer();
    }
    public void accept(Visitor v) {
        v.visitar(this);
    }

    public void cambiarEstado(EstadoTortuga  nuevoEstado) {
        this.estadoActual = nuevoEstado;
    }

    
    public void mover() {
    	x = x + ( velocidad * direccionDeEntidad) * estadoActual.variarVelocidad();
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
    }


	
	public void recibirGolpe() {
		estadoActual.golpeadoPorMario();
	}


	public void morir() {
		desaparecer();
		y += 32*50;
	}
	@Override
	public void recibirGolpePorBolaDefuego() {
		estadoActual.recibirGolpeBola();
		
	} 
 
    
	

}
