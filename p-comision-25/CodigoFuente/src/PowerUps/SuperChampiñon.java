package PowerUps;

import Enemigos.Enemigo;
import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Plataformas.Plataforma;
import Visitor.Visitor;

public class SuperChampiñon extends PowerUp {
	private int velocidad;
	protected Observer observer;
	protected Hitbox hitbox;


	public SuperChampiñon(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 2;
        this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
    }

    public void mover() {
    	x += velocidad * direccionDeEntidad ;
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
    }
    
	@Override
	public void recibirGolpe( ) {
		desaparecer();
		detener();
		y += 32*50;
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

    //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    
}
