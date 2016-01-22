package net.erickpineda.papanoelrepartidor;

public class SanNicolasRepartidor {
    private Fichero file;
    private String nombreFichero = "/desitjos2.txt";

    public SanNicolasRepartidor() {
        file = new Fichero(nombreFichero);
    }

    public SanNicolasRepartidor alistarRutas() {
        file.abrirFichero();
        return this;
    }

    public void hohoho() {
        file.leerFichero();
        file.maquinasDe5Tipos();
    }

    public void trabajoCompletado() {
        file.cerrarFichero();
    }
}
