package Enemigos;

import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Juego.SesionDeJuego;
import Visitor.Visitor;

public class Caparazon  implements  EstadoTortuga {
	protected  Tortuga tortuga;
	protected FabricaSprites fabricaSprites = new FabricaSpritesOriginales();
	public Caparazon(Tortuga tortuga) {
		this.tortuga = tortuga;
		
	}


	public void golpeadoPorMario() {
	     //tortuga.morir();
		  System.out.print("la tortuga esaba en caparazon, recibio un golpe y murio");
		  
	}


	
	public int variarVelocidad() {
		return 3;
	}


	
	public Sprite getTortugaDer() {
		// TODO Auto-generated method stub
		return fabricaSprites.getCaparazon();
	}


	
	public Sprite getTortugaIzq() {
		// TODO Auto-generated method stub
		return fabricaSprites.getCaparazon();
	}


	@Override
	public void recibirGolpeBola() {
		tortuga.sesion.incrementarPuntaje(90);
		tortuga.morir();
	}
	
	
	   
	   
}
