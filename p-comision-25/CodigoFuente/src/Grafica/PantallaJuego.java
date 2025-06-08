package Grafica;

import javax.swing.Timer; // Importar Timer
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Image;

import javax.swing.BoxLayout;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;

import Juego.EntidadJugador;
import Juego.EntidadLogica;
import Juego.SesionDeJuego;

public class PantallaJuego extends JPanel{

	private static final long serialVersionUID = 1L;
	protected JPanel panelJuego;
	protected JLabel imagenJuego;
	protected JScrollPane panelScrollJuego;
	private JPanel panelInfo;  // Panel para el puntaje, vidas y tiempo
    private JLabel puntaje;
    private JLabel vidas;
    private JLabel timer;
    private JLabel nivel;

	
	public PantallaJuego(SesionDeJuego sesionDeJuego) {
		setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_ALTO));
		setLayout(new BorderLayout());
		setFocusable(true);
		agregarPanelInfo(sesionDeJuego); 
		agregarPanelJuegoConFondoYScroll();
	}


	protected void agregarPanelInfo(SesionDeJuego sesionDeJuego) {
	    // Crear el panel para la información del juego
	    panelInfo = new JPanel();
	    panelInfo.setLayout(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Cambiar a FlowLayout
	    panelInfo.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, 40)); // Ajustar el alto
	    panelInfo.setBackground(Color.BLACK); // Establecer el fondo a negro

	    // Inicializa los JLabel usando los datos de la sesión
	    puntaje = new JLabel("Puntaje: " + sesionDeJuego.getPuntaje());
	    puntaje.setFont(new Font("Arial", Font.BOLD, 20)); // Establecer la fuente
	    puntaje.setForeground(Color.WHITE);
	    panelInfo.add(puntaje);

	    vidas = new JLabel("Vidas: " + sesionDeJuego.getVidasRestantes());
	    vidas.setFont(new Font("Arial", Font.BOLD, 20)); // Establecer la fuente
	    vidas.setForeground(Color.WHITE);
	    panelInfo.add(vidas);

	    timer = new JLabel("Tiempo: " + sesionDeJuego.getTiempoRestante());
	    timer.setFont(new Font("Arial", Font.BOLD, 20)); // Establecer la fuente
	    timer.setForeground(Color.WHITE);
	    panelInfo.add(timer);

	    // Agregar el panel a la ventana en la parte superior
	    add(panelInfo, BorderLayout.NORTH);

	    // Crear un Timer para actualizar los datos cada segundo
	    Timer updateTimer = new Timer(100, new ActionListener() {
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Actualizar el JLabel de tiempo
	            timer.setText("Tiempo: " + sesionDeJuego.getTiempoRestante());

	            // Actualizar otros valores si es necesario
	            puntaje.setText("Puntaje: " + sesionDeJuego.getPuntaje());
	            vidas.setText("Vidas: " + sesionDeJuego.getVidasRestantes());
	            
	            // Si el tiempo se agota, puedes detener el timer
	            if (sesionDeJuego.getTiempoRestante() <= 0) {
	                ((Timer)e.getSource()).stop(); // Detener el timer
	                // Puedes agregar lógica adicional aquí, como finalizar el juego
	            }
	        }
	    });

	    updateTimer.start(); // Iniciar el timer
	}

	
	protected void agregarPanelJuegoConFondoYScroll() {
		
		imagenJuego = new JLabel();
		imagenJuego.setLayout(null);
		imagenJuego.setBounds(0,0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_JUEGO_ALTO);

		
		panelJuego = new JPanel(null);
		panelJuego.setPreferredSize(new Dimension(ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_JUEGO_ALTO));
		panelJuego.add(imagenJuego);
		
		panelScrollJuego = new JScrollPane(panelJuego);
		panelScrollJuego.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
		panelScrollJuego.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
		
		panelScrollJuego = new JScrollPane(panelJuego);
	    panelScrollJuego.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
	    panelScrollJuego.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_NEVER);
	       
		panelScrollJuego.setBounds(0, 0, ConstantesVistas.PANEL_ANCHO, ConstantesVistas.PANEL_JUEGO_ALTO);
			
		add(panelScrollJuego, BorderLayout.CENTER);
	}
	
	public void actualizarScrollHaciaJugador(EntidadJugador entidadJugador) {
		
		JScrollBar horizontalScrollBar = panelScrollJuego.getHorizontalScrollBar();
		
		int jugadorX = entidadJugador.getX();
        int ventanaAncho = panelScrollJuego.getViewport().getWidth();

        int posicionScroll = jugadorX - (ventanaAncho / 2);
        horizontalScrollBar.setValue(Math.max(0, posicionScroll));

	}
	
	public Observer incorporarEntidad(EntidadLogica entidadLogica) {
		ObserverEntidades observerEntidad = new ObserverEntidades(entidadLogica);
		imagenJuego.add(observerEntidad);	
		return observerEntidad;
	}
	
	public Observer incorporarEntidadJugador(EntidadJugador entidadJugador) {
		ObserverJugador observerJugador = new ObserverJugador(this, entidadJugador);
		imagenJuego.add(observerJugador);
		return observerJugador;
	}
	
	public Observer incorporarSilueta(EntidadLogica entidadLogica) {
		double ratio = (double) 3584 / 240;
		int nuevaAltura = 550;
		int nuevoAncho = (int) (nuevaAltura * ratio);
		
		ObserverEntidades observerEntidad = new ObserverEntidades(entidadLogica);
		
		ImageIcon iconoImagen = new ImageIcon(getClass().getClassLoader().getResource(entidadLogica.getSprite().getRutaImagen()));
		Image imagenEscalada = iconoImagen.getImage().getScaledInstance(nuevoAncho, nuevaAltura, Image.SCALE_SMOOTH);
		Icon iconoImagenEscalado = new ImageIcon(imagenEscalada);
		imagenJuego.setIcon(iconoImagenEscalado);
		
		imagenJuego.setBounds(0,0,imagenJuego.getIcon().getIconWidth(),imagenJuego.getIcon().getIconHeight());
		panelJuego.setPreferredSize(new Dimension(imagenJuego.getIcon().getIconWidth(),imagenJuego.getIcon().getIconHeight()));
		
		return observerEntidad;
	
	}
	
	public void actualizarPuntaje(int puntos) {
        puntaje.setText("Puntaje: " + puntos);
    }

    public void actualizarVidas(int vida) {
        vidas.setText("Vidas: " + vida);
    }

    public void actualizarTimer(int time) {
        timer.setText("Tiempo: " + time);
    }
    public void actualizarNivel(int nivel) {
    	this.nivel.setText("Nivel: "+ nivel);
    }
}
