package Plataformas;

import java.util.Random;


import Fabricas.Sprite;
import Grafica.Observer;
import Juego.Hitbox;
import Juego.Nivel;
import PowerUps.PowerUp;
import Visitor.Visitor;

public class BloqueDePregunta extends Plataforma {

	protected Hitbox hitbox;
	// protected PowerUp powerUpContenido;
	protected boolean recibioGolpe;
	protected Nivel nivelActual;

	public BloqueDePregunta(Sprite sprite, int x, int y) {
		super(sprite, x, y);
		this.hitbox = new Hitbox(x, y, sprite.getAncho(), sprite.getAlto());

		recibioGolpe = false;
	}

	public Hitbox getHitbox() {
		return this.hitbox;
	}

	@Override
	public void recibirGolpe() {// si entro a este metodo es porque mario le salto por abajo
		System.out.println("Suelto el power Up y me quedo como bloque solido");

		if (!recibioGolpe) {
			//genero el powerup
			PowerUp powerUp = generarPowerUp();
			sprite = fabrica.getBloque();
			observer.actualizar();
		}
		recibioGolpe = true;
	}

	public PowerUp generarPowerUp() {
		PowerUp powerUp = null; // Inicializamos la variable
		Observer obv = null;
		Random random = new Random();
		int opcion = random.nextInt(5); // Número aleatorio entre 0 y 4
		
		System.out.println("Opción generada: " + opcion);

		switch (opcion) {
		case 0:
			powerUp = nivelActual.agregarEntidad(17, x, y + 34);
			obv = nivelActual.getJuego().getControlador().registrarEntidad(powerUp);
			powerUp.registrarObserver(obv);
			break;
		case 1:
			powerUp = nivelActual.agregarEntidad(14, x, y + 34);
			obv = nivelActual.getJuego().getControlador().registrarEntidad(powerUp);
			powerUp.registrarObserver(obv);
			
			break;
		case 2:
			powerUp = nivelActual.agregarEntidad(16, x, y + 34);
			obv = nivelActual.getJuego().getControlador().registrarEntidad(powerUp);
			powerUp.registrarObserver(obv);
			
			break;
		case 3:
			powerUp = nivelActual.agregarEntidad(15, x, y + 34);
			obv = nivelActual.getJuego().getControlador().registrarEntidad(powerUp);
			powerUp.registrarObserver(obv);
			
			break;
		case 4:
			powerUp = nivelActual.agregarEntidad(13, x, y + 34);
			obv = nivelActual.getJuego().getControlador().registrarEntidad(powerUp);
			powerUp.registrarObserver(obv);
			
			break;
		default:
			throw new IllegalStateException("Opción inesperada: " + opcion);
		}
	
		return powerUp;
	}

	@Override
	public void accept(Visitor visitor) {
		visitor.visitar(this); 
	
	}

	public void setearNivel(Nivel nivel) {
		nivelActual = nivel;
	}

}