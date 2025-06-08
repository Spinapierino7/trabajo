package Grafica;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import Juego.SesionDeJuego;

public class PantallaGameOver extends JPanel {
    
    private static final long serialVersionUID = 1L;
    private static final String RUTA_FONDO_PANTALLA_GAMEOVER = "/Imagenes/imagen_gameover.png";
    private JLabel imagenFondo;
    private JTextField nombreJugador;
    private JButton guardarNombreBoton;
    private SesionDeJuego sesionDeJuego;
   

    public PantallaGameOver(SesionDeJuego sesionDeJuego, ControladorVistas controladorVistas) {
        this.sesionDeJuego = sesionDeJuego;
        setSize(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        setLayout(null);
       
        // Configuración de imagen de fondo
        imagenFondo = new JLabel();
        ImageIcon iconoImagen = new ImageIcon(this.getClass().getResource(RUTA_FONDO_PANTALLA_GAMEOVER));
        Image imagenEscalada = iconoImagen.getImage().getScaledInstance(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO, Image.SCALE_SMOOTH);
        Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
        imagenFondo.setIcon(iconoImagenEscalado);
        imagenFondo.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO);
        add(imagenFondo);

        // Campo de texto para ingresar el nombre del jugador
        nombreJugador = new JTextField("Ingresa tu nombre");
        nombreJugador.setBounds(250, 390, 220, 30); // Ajusta el tamaño para ser más alto y ocupa menos espacio vertical
        add(nombreJugador);

        // Botón para guardar el nombre
        guardarNombreBoton = new JButton("Guardar");
        guardarNombreBoton.setBounds(300, 450, 220, 170);
        guardarNombreBoton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarPuntaje();
                controladorVistas.nuevaPantallaInicial();
                controladorVistas.mostrarPantallaInicial();
            }
        });
        guardarNombreBoton.setOpaque(false);
        guardarNombreBoton.setContentAreaFilled(false);
        guardarNombreBoton.setBorderPainted(false);
        add(guardarNombreBoton);
        
    }

    private void guardarPuntaje() {
        String nombre = nombreJugador.getText();
        sesionDeJuego.guardarPuntaje(nombre);
        sesionDeJuego.reiniciar();
        JOptionPane.showMessageDialog(this, "Nombre guardado correctamente!");
    }
}
