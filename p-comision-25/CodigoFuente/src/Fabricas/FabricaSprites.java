package Fabricas;

public abstract class FabricaSprites {
	protected String rutaACarpeta;
	
	protected FabricaSprites(String rutaACarpeta) {
		this.rutaACarpeta=rutaACarpeta; 
	}
	
	public Sprite getSilueta() {
		return new Sprite(rutaACarpeta +"/silueta1.png");
	}
	
	
	
	// Mario y Estados de Mario 
	
	////normal 
	public Sprite getMarioNormalInmovilDer() {
		return new Sprite(rutaACarpeta +"/marioInmovilDer.png");
	}
	
	public Sprite getMarioNormalInmovilIzq() {
		return new Sprite(rutaACarpeta +"/marioInmovilIzq.png");
	}
	
	public Sprite getMarioNormalMoviendoDer() {
		return new Sprite(rutaACarpeta +"/marioMoviendoseDer.gif");
	}
	
	public Sprite getMarioNormalMoviendoIzq() {
		return new Sprite(rutaACarpeta +"/marioMoviendoseIzq.gif");
	}
	
	public Sprite getMarioNormalSaltandoDer() {
		return new Sprite(rutaACarpeta +"/marioSaltandoDer.png");
	}
	
	public Sprite getMarioNormalSaltandoIzq() {
		return new Sprite(rutaACarpeta +"/marioSaltandoIzq.png");
	}
	
	
	
	////super 
	public Sprite getMarioSuperInmovilDer() {
		return new Sprite(rutaACarpeta +"/superMarioInmovilDer.png");
	}
	
	public Sprite getMarioSuperInmovilIzq() {
		return new Sprite(rutaACarpeta +"/superMarioInmovilIzq.png");
	}
	
	public Sprite getMarioSuperMoviendoDer() {
		return new Sprite(rutaACarpeta +"/superMarioMoviendoseDer.gif");
	}
	
	public Sprite getMarioSuperMoviendoIzq() {
		return new Sprite(rutaACarpeta +"/superMarioMoviendoseIzq.gif");
	}
	
	public Sprite getMarioSuperSaltandoDer() {
		return new Sprite(rutaACarpeta +"/superMarioSaltandoDer.png");  
	}
	
	public Sprite getMarioSuperSaltandoIzq() {
		return new Sprite(rutaACarpeta +"/superMarioSaltandoIzq.png");  
	}
	
	
	////super Fuego
	public Sprite getSuperMarioBolaDeFuegoInmovilDer() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoInmovilDer.png");
	}
	
	public Sprite getSuperMarioBolaDeFuegoInmovilIzq() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoInmovilIzq.png");
	}
	
	public Sprite getSuperMarioBolaDeFuegoMoviendoDer() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoMoviendoseDer.gif");
	}
	
	public Sprite getSuperMarioBolaDeFuegoMoviendoIzq() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoMoviendoseIzq.gif");
	}
	
	public Sprite getSuperMarioBolaDeFuegoSaltandoDer() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoSaltandoDer.png");
	}
	
	public Sprite getSuperMarioBolaDeFuegoSaltandoIzq() {
		return new Sprite(rutaACarpeta +"/marioBolaDeFuegoSaltandoIzq.png");
	}
	
	
	////invencible
	public Sprite getSuperMarioInvencibleInmovilDer() {
		return new Sprite(rutaACarpeta +"/marioInvencibleInmovilDer.gif");
	}
	
	public Sprite getSuperMarioInvencibleInmovilIzq() {
		return new Sprite(rutaACarpeta +"/marioInvencibleInmovilIzq.gif");
	}
	
	
	
	public Sprite getBuzzyBeetle() {
		return new Sprite(rutaACarpeta +"/buzzyBeetle.gif");
	}
	
	public Sprite getGoomba() {
		return new Sprite(rutaACarpeta +"/goomba.gif");
	}
	
	public Sprite getKoopaTroopa() {
		return new Sprite(rutaACarpeta +"/koopaTroopa.gif");
	}
	public Sprite getKoopaTroopaIz() {
		return new Sprite(rutaACarpeta +"/KoopaTroopaIz.gif");
	}
	
	public Sprite getLakitu() {
		return new Sprite(rutaACarpeta +"/lakitu.gif");
	}
	
	public Sprite getPiranhaPlant() {
		return new Sprite(rutaACarpeta +"/piranhaPlant.gif");
	}
	
	public Sprite getSpiny() {
		return new Sprite(rutaACarpeta +"/spiny.gif");
	}
	
	public Sprite getBloque() {
		return new Sprite(rutaACarpeta +"/bloque.png");
	}
	
	public Sprite getBloqueDePregunta() {
		return new Sprite(rutaACarpeta +"/bloqueDePregunta.gif");
	}
	
	public Sprite getLadrilloSolido() {
		return new Sprite(rutaACarpeta +"/ladrillo.png");
	}
	
	public Sprite getPlataforma() {
		return new Sprite(rutaACarpeta +"/plataforma.png");
	}
	
	public Sprite getTuberia() {
		return new Sprite(rutaACarpeta +"/Tuberia.png");
	}
	
	public Sprite getVacio() {
		return new Sprite(rutaACarpeta +"/BloqueVacio.png");
	}
	public Sprite getBloqueTransparente() {
		return new Sprite (rutaACarpeta +"/BloqueTransparente.png");
	}
	
	public Sprite getChampiñonVerde() {
		return new Sprite(rutaACarpeta +"/champiñonVerde.png");
	}
	
	public Sprite getEstrella() {
		return new Sprite(rutaACarpeta +"/estrella.gif");
	}
	
	public Sprite getFlorDeFuego() {
		return new Sprite(rutaACarpeta +"/florDeFuego.gif");
	}
	
	public Sprite getMoneda() {
		return new Sprite(rutaACarpeta +"/moneda.gif");
	}
	
	public Sprite getSuperChampiñon() {
		return new Sprite(rutaACarpeta +"/superChampiñon.png");
	}
	
	public Sprite getBolaDeFuego() {
		return new Sprite(rutaACarpeta +"/bolaDeFuego.gif");
	}
	
	public Sprite getBolaDeFuegoHit() {
		return new Sprite(rutaACarpeta +"/bolaDeFuegoHit.gif");
	}
	
	public Sprite getBloqueSuelo() {
		return new Sprite(rutaACarpeta +"/bloqueSuelo.png");
	}
	public Sprite getBloqueFinal() {
		return new Sprite(rutaACarpeta +"/BloqueFinal.png");
	}

	public Sprite getProyectil() {
		return new Sprite(rutaACarpeta + "/Proyectil.png");
	}
	public Sprite getCaparazon() {
		return new Sprite(rutaACarpeta + "/Caparazon.png");
	}
	
	public Sprite getSpriteElimiacion() {
		return new Sprite(rutaACarpeta + "/SpriteEliminacion.png");
	}


}
