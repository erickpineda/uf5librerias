package net.erickpineda.generadorequipos.controllers;

import java.util.LinkedHashSet;
import java.util.Set;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import net.erickpineda.generadorequipos.models.Profesor;
import net.erickpineda.generadorequipos.utils.Util;

public class ContenidoController {
  @FXML
  private TextField director;
  @FXML
  private TextField coordinacion;
  @FXML
  private TextField secretario;
  @FXML
  private ListView<String> jefeEstudios;
  @FXML
  private Button recalcular;
  private Set<Profesor> listaOriginalProfesores;

  @FXML
  public void initialize() {

  }

  @FXML
  public void btnRecalcular(MouseEvent event) {
    if (hayDatos()) {
      borrarTodosLosCampos();
      distribuirPersonal(crearAPartirDeListaOriginal(listaOriginalProfesores));
      todosLosCamposComoEditables(true);
    }
  }

  /**
   * Método que se ejecuta cuando se lee un fichero XML y a partir de un conjunto de clases
   * {@link Profesor} rellenar los campos del controlador.
   */
  public void agregarPersonas() {
    borrarTodosLosCampos();
    Set<Profesor> listaTemporalProfesores = crearAPartirDeListaOriginal(listaOriginalProfesores);
    distribuirPersonal(listaTemporalProfesores);
    todosLosCamposComoEditables(false);
  }

  public boolean hayDatos() {
    return (listaOriginalProfesores != null && !listaOriginalProfesores.isEmpty());
  }

  /**
   * Método para crear una lista nueva de Profesores a partir de la original que ha sido procesada.
   * 
   * @param profesores conjunto de profesores el cual será la lista original sin modificar.
   * @return retorna un nuevo conjunto de clases Profesor a partir de una sin modificar.
   */
  private Set<Profesor> crearAPartirDeListaOriginal(Set<Profesor> profesores) {
    return new LinkedHashSet<Profesor>(profesores);
  }

  /**
   * Método que distribuye los distintos cargos a adquirir.
   * 
   * @param profesores conjunto a procesar su información.
   */
  private void distribuirPersonal(final Set<Profesor> profesores) {
    if (profesores.size() > 3) {
      coordinacion.setText(Util.extraerCoordinador(profesores).getNombreCompleto());
    }
    director.setText(Util.extraerDirector(profesores).getNombreCompleto());
    secretario.setText(Util.extraerSecretario(profesores).getNombreCompleto());
    jefeEstudios.setItems(getObservableList(profesores));
  }

  /**
   * 
   * @param profesores conjunto de profesores a agregar al {@link ObservableList}.
   * @return retorna un {@link ObservableList} para mostrarlo luego en el campo de Jefes de Estudio.
   */
  private ObservableList<String> getObservableList(final Set<Profesor> profesores) {
    ObservableList<String> ol = FXCollections.observableArrayList();
    for (Profesor p : profesores) {
      ol.add(p.getNombreCompleto());
    }
    return ol;
  }

  /**
   * Método para comprobar si hay mujeres y hombres en el fichero XML que ha sido procesado.
   * 
   * @param profesores conjunto de profesores para comprobar.
   * @return retorna true si hay tanto mujeres como hombres de lo contrario retorna false.
   */
  public boolean hayMujeresYHombres(final Set<Profesor> profesores) {
    int mujeres = 0, hombres = 0;
    for (Profesor p : profesores) {
      if (p.getSexo().equalsIgnoreCase("Dona")) {
        mujeres++;
      }
      if (p.getSexo().equalsIgnoreCase("Home")) {
        hombres++;
      }
    }
    return (hombres > 0 && mujeres > 0);
  }

  /**
   * 
   * @return retorna un equipo nuevo que luego se guardará en un fichero XML.
   */
  public Set<Profesor> nuevoEquipo() {
    return listaOriginalProfesores;
  }

  /**
   * Método que sirve para cambiar la lista de profesores, que se procesan cada vez que se abre un
   * fichero XML con información y datos correctos.
   * 
   * @param profesores conjunto de profesores a cambiar.
   */
  public void setProfesores(final Set<Profesor> profesores) {
    this.listaOriginalProfesores = profesores;
  }

  /**
   * Métod que permite cambiar el estado de todos los campos a no editable, si el parámetro es false
   * y a editable si es true.
   * 
   * @param valor booleano que servirá para indicar si los campos serán o no editables.
   */
  public void todosLosCamposComoEditables(final boolean valor) {
    director.setEditable(valor);
    coordinacion.setEditable(valor);
    secretario.setEditable(valor);
    jefeEstudios.setEditable(valor);
  }

  /**
   * Método que permite vaciar todos los campos.
   */
  public void borrarTodosLosCampos() {
    if (!director.getText().isEmpty()) {
      director.clear();
    }
    if (!coordinacion.getText().isEmpty()) {
      coordinacion.clear();
    }
    if (!secretario.getText().isEmpty()) {
      secretario.clear();
    }
    if (!jefeEstudios.getItems().isEmpty()) {
      jefeEstudios.getItems().clear();
    }
  }
}
