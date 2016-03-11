package net.erickpineda.papanoelascii;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;

public class Fichero {
  private BufferedReader br = null;
  private String nombreFichero;
  private String msj = "***NO SE HA PODIDO LEER EL FICHERO***";
  private String lineaFichero = "";
  private static final Logger logger = Logger.getLogger(Fichero.class.getName());
  private static final String NOEL = "\\*<]:-DOo";
  private static final String RENO = ">:o\\)";
  private static final String AYUDANTE = "<]:-D";

  public Fichero(final String nom) {
    this.nombreFichero = "/" + nom;
  }

  /**
   * Abrir el fichero de lectura.
   */
  public void abrirFichero() {
    InputStream entrada = Fichero.class.getResourceAsStream(nombreFichero);
    logger.info("Abriendo fichero " + nombreFichero);

    if (entrada != null) {
      Reader leer = new InputStreamReader(entrada);
      br = new BufferedReader(leer);

    } else {
      logger.error(msj);
    }
  }

  /**
   * Leer el fichero que contiene las instrucciones.
   */
  public void leerFichero() {
    try {
      lineaFichero = br.readLine();

      while (lineaFichero != null) {
        contarPersonajes(NOEL, lineaFichero);
        contarPersonajes(RENO, lineaFichero);
        contarPersonajes(AYUDANTE, lineaFichero);
        System.out.println();
        lineaFichero = br.readLine();
      }
    } catch (IOException e) {
      logger.error("Error de lectura del fichero", e);
    }
  }
  
  private void contarPersonajes(final String regex, final String linea) {
    Pattern patron = Pattern.compile(regex);
    Matcher matcher = patron.matcher(linea);
    int cant = 0;

    while (matcher.find()) {
      cant++;
    }

    if (cant > 0 && !linea.isEmpty()) {
      System.out.printf("%s%s%d%s", cualPersonaje(regex), " (", cant, ") ");
    }
  }

  private String cualPersonaje(final String regexp) {
    if (regexp.equals(NOEL)) {
      return "Pare noel";
    }
    if (regexp.equals(RENO)) {
      return "Ren";
    }
    if (regexp.equals(AYUDANTE)) {
      return "Follet";
    }
    return null;
  }

  /**
   * Cierra el fichero.
   */
  public void cerrarFichero() {
    try {
      br.close();
      logger.info("Fichero" + nombreFichero + " cerrado correctamente");
    } catch (IOException e) {
      logger.error("No se pudo cerrar el fichero", e);
    }
  }
}
