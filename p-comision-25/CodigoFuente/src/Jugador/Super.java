package Jugador;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Sonido.SonidoManager;

public class Super implements Estado {
	
	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();
	
		 
    @Override
    public void recibirGolpe(Personaje personaje) {
       
        personaje.setEstado(new Normal()); // Cambiar a estado Normal
       
    }
    
	@Override
	public boolean puedeRomperLadrillo() {
		return true;
		
	}

	@Override
	public int variarVelocidad() {
		// TODO Auto-generated method stub
		return 1;
	}

	@Override
	public Sprite getMarioInmovilDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioSuperInmovilDer();
	}

	@Override
	public Sprite getMarioMoviendoDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioSuperMoviendoDer();
	}

	@Override
	public Sprite getMarioMoviendoIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioSuperMoviendoIzq();
	}

	@Override
	public Sprite getMarioInmovilIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getMarioSuperInmovilIzq();
	}

	@Override
	public Sprite getMarioSaltandoDer() {
		// TODO Auto-generated method stub
    	SonidoManager.getInstancia().reproducirEfecto("saltandoMarioGrande");
		return fabricaSprites.getMarioSuperSaltandoDer();
	}

	@Override
	public Sprite getMarioSaltandoIzq() {
		// TODO Auto-generated method stub
		SonidoManager.getInstancia().reproducirEfecto("saltandoMarioGrande");
		return fabricaSprites.getMarioSuperSaltandoIzq();
	}

	@Override
	public void lanzarBolasDeFuego() {
		System.out.println("no puede lanzar en estado super");
	}

	@Override
	public int obtenerPuntajeSuperChampiñon() {
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
		return 30;
	}

	@Override
	public boolean indestructuble() {
		// TODO Auto-generated method stub
		return false;
	}



}
