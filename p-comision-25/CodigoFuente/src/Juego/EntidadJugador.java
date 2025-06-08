package Juego;

import Fabricas.Sprite;
import PowerUps.ChampiñonVerde;
import PowerUps.Estrella;
import PowerUps.FlorDeFuego;
import PowerUps.Moneda;
import PowerUps.PowerUp;
import PowerUps.SuperChampiñon;
import Visitor.EntidadVisitor;

public interface EntidadJugador extends EntidadLogica , EntidadVisitor {
	public void saltar();
	
	public void mover();
	
	public void lanzarBolasDeFuego();
	
	public void setDireccion(int n);
	
	public int getDireccion();
	
	public boolean cayendo();
	
	public void setNivel(Nivel nivel);
	
	public boolean getSubiendo();
	
	public void setPosicionSuelo(int nuevaPosicion);
    
	public boolean puedeRomperPlataformas();
    
    public void setSobrePlataforma(boolean sobrePlataforma);
    
    public void setSesionDeJuego(SesionDeJuego sesion);
    
    public void consumirPowerUp(SuperChampiñon pw) ;
    
    public void consumirPowerUp(Moneda pw) ;

    public void consumirPowerUp(FlorDeFuego pw) ;

    public void consumirPowerUp(ChampiñonVerde pw) ;

    public void consumirPowerUp(Estrella pw) ;

	public boolean getSobrePlataforma();

}
