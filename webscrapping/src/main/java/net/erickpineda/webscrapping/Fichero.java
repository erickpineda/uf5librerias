package net.erickpineda.webscrapping;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.PrintWriter;
import java.util.List;

public class Fichero {
    /**
     * Nombre del fichero a crear para después leer su contenido.
     */
    private static final String NOMBRE_FICHERO = "inserts.sql";

    public Fichero() {

    }
    /**
     * Método para leer el fichero de los insert's.
     */
    public static void leerFichero() {
        File archivo = null;
        FileReader fr = null;
        BufferedReader br = null;

        try {
            archivo = new File(NOMBRE_FICHERO);
            fr = new FileReader(archivo);
            br = new BufferedReader(fr);

            String linea;
            while ((linea = br.readLine()) != null) {
                System.out.println(linea);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fr) {
                    fr.close();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    /**
     * Método para escribir en el fichero a través de una lista de colores que recibe por parámetro.
     * @param colores lista de colores que se recogerá desde el webscrapping.
     */
    public static void escribirFichero(List<Color> colores) {
        FileWriter fichero = null;
        PrintWriter pw = null;
        try {
            fichero = new FileWriter(NOMBRE_FICHERO);
            pw = new PrintWriter(fichero);

            String insert = "";
            for (Color color : colores) {
                insert = "INSERT INTO colors (nom,colorhex,red,green,blue) VALUES ('"
                        + color.getNom() + "','" + color.getColorhex() + "'," + color.getRed() + ","
                        + color.getGreen() + "," + color.getBlue() + ");";
                pw.println(insert);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (null != fichero)
                    fichero.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}