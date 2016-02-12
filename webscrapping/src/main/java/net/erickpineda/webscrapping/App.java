package net.erickpineda.webscrapping;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * @author Erick Pineda.
 *
 */
public class App {
    private static final String URL = "https://ca.wikipedia.org/wiki/Llista_de_colors";
    private List<Color> colores = new ArrayList<Color>();
    private Document doc;

    public static void main(String[] args) {
        new App().iniciar();
    }
    /**
     * Inicia el proceso de webscrapping.
     */
    private void iniciar() {
        conectarURL();
        buscarTrEnTablas(doc.select("table.prettytable tr"));
        Fichero.escribirFichero(colores);
        Fichero.leerFichero();
    }
    /**
     * Conecta con la URL especificada.
     */
    private void conectarURL() {
        try {
            doc = Jsoup.connect(URL).get();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * Búsca en la tabla las etiquetas tr y td para guarda los datos en una Lista.
     * @param elements parámetro que contiene las tablas a partir de su tr.
     */
    private void buscarTrEnTablas(Elements elements) {
        for (Element el : elements) {
            Element tr = el.nextElementSibling();
            if (tr != null) {
                Color color = new Color();
                color.setNom(tr.select("th").text());
                color.setColorhex(tr.select("td").get(1).text());
                color.setRed(Integer.parseInt(tr.select("td").get(2).text()));
                color.setGreen(Integer.parseInt(tr.select("td").get(3).text()));
                color.setBlue(Integer.parseInt(tr.select("td").get(4).text()));
                colores.add(color);
            }
        }
    }
}
