package Enemigos;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Visitor.Visitor;

public class KoopaTroopa  implements  EstadoTortuga {
	protected  Tortuga tortuga;
	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();

	public KoopaTroopa(Tortuga tortuga) {
		this.tortuga = tortuga;
		
	}

	
	public void golpeadoPorMario() {
		tortuga.cambiarvelocidad(4);
	      tortuga.cambiarEstado(new Caparazon(tortuga));
		  System.out.print("la tortuga recibio un golpe, ahora cambia de estado");
		  
	}


	@Override
	public int variarVelocidad() {
		return 1;
	}


	@Override
	public Sprite getTortugaDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getKoopaTroopa();
	}


	@Override
	public Sprite getTortugaIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getKoopaTroopa();
	}


	@Override
	public void recibirGolpeBola() {
		tortuga.sesion.incrementarPuntaje(60);
		tortuga.morir();
		
	}

	
	
	   
	   
}
