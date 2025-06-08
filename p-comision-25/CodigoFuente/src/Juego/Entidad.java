package Juego;


import Fabricas.FabricaSprites;
import Fabricas.FabricaSpritesOriginales;
import Fabricas.Sprite;
import Grafica.Observer;
import Visitor.*;


public abstract class Entidad implements EntidadVisitor, EntidadLogica{
	protected int x;
	protected int y;
	protected int direccionDeEntidad;
	protected Sprite sprite;
	protected Observer observer;
	protected Hitbox hitbox;
	protected FabricaSprites fabrica = new FabricaSpritesOriginales() ;
	
	protected Entidad(Sprite sprite, int x, int y) {
		this.x=x;
		this.y=y;
		this.sprite=sprite;
		
		this.direccionDeEntidad = 0;
		
		this.hitbox = new Hitbox (x, y, sprite.getAncho(), sprite.getAlto());
	}
	protected Entidad(Sprite sprite, int x, int y, Observer obv) {
		this.x=x;
		this.y=y;
		this.sprite=sprite;
		
		this.direccionDeEntidad = 0;
		
		this.hitbox = new Hitbox (x, y, sprite.getAncho(), sprite.getAlto());
		
		this.observer=obv;
	}
	
	
	public void desaparecer() {
		sprite = fabrica.getBloqueTransparente();
	}
	
	public Sprite getSprite() {
		return sprite;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public void setX(int x) {
		this.x=x;
		this.hitbox.setX(x);
	}
	
	public void setY(int y) {
		this.y=y;
		this.hitbox.setY(y);
	}
	
	public void registrarObserver(Observer observer) {
		this.observer=observer;
	}
	
	
	public Hitbox getHitbox() {
        return hitbox;
    }

    public void setHitBox(Hitbox hitBox) {
        this.hitbox = hitBox;
    }

    //patron Visitor redefinido en clases especificas
    @Override
	public void accept(Visitor v) {
	    v.visitar(this);
	}
    
    
    
    
	
}
