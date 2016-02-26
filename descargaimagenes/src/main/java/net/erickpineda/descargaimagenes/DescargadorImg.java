package net.erickpineda.descargaimagenes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;

import javax.swing.DefaultListModel;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;

import net.miginfocom.swing.MigLayout;
import java.awt.SystemColor;

public class DescargadorImg extends JPanel implements ChangeListener {

  private static final long serialVersionUID = 1L;
  /**
   * Recoge la URL para proceder la descarga.
   */
  private JTextField url;
  /**
   * Opción para descargar una única imágen.
   */
  private JRadioButton imagenUnica;
  /**
   * Opción para descargar varias imágenes de una URL.
   */
  private JRadioButton variasImagenes;
  /**
   * Container donde ira el JList con los nombres de imágenes.
   */
  private JScrollPane scrollPane;
  /**
   * Para almacenar los nombres de las imágenes.
   */
  private JList<String> list;
  /**
   * Modelo que se le pasará a la lista de nombres de imágenes.
   */
  private DefaultListModel<String> model;

  /**
   * Create the panel.
   */
  public DescargadorImg() {
    setBackground(SystemColor.menu);
    model = new DefaultListModel<String>();
    setLayout(new MigLayout("", "[][][grow][][]", "[][][][][][grow][]"));

    JLabel lblUrl = new JLabel("URL");
    add(lblUrl, "cell 1 1,alignx trailing");

    url = new JTextField();
    add(url, "cell 2 1 2 1,growx");
    url.setColumns(10);

    imagenUnica = new JRadioButton("Imágen única");
    imagenUnica.addChangeListener(this);
    add(imagenUnica, "flowx,cell 3 2,alignx trailing");

    variasImagenes = new JRadioButton("Varias imágenes");
    variasImagenes.addChangeListener(this);
    add(variasImagenes, "cell 3 2,alignx trailing");

    JButton descargar = new JButton("Descargar");
    descargar.setBackground(SystemColor.controlHighlight);
    addAction(descargar);
    add(descargar, "cell 3 2,alignx trailing");

    JLabel labelImg = new JLabel("Imágenes descargadas");
    add(labelImg, "cell 1 4");

    scrollPane = new JScrollPane();
    add(scrollPane, "cell 1 5 3 1,grow");
    leerImagenesEnDirectorio();

    list = new JList<String>(model);
    scrollPane.setViewportView(list);

  }

  /**
   * Agrega acciones al botón.
   * 
   * @param btn botón a agregar la acción cuando se hace clic sobre el.
   */
  private void addAction(JButton btn) {
    btn.addActionListener(new ActionListener() {

      @Override
      public void actionPerformed(ActionEvent arg0) {
        botonDescargar();
      }

    });
  }

  /**
   * Funciones que ejecuta el {@code JButton descargar}.
   */
  private void botonDescargar() {
    if (!imagenUnica.isSelected() && !variasImagenes.isSelected()) {
      mensajeAdvertencia("Selecciona una de las dos opciones de descarga");
    } else {
      if (!url.getText().isEmpty()) {
        comprobarUrlYOpcionDeDescarga(new ConectorURL());
      } else {
        mensajeAdvertencia("El campo URL no puede estar vacío");
      }
    }
  }

  /**
   * Método que comprueba si la URL introducida es correcta y según la opción de descarga se
   * procederá a descargar la imágen o imágenes.
   * 
   * @param cu conector de URL para descargar la imágen o imágenes.
   */
  private void comprobarUrlYOpcionDeDescarga(final ConectorURL cu) {
    if (imagenUnica.isSelected()) {
      if (Util.validarURLImagen(url.getText())) {
        cu.setURL_IMG(url.getText());
        cu.descargarImagen();
        validarDescarga(cu);
      } else {
        mensajeAdvertencia("La URL con imágen única no es válida");
      }
    }
    if (variasImagenes.isSelected()) {
      if (Util.validarURL(url.getText())) {
        cu.setURL_IMG(url.getText());
        cu.descargarImagenes();
        validarDescarga(cu);
      } else {
        mensajeAdvertencia("La URL no es válida");
      }
    }
  }

  /**
   * Comprueba si la imágen o las imágenes se han podido descargar correctamente.
   * 
   * @param cu parámetro conector.
   */
  private void validarDescarga(final ConectorURL cu) {
    if (!cu.isImgOK()) {
      mensajeError("Por algún motivo la descarga de\nimágen no se ha podido efectuar");
    } else {
      actualizarModelo();
      mensajeInfo("Descarga correcta");
    }
  }

  @Override
  public void stateChanged(ChangeEvent arg0) {
    if (imagenUnica.isSelected()) {
      variasImagenes.setSelected(false);
    }
    if (variasImagenes.isSelected()) {
      imagenUnica.setSelected(false);
    }
  }

  /**
   * Actualiza con nuevos datos la lista de imágenes.
   */
  private void actualizarModelo() {
    model.clear();
    leerImagenesEnDirectorio();
    list.setModel(model);
    url.setText("");
  }

  /**
   * Método que comprueba un directorio y busca los ficheros que sean de tipo imagen
   */
  private void leerImagenesEnDirectorio() {
    try {
      Files.walk(Paths.get("img")).forEach(filePath -> {
        if (Files.isRegularFile(filePath)) {
          model.addElement(filePath.toFile().getName());
        }
      });
    } catch (IOException e) {
      e.printStackTrace();
    }
  }

  /**
   * Mostrar mensajes de error.
   * 
   * @param msj mensaje a mostrar.
   */
  private void mensajeError(String msj) {
    JOptionPane.showMessageDialog(null, msj, "Error", JOptionPane.ERROR_MESSAGE);
  }

  /**
   * Mostrar mensajes de advertencia.
   * 
   * @param msj mensaje a mostrar.
   */
  private void mensajeAdvertencia(String msj) {
    JOptionPane.showMessageDialog(null, msj, "Advertencia", JOptionPane.WARNING_MESSAGE);
  }

  /**
   * Mostrar mensajes de información.
   * 
   * @param msj mensaje a mostrar.
   */
  private void mensajeInfo(String msj) {
    JOptionPane.showMessageDialog(null, msj, "Información", JOptionPane.INFORMATION_MESSAGE);
  }
}