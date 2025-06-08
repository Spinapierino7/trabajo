package Visitor;

import Enemigos.BuzzyBeetle;
import Enemigos.Enemigo;
import Enemigos.Goomba;
import Enemigos.KoopaTroopa;
import Enemigos.Lakitu;
import Enemigos.PiranhaPlant;
import Enemigos.Spiny;
import Enemigos.Tortuga;
import Juego.Entidad;
import Jugador.Jugador;
import Plataformas.BloqueDePregunta;
import Plataformas.BloqueFinal;
import Plataformas.BloqueTransparente;
import Plataformas.Plataforma;
import Plataformas.Vacio;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampiñon;

public interface Visitor {
	void visitar(Entidad entidad);

	void visitar(Enemigo enemigo);

	void visitar(Plataforma plataforma);

	void visitar(Jugador jugador);

	void visitar(PowerUp powerup);

	void visitar(SuperChampiñon powerup);

	void visitar(Moneda powerup);

	void visitar(FlorDeFuego powerup);

	void visitar(ChampiñonVerde powerup);

	void visitar(Estrella powerup);

	void visitar(BloqueTransparente bloque);

	void visitar(BloqueFinal bloque);

	void visitar(BloqueDePregunta bloque);

	// mario se topa con los diferentes enemigos

	void visitar(BuzzyBeetle buzzyBeetle);

	void visitar(Tortuga Tortuga);

	void visitar(Goomba goomba);

	void visitar(Lakitu lakitu);

	void visitar(PiranhaPlant PiranhaPlant);

	void visitar(Spiny Spiny);

}