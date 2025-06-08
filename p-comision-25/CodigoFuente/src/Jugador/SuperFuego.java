package Jugador;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Grafica.Observer;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.SuperChampiñon;

public class SuperFuego implements Estado {

	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();
	protected Personaje personaje;
	  public SuperFuego(Personaje personaje) {
	        this.personaje = personaje;
	    }

    @Override
    public void recibirGolpe(Personaje personaje) {
        personaje.setEstado(new Normal());  // Retrocede a estado Super en lugar de Normal
    }

	@Override
	public boolean puedeRomperLadrillo() {
		return true;
	}

	@Override
	public int variarVelocidad() {
		return 1;
	}


	
	public Sprite getMarioInmovilDer() {
		return fabricaSprites.getSuperMarioBolaDeFuegoInmovilDer();
	}

	
	public Sprite getMarioMoviendoDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioBolaDeFuegoMoviendoDer();
	}

	
	public Sprite getMarioMoviendoIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioBolaDeFuegoMoviendoIzq();
	}

	@Override
	public Sprite getMarioInmovilIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioBolaDeFuegoInmovilIzq();
	}

	@Override
	public Sprite getMarioSaltandoDer() {
		return fabricaSprites.getSuperMarioBolaDeFuegoSaltandoDer();
	}

	@Override
	public Sprite getMarioSaltandoIzq() {
		return fabricaSprites.getSuperMarioBolaDeFuegoSaltandoIzq();
	}



	@Override
	public void lanzarBolasDeFuego() {
	    // Crear la bola de fuego en la posición de Mario, ajustando según la dirección
	    BolaDeFuego bolaDefuego = personaje.jugador.getNivel().agregarBolaDeFuego(
	        personaje.jugador.getX() + (personaje.jugador.direccion == 2 || personaje.jugador.direccion == -1 ? -44 : 10), 
	        personaje.jugador.getY() + 14
	    );

	    // Registrar la bola de fuego en el sistema de observadores
	    Observer obv = personaje.jugador.getNivel().getJuego().getControlador().registrarEntidad(bolaDefuego);
	    bolaDefuego.registrarObserver(obv);

	    // Cambiar la dirección de la bola de fuego si Mario está mirando hacia la izquierda
	    if (personaje.jugador.direccion == 2 || personaje.jugador.direccion == -1) {
	        bolaDefuego.cambiarDireccion();
	    }
	}

	@Override
	public int obtenerPuntajeSuperChampiñon() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public int obtenerPuntajeEstrella() {
		// TODO Auto-generated method stub
		return 30;
	}

	@Override
	public int obtenerPuntajeFlordeFuego() {
		// TODO Auto-generated method stub
		return 50;
	}

	@Override
	public boolean indestructuble() {
		// TODO Auto-generated method stub
		return false;
	}

	
	



	
}
