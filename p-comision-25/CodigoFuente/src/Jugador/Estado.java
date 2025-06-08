package Jugador;

import Fabricas.Sprite;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.SuperChampiñon;

public interface Estado {
		boolean puedeRomperLadrillo();
	    void lanzarBolasDeFuego(); 
	    void recibirGolpe(Personaje personaje);
	    int variarVelocidad();
	  
	    Sprite getMarioInmovilDer();
		Sprite getMarioInmovilIzq();
		
		Sprite getMarioMoviendoDer();
		Sprite getMarioMoviendoIzq();

		Sprite getMarioSaltandoDer();
		Sprite getMarioSaltandoIzq();
		int obtenerPuntajeSuperChampiñon();
		int obtenerPuntajeEstrella();
		int obtenerPuntajeFlordeFuego();
		boolean indestructuble();
		
}
