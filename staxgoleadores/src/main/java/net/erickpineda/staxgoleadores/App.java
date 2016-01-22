package net.erickpineda.staxgoleadores;

import java.io.InputStream;

import javax.xml.stream.FactoryConfigurationError;
import javax.xml.stream.XMLInputFactory;
import javax.xml.stream.XMLStreamException;
import javax.xml.stream.XMLStreamReader;

/**
 * @author Erick Pineda
 *
 */
public class App {
    private static final String NOMBRE_FICHERO = "/golejadors.xml";

    public static void main(String[] args) {
        new App().inicio();
    }

    private void inicio() {
        try {
            InputStream entrada = App.class.getResourceAsStream(NOMBRE_FICHERO);
            XMLStreamReader parser = XMLInputFactory.newInstance().createXMLStreamReader(entrada);
            new Procesar(parser).procesarXML();
        } catch (XMLStreamException | FactoryConfigurationError e) {
            e.printStackTrace();
        }

    }

}
