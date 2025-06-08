package Enemigos;

import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Juego.SesionDeJuego;
import Plataformas.Plataforma;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class Goomba extends Enemigo{
	
	private int velocidad;
	protected Observer observer;
	protected Hitbox hitbox;


	public Goomba(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 5;
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
    }

    public void mover() {
    	x += velocidad * direccionDeEntidad ;
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
    }
    
	@Override
	public void recibirGolpe() {
		desaparecer();
		detener();
		y += 32*50;
		sesion.incrementarPuntaje(60);
	}
	

    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

   
    
    public Hitbox getHitbox() {
        return hitbox;
    }

    public void detener() {
    	velocidad = 0;
    }

	@Override
	public void recibirGolpePorBolaDefuego() {
		sesion.incrementarPuntaje(60);
		recibirGolpe();
	}
	  //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    




}
