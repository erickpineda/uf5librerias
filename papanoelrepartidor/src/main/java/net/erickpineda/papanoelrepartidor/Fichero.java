package net.erickpineda.papanoelrepartidor;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.apache.log4j.Logger;

public class Fichero {
    private BufferedReader br = null;
    private String nombreFichero;
    private String msj = "***NO SE HA PODIDO LEER EL FICHERO***";
    private String lineaFichero = "";
    private static final Logger logger = Logger.getLogger(Fichero.class);
    private Set<Regalo> regalos;
    private List<Persona> crios;

    public Fichero(final String nom) {
        this.nombreFichero = nom;
        this.regalos = new HashSet<Regalo>();
        this.crios = new ArrayList<Persona>();
    }
    /**
     * 
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

    public void leerFichero() {
        try {
            lineaFichero = br.readLine();

            while (lineaFichero != null) {
                separarNombresYContarRegalos();
                lineaFichero = br.readLine();
            }

        } catch (IOException e) {
            logger.error("Error de lectura del fichero", e);
        }
    }

    private void sumarRegalo(String regalo) {
        for (Regalo r : regalos) {
            if (r.getNombre().equals(regalo)) {
                r.sumarRegalo();
            }
        }
    }

    private void comprobarRegaloRepetido(String[] array, int pos) {
        if (!regalos.contains(new Regalo(array[pos]))) {
            regalos.add(new Regalo(array[pos], 1));
        } else {
            sumarRegalo(array[pos]);
        }
    }

    private void separarNombresYContarRegalos() throws IOException {
        if (lineaFichero.contains(":")) {
            String[] array = lineaFichero.split(": ");
            addPersona(contarRegalos(array));
        }
    }

    private void addPersona(Persona p) {
        if (p != null) {
            crios.add(p);
        }
    }

    private Persona contarRegalos(String[] array) throws IOException {

        if (array[1].contains(",")) {
            String[] regalosArray = array[1].split(",");
            for (int i = 0; i < regalosArray.length; i++) {
                comprobarRegaloRepetido(regalosArray, i);
            }
        } else {
            comprobarRegaloRepetido(array, 1);
        }

        return new Persona(array[0], regalos);
    }

    private List<Regalo> ordenarRegalos() {
        List<Regalo> list = new ArrayList<Regalo>(regalos);
        Collections.sort(list, new Comparator<Regalo>() {
            @Override
            public int compare(Regalo o1, Regalo o2) {
                return o1.getCantidad() - o2.getCantidad();
            }
        });
        return list;
    }

    public void maquinasDe5Tipos() {
        int numeracion = 1, cont = 0;
        String linea = numeracion + "- ";
        List<Regalo> list = ordenarRegalos();

        for (int i = 0; i < list.size(); i++) {
            linea += list.get(i).getNombre() + ", ";

            if ((cont + 1) % 5 == 0) {
                System.out.printf("%s\n", linea.substring(0, linea.length()-2));
                numeracion++;
                linea = numeracion + "- ";
            }
            cont++;
        }
    }

    public void pintarCrios() {
        crios.forEach(v -> System.out.println(v));
    }

    public void pintarRegalos() {
        regalos.forEach(v -> System.out.println(v));
    }

    public void pintarRegalosOrdenados() {
        maquinasDe5Tipos();
    }

    private void info() {
        System.out.println(
                "\nSan Nicolas ha repartido " + regalos.size()
                + " tipos de regalos a "
                + crios.size() + " crios.\n");
    }

    public void cerrarFichero() {
        try {
            info();
            br.close();
            logger.info("Fichero" + nombreFichero + " cerrado correctamente");
        } catch (IOException e) {
            logger.error("No se pudo cerrar el fichero", e);
        }
    }
}
