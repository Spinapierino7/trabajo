package Jugador;

import Fabricas.Sprite;
import Juego.Entidad;

public class BolaDeFuego extends Entidad{
	
	private int velocidad;
	private int direccion = 1;
	
	
	public BolaDeFuego(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		velocidad=10;
	}

	@Override
	public void mover() {
		x += velocidad * direccion;
        hitbox.setX(x);
        hitbox.setY(y);
        observer.actualizar();
		
	}

	@Override
	public void cambiarDireccion() {
		direccion = direccion * (-1);
	}

	

	@Override
	public void recibirGolpe() {
		desaparecer();
		direccion =0;
		y += 32*60;
	}


}
