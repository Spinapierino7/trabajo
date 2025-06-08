package PowerUps;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Plataformas.Plataforma;
import Visitor.Visitor;

public class FlorDeFuego extends PowerUp{


	private int velocidad;
	protected Observer observer;
	protected Hitbox hitbox;


	public FlorDeFuego(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 2;
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
    }

    public void mover() {
    	  hitbox.setX(x);
          hitbox.setY(y);
          observer.actualizar();
    }
    
	

    public void registrarObserver(Observer observer) {
        this.observer = observer;
    }

   
    public void recibirGolpe( ) {
		desaparecer();
		detener();
		y += 32*50;
	}
    
    public Hitbox getHitbox() {
        return hitbox;
    }

    public void detener() {
    	velocidad = 0;
    }

	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitar(this); // Llama al método visitar() del Visitor
	}

}
