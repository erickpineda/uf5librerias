package net.erickpineda.thecolorsleague;

import java.io.IOException;
import java.io.InputStream;

import javax.xml.parsers.ParserConfigurationException;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;

import org.xml.sax.SAXException;

/**
 * @author Erick Pineda
 *
 */
public class App {
  private static final String[] FILES = { "/lligaR-1.xml", "/lligaR-2.xml", "/lligaR-3.xml" };

  public static void main(String[] args)
      throws ParserConfigurationException, SAXException, IOException {

    SAXParserFactory spf = SAXParserFactory.newInstance();
    SAXParser parser = spf.newSAXParser();

    for (String fichero : FILES) {
      InputStream entrada = App.class.getResourceAsStream(fichero);
      parser.parse(entrada, new Procesar());
    }
  }
}