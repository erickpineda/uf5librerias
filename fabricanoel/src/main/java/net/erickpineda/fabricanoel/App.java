package net.erickpineda.fabricanoel;

/**
 * El pare Noel ha d'entregar els regals
 * 
 * @author Erick Pineda.
 */
public class App {
  private static final String FICHERO = "desitjos1.txt";

  public static void main(String[] args) {
    new App().iniciar();
  }

  private void iniciar() {
    Fichero f = new Fichero(FICHERO);
    f.abrirFichero();
    f.leerFichero();
    f.cerrarFichero();
  }
}
