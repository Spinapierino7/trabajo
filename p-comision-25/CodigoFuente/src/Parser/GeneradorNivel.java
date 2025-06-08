package Parser;

import Juego.Nivel;
import Plataformas.BloqueDePregunta;
import Plataformas.LadrilloSolido;
import Plataformas.Plataforma;
import Fabricas.FabricaEntidades;

import java.util.LinkedList;
import java.util.List;

import Enemigos.Lakitu;

public class GeneradorNivel {
    
    protected FabricaEntidades fabricaEntidades;
    protected Parser parser;
    protected int limiteIzquierdoX = 0; 
    protected int alturaBloque = 32; 
    protected int alturaPared = 1024;
    protected int NivelSuelo= 64;
    protected List<String> rutas;

    public GeneradorNivel(FabricaEntidades fabricaEntidades) {
        this.fabricaEntidades = fabricaEntidades;
        rutas= new LinkedList<String>();
        rutas.add("Nivel1.txt");
        rutas.add("Nivel2.txt");
        rutas.add("Nivel3.txt");
        rutas.add("Nivel4.txt"); 
    }
    
    public Nivel generarNivel(int numero) {
        this.parser= new Parser(rutas.get(numero - 1)); //mapeo de lista
        // Generar nivel usando la silueta basada en el número
        Nivel nivel = new Nivel(fabricaEntidades.getSilueta());
        
        // Posición inicial del jugador en el nivel
        nivel.agregarJugador(fabricaEntidades.getJugador(128, 128));
        
        // Leer entidades desde el archivo
        List<Integer> posicionesXDeVacio = new LinkedList<Integer>(); 
        List<String[]> entidades = parser.leerArchivo(); //{["M", "5", "6" ],["En1", "9", "4"], ["M", "4", "4"] ["vacio", ]}
        int posicionesXFinal = 10*alturaBloque;
        // Iterar sobre las entidades y crear cada una en el nivel
        for (String[] datosEntidad : entidades) {
            String tipoEntidad = datosEntidad[0]; // M
            int posicionX = Integer.parseInt(datosEntidad[1]) * alturaBloque;   
            int posicionY = Integer.parseInt(datosEntidad[2]) * alturaBloque ;
                        
            
            posicionesXFinal = crearEntidad(tipoEntidad, posicionX, posicionY, nivel, posicionesXDeVacio);
        }

        // Crear límites del nivel
        crearLimitesNivel(nivel, posicionesXFinal, posicionesXDeVacio);

        return nivel;
    }

    private void crearLimitesNivel(Nivel nivel, int posicionBloqueFinalX, List<Integer> listaDeVacio) {

        // Crear la pared izquierda de bloques
        for (int y = 0; y < alturaPared; y += alturaBloque) { 
            nivel.agregarPlataforma(fabricaEntidades.getBloqueSuelo(limiteIzquierdoX - alturaBloque, y));
        }

        // Crear el suelo de bloques desde la pared izquierda hasta la pared derecha
        for (int x = limiteIzquierdoX; x <= posicionBloqueFinalX + 512; x += 32) {
            // Verificar si la posición actual x está en la lista de vacíos
            if (!listaDeVacio.contains(x)) { // Solo agregar si no está en la lista
                nivel.agregarPlataforma(fabricaEntidades.getBloqueSuelo(x, 128 - 32));
            }
        }

        // Segunda línea de bloques, similar a la anterior
        for (int x = limiteIzquierdoX; x <= posicionBloqueFinalX + 512; x += 32) {
            if (!listaDeVacio.contains(x)) {
                nivel.agregarPlataforma(fabricaEntidades.getBloqueSuelo(x, 128 - 64));
            }
        }

        // Crear la pared derecha de bloques en la posición del bloque final
        for (int y = 0; y < alturaPared; y += alturaBloque) { 
            nivel.agregarPlataforma(fabricaEntidades.getBloqueSolido(posicionBloqueFinalX + 352, y));
        }
    }
    
    
    private int crearEntidad(String tipoEntidad, int posicionX, int posicionY, Nivel nivel, List<Integer> ListaDeVacio) {
    	int PosicionXFinal = 10*alturaBloque;
        switch (tipoEntidad) {
        
        	//enemigos:
            case "En1": nivel.agregarEnemigo(fabricaEntidades.getGoomba(posicionX, posicionY ));
            break;
            case "En2": nivel.agregarEnemigo(fabricaEntidades.getTortuga(posicionX, posicionY));
            break;
            case "En4": { 
            	Lakitu lakitu = fabricaEntidades.getLakitu(posicionX, posicionY + 256);
            	nivel.agregarEnemigo(lakitu);
            	lakitu.setNivel(nivel);
            	break;
            }
            case "En5": nivel.agregarEnemigo(fabricaEntidades.getSpiny(posicionX, posicionY));
            break;
            case "En6": nivel.agregarEnemigo(fabricaEntidades.getBuzzyBeetle(posicionX, posicionY));
            break;
            //plataformas
            case "M" :  nivel.agregarPowerUp(fabricaEntidades.getMoneda(posicionX, posicionY));;
            break;
            //plataformas
            case "Pl1" :  nivel.agregarPlataforma(fabricaEntidades.getBloqueSolido(posicionX, posicionY));
            break;
            case "Pl2" :  {
            	LadrilloSolido ladrillo = fabricaEntidades.getLadrilloSolido(posicionX, posicionY);
            	nivel.agregarPlataforma(ladrillo);
            	ladrillo.setNivel(nivel);
            break;
            }
            case "Pl3" : {               
            	for (int i =128; i <= posicionY; i += 32) { 
            	    nivel.agregarPlataforma(fabricaEntidades.getTuberia(posicionX, i));
            	    nivel.agregarPlataforma(fabricaEntidades.getTuberia(posicionX+32, i));
            	    nivel.agregarPlataforma(fabricaEntidades.getTuberia(posicionX+64, i));
            	}
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX + 96, posicionY +32));
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX -32, posicionY+32));
                break; 
            }

            case "Pl3T" : {
            	
            	for (int i =128; i <= posicionY; i += 32) { 
            	    nivel.agregarPlataforma(fabricaEntidades.getTuberia(posicionX, i));
            	}
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX + 32, posicionY +32));
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX -32, posicionY+32));
            	 nivel.agregarEnemigo(fabricaEntidades.getPiranhaPlant(posicionX +64, posicionY + 32));
                break; // Asegúrate de incluir un break para salir del case
            }
            
            
            
            case "Pl4" : { 
            	Plataforma bloque  = nivel.agregarPlataforma(fabricaEntidades.getBloqueDePregunta(posicionX, posicionY));			
            	bloque.setearNivel(nivel);
            	break;				
            }
          
            
            case "Pl5" :  {
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX, posicionY));
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX, posicionY - 32));
            	nivel.agregarPlataforma(fabricaEntidades.getVacio(posicionX, posicionY - 64));
            	
            	ListaDeVacio.add(posicionX); //se lo agrega a la lista que tiene que omitirse cuando se genere el suelo
            };
            break;
            
            //bloques transparentes 
            case "LD" :  nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX, posicionY));
            break;
            case "LI" :  nivel.agregarPlataforma(fabricaEntidades.getBloqueTransparente(posicionX, posicionY));
            break;
            
             //bloque final	   
            case "PlF": {
            	nivel.agregarPlataforma(fabricaEntidades.getBloqueFinal(posicionX, 64));
            	PosicionXFinal = posicionX;
            }
            	break;
        }
        return PosicionXFinal;
    }
    
}