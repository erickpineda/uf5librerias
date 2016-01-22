package net.erickpineda.papanoelconlapatarota;

public class SustitutoDeSanta {
    private String nombreFichero = "/instrucciones.txt";
    private Fichero file;

    public SustitutoDeSanta() {
        file = new Fichero(nombreFichero);
    }

    /**
     * 
     * @return retorna un sustituto de santa creado.
     */
    public SustitutoDeSanta alistarRutas() {
        file.abrirFichero();
        file.leerFichero();
        return this;
    }

    /**
     * Visitando casas.
     */
    public void hohoho() {
        file.comprobarRutas();
        file.pintarCasas();
    }

    /**
     * Sustituto de santa ha completado su trabajo.
     */
    public void trabajoCompletado() {
        file.cerrarFichero();
    }
}
