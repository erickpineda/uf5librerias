package net.erickpineda.generadorequipos.controllers;

import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.scene.control.Button;

import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;

public class RootController {
	@FXML
	private Button leerFichero;
	@FXML
	private Button guardar;
	@FXML
	private BorderPane contenido;

	@FXML
	public void btnLeerFichero(MouseEvent event) {
	  contenido.getStyleClass().add("contenido");
	}
	
	@FXML
	public void btnGuardar(MouseEvent event) {
	  
	}
}
