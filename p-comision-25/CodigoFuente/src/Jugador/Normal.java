package Jugador;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.SuperChampiñon;

public class Normal implements Estado {

	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();

	public void mover(Jugador jugador) {
		System.out.print("Mario en estado normal se mueve");
		jugador.mover();
	}

	public void saltar(Jugador jugador) {

		System.out.print("Mario en estado normal salta");
		jugador.saltar();

	}

	public void lanzarBolasDeFuego(Personaje personaje) {

		System.out.print("Mario en estado normal no lanza bolas de fuego");
	}

	public void recibirGolpe(Personaje personaje) {
		personaje.jugador.perderVida();
	}

	@Override
	public boolean puedeRomperLadrillo() {
		return false;
	}

	@Override
	public int variarVelocidad() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Sprite getMarioInmovilDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalInmovilDer();
	}

	@Override
	public Sprite getMarioMoviendoDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalMoviendoDer();
	}

	@Override
	public Sprite getMarioMoviendoIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalMoviendoIzq();
	}

	@Override
	public Sprite getMarioInmovilIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalInmovilIzq();
	}

	@Override
	public Sprite getMarioSaltandoDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalSaltandoDer();
	}

	@Override
	public Sprite getMarioSaltandoIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioNormalSaltandoIzq();
	}




	@Override
	public void lanzarBolasDeFuego() {
		System.out.println("no puede lanzar en estado normal");
	}

	@Override
	public int obtenerPuntajeSuperChampiñon() {
		// TODO Auto-generated method stub
		return 10;
	}

	@Override
	public int obtenerPuntajeEstrella() {
		// TODO Auto-generated method stub
		return 20;
	}

	@Override
	public int obtenerPuntajeFlordeFuego() {
		// TODO Auto-generated method stub
		return 5;
	}

	@Override
	public boolean indestructuble() {
		// TODO Auto-generated method stub
		return false;
	}


}
