package PowerUps;


import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Visitor.Visitor;

public class ChampiñonVerde extends PowerUp{
	
	private int velocidad;
	
	protected Observer observer;
	
	protected Hitbox hitbox;

	public ChampiñonVerde(Sprite sprite, int x, int y) {
        super(sprite, x, y);
        velocidad = 1;
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

	
	@Override
	public void accept(Visitor visitor) {
		visitor.visitar(this); // Llama al método visitar() del Visitor
	}

}
