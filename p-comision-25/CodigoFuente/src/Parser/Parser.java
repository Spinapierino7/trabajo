package Parser;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Parser {

    protected File archivo;

    public Parser(String rutaArchivo) {
        this.archivo = new File(rutaArchivo);
    }

    public List<String[]> leerArchivo() {
        List<String[]> entidades = new ArrayList<>(); //[En,5] o [LI, Pl1, Pl1, LD, 4 , 5]

        try (BufferedReader lectura = new BufferedReader(new FileReader(archivo.getAbsolutePath()))) {
            String lineaDeDatos = lectura.readLine();

            while (lineaDeDatos != null) {
                // Eliminar el comentario de la línea
                int comentarioIndex = lineaDeDatos.indexOf('#');
                if (comentarioIndex != -1) {
                    lineaDeDatos = lineaDeDatos.substring(0, comentarioIndex);
                }

                // Dividir la línea por espacios
                String[] partes = lineaDeDatos.trim().split("\\s+"); 

                // Manejar los casos de bloque de pregunta
                if (partes.length == 3) {
                    entidades.add(new String[]{partes[0], partes[1], partes[2]});
                }

                // Manejar el caso de bloque de vacio, final y enemigos simples 
                if (partes.length == 2) {
                    entidades.add(new String[]{partes[0], partes[1], "4"});
                }
                
                if (partes[0].equals("LI")) {
                    String[] plataformaCompuesta = partes.clone();
                    int posicionXInicial = 0;
                    int posicionYInicial = 0;

                    // Búsqueda de las posiciones iniciales cuando se encuentra "LD"
                    for (int i = 1; i < partes.length; i++) {
                        if (plataformaCompuesta[i].equals("LD")) {
                            // Extraer las posiciones X e Y después de "LD"
                            posicionXInicial = Integer.parseInt(plataformaCompuesta[i + 1]); // Convertimos a int
                            posicionYInicial = Integer.parseInt(plataformaCompuesta[i + 2]);
                            break;
                        }
                    }
                    entidades.add(new String[]{partes[0], String.valueOf(posicionXInicial - 1), String.valueOf(posicionYInicial+1)});
                    // Iteramos sobre las partes de la línea (desde el 1 hasta antes de "LD")
                    int posicionXActual = posicionXInicial;
                    for (int i = 1; i < partes.length; i++) {
                        if (partes[i].equals("LD")) {
                        	 entidades.add(new String[]{partes[i], String.valueOf(posicionXActual), String.valueOf(posicionYInicial+1)});
                            break;
                        }

                        // Para enemigos, ajustamos la posición en Y
                        if (partes[i].startsWith("En")) {
                            entidades.add(new String[]{partes[i], String.valueOf(posicionXActual), String.valueOf(posicionYInicial + 1)});
                            entidades.add(new String[]{"Pl2", String.valueOf(posicionXActual), String.valueOf(posicionYInicial)});
                        } else if (partes[i].startsWith("Pl")) {
                            // Para plataformas, usamos la posición Y original
                            entidades.add(new String[]{partes[i], String.valueOf(posicionXActual), String.valueOf(posicionYInicial)});
                        }

                        // Avanzar la posición X después de cada entidad (por ejemplo, 32 píxeles por entidad)
                        posicionXActual += 1;
                    }
                   
                   
                }
                   

                lineaDeDatos = lectura.readLine();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return entidades;
    }

}
