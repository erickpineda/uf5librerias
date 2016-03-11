package net.erickpineda.papanoelascii;

/**
 * @author Erick Pineda
 *
 */
public class App {
  private static final String FICHERO = "ascii.txt";

  public static void main(String[] args) {
    new App().iniciar();
  }

  public void iniciar() {
    Fichero f = new Fichero(FICHERO);
    f.abrirFichero();
    f.leerFichero();
    f.cerrarFichero();
  }
}
