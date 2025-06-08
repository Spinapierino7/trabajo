package Juego;

import java.awt.Rectangle;

public class Hitbox extends Rectangle{

	private static final long serialVersionUID = 1L;
	
	protected double x; 
    protected double y; 
    protected double ancho; 
    protected double alto; 

    public Hitbox(int x, int y, int ancho, int alto) {
    	super(x, y, ancho, alto);
        this.x = x;
        this.y = y;
        this.ancho = ancho;
        this.alto = alto;
    }

    public double getX() {
        return x;
    }

    public double getY() {
        return y;
    }

    public double getAncho() {
        return ancho;
    }

    public double getAlto() {
        return alto;
    }
    public void setX(int x) {
        this.x = x;
        this.setBounds((int) x, (int) y, (int) ancho, (int) alto);
    }

    public void setY(int y) {
        this.y = y;
        this.setBounds((int) x, (int) y, (int) ancho, (int) alto);
    }
}
