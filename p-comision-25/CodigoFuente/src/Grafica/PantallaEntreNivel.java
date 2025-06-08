package Grafica;

import javax.swing.*;
import java.awt.*;

public class PantallaEntreNivel extends JPanel {

    private static final long serialVersionUID = 1L;
    private JLabel imagenFondo;
    private static final String RUTA_FONDO_NIVEL_1 = "/Imagenes/imagen_gameover.png";
    private static final String RUTA_FONDO_NIVEL_2 = "/Imagenes/imagen_entre_nivel2.png";
    private static final String RUTA_FONDO_NIVEL_3 = "/Imagenes/imagen_entre_nivel3.png";
    private static final String RUTA_FONDO_NIVEL_4 = "/Imagenes/imagen_entre_nivel4.png";
    private static final String RUTA_FONDO_DEFAULT = "/Imagenes/imagen_gameover.png";

    public PantallaEntreNivel(int nivel) {

    	
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
        String rutaImagen = RUTA_FONDO_DEFAULT;
        
        switch (nivel) {
            case 1:
                rutaImagen = RUTA_FONDO_NIVEL_1;
                break;
            case 2:
                rutaImagen = RUTA_FONDO_NIVEL_2;
                break;
            case 3:
                rutaImagen = RUTA_FONDO_NIVEL_3;
                break;
            case 4:
            	rutaImagen = RUTA_FONDO_NIVEL_4;
            	break;
            default:
                rutaImagen = RUTA_FONDO_DEFAULT;
                break;
        }

        configurarImagenFondo(rutaImagen);
        
        add(imagenFondo);
    }
    
    private void configurarImagenFondo(String ruta) {
    	imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(ruta));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
    }
}
