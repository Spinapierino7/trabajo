package Enemigos;

import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Visitor.Visitor;

public class BuzzyBeetle extends Enemigo {

	private int velocidad;

	
	protected Observer observer;

	
	protected Hitbox hitbox;


	public BuzzyBeetle(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 3;
       
        
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
    }

    public void mover() {
    	x += velocidad * direccionDeEntidad ;
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
    }

    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

    
    public Hitbox getHitbox() {
        return hitbox;
    }
    @Override
  	public void recibirGolpe() {
  		desaparecer();
  		direccionDeEntidad =0;
  		y += 32*50;
  		sesion.incrementarPuntaje(30);
  	}

  	@Override
  	public void recibirGolpePorBolaDefuego() {
  		recibirGolpe();
  	}
    //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    
    
}
