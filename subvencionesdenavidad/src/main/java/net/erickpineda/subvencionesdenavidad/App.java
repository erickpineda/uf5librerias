package net.erickpineda.subvencionesdenavidad;

/**
 * Hello world!
 *
 */
public class App {
  private String nombreFichero = "/subvencions2.txt";

  public static void main(String[] args) {
    new App().inicio();
  }

  public void inicio() {
    Fichero file = new Fichero(nombreFichero);
    file.abrirFichero();
    file.leerFichero();
    file.cerrarFichero();
  }
}
