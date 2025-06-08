package Jugador;

import Fabricas.FabricaSonido;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampiñon;
import Sonido.SonidoManager;

public class Personaje {
   
    protected Estado estadoActual;
    protected Estado estadoPrevio;
    protected Jugador jugador;
    protected long duracionInvulnerable = 10000;

    public Personaje(Jugador j, Estado estadoInicial) {
        this.jugador = j;
        setEstado(estadoInicial);
    }
    
    public void setEstado(Estado nuevoEstado) {
        estadoActual = nuevoEstado;
    }
    public Estado getEstado() {
    	return estadoActual;
    }

    public void consumirEstrella() {
    	SonidoManager sonido= SonidoManager.getInstancia();
		FabricaSonido fabrica = new FabricaSonido(sonido);
		fabrica.setMarioEstrella();
        estadoPrevio = estadoActual;  
        estadoActual = new Invulnerable(this, estadoPrevio, duracionInvulnerable);  
    }
    
}
