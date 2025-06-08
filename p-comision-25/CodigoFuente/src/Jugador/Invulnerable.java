package Jugador;

import Fabricas.FabricaSprites;

import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.SuperChampiñon;

public class Invulnerable implements Estado {
	
	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();
    protected Personaje personaje;
    protected Estado estadoPrevio;
   

    public Invulnerable(Personaje personaje, Estado estadoPrevio, long duracionInvulnerable) {
        this.personaje = personaje;
        this.estadoPrevio = estadoPrevio;
        iniciarInvulnerabilidad(duracionInvulnerable);
    }

    private void iniciarInvulnerabilidad(long duracion) {
        // Simulación de temporizador para finalizar la invulnerabilidad
        new java.util.Timer().schedule(new java.util.TimerTask() {
            @Override
            public void run() {
                finalizarInvulnerabilidad();
            }
        }, duracion);
    }

    private void finalizarInvulnerabilidad() {
        personaje.setEstado(estadoPrevio);
        System.out.println("La invulnerabilidad ha terminado.");
    }

	
    public void recibirGolpe(Personaje personaje) {
       
    }

	
    public boolean  puedeRomperLadrillo() {		
		return true; 
	}
	
	
	public int variarVelocidad() {
		return 1;
	}

	
	public Sprite getMarioInmovilDer() {
		
		return fabricaSprites.getSuperMarioInvencibleInmovilDer();
	}

	
	public Sprite getMarioMoviendoDer() {
		
		return fabricaSprites.getSuperMarioInvencibleInmovilDer();
	}

	
	public Sprite getMarioMoviendoIzq() {
		
		return fabricaSprites.getSuperMarioInvencibleInmovilIzq();
	}

	
	public Sprite getMarioInmovilIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioInvencibleInmovilIzq();
	}

	@Override
	public Sprite getMarioSaltandoDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioInvencibleInmovilDer();
	}

	@Override
	public Sprite getMarioSaltandoIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getSuperMarioInvencibleInmovilIzq();
	}

	@Override
	public void lanzarBolasDeFuego() {
	  System.out.print("Mario lanza siguiendo el estado anterior");
		
		estadoPrevio.lanzarBolasDeFuego();
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
		return 5;
	}

	@Override
	public boolean indestructuble() {
		// TODO Auto-generated method stub
		return true;
	}



	

}
