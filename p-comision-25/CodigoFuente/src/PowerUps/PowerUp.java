package PowerUps;

import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Entidad;
import Juego.Hitbox;
import Visitor.Visitor;

public abstract class PowerUp extends Entidad{
	
	
	public PowerUp(Sprite sprite, int x, int y) {
		super(sprite, x, y);  
	     this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());
	     direccionDeEntidad = 1;
	}


	@Override
	public void mover() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void cambiarDireccion() {
		direccionDeEntidad = direccionDeEntidad * (-1);
	}
	
	
	@Override
	public void recibirGolpe( ) {
		desaparecer();
		
		y += 32*50;
	}
	
	  //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    


}
