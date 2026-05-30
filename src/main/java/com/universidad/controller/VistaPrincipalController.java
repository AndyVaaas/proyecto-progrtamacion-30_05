/**
 * gestor ui y comunicacoin entre la bista y base de datos
 */
package com.universidad.controller;

import com.universidad.dao.PacienteDAO;
import com.universidad.model.Paciente;
import com.universidad.model.Prioridad;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

import javafx.fxml.FXML;

import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;

import javafx.scene.control.cell.PropertyValueFactory;

public class VistaPrincipalController {

    @FXML
    private TextField txtNombre;

    @FXML
    private TextField txtEdad;

    @FXML
    private TextField txtDpi;

    @FXML
    private TextArea txtSintomas;

    @FXML
    private ComboBox<Prioridad> cmbPrioridad;

    @FXML
    private TableView<Paciente> tablaPacientes;

    @FXML
    private TableColumn<Paciente, Integer> colId;

    @FXML
    private TableColumn<Paciente, String> colNombre;

    @FXML
    private TableColumn<Paciente, Prioridad> colPrioridad;

    private final PacienteDAO pacienteDAO = new PacienteDAO();

    @FXML
    public void initialize() {

        cmbPrioridad.getItems().addAll(Prioridad.values());

        colId.setCellValueFactory(
                new PropertyValueFactory<>("id"));

        colNombre.setCellValueFactory(
                new PropertyValueFactory<>("nombre"));

        colPrioridad.setCellValueFactory(
                new PropertyValueFactory<>("prioridad"));

        actualizarLista();
    }

    @FXML
    private void registrarPaciente() {

        try {

            Paciente paciente = new Paciente(
                    txtNombre.getText(),
                    Integer.parseInt(txtEdad.getText()),
                    txtDpi.getText(),
                    txtSintomas.getText(),
                    cmbPrioridad.getValue()
            );

            if (pacienteDAO.guardarPaciente(paciente)) {

                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                alert.setTitle("Éxito");
                alert.setHeaderText(null);
                alert.setContentText("Paciente registrado correctamente");
                alert.showAndWait();

                limpiarCampos();
                actualizarLista();
            }

        } catch (Exception e) {

            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText(null);
            alert.setContentText("Verifique los datos ingresados");
            alert.showAndWait();

            e.printStackTrace();
        }
    }

    @FXML
    private void actualizarLista() {

        ObservableList<Paciente> lista =
                FXCollections.observableArrayList(
                        pacienteDAO.listarPacientes()
                );

        tablaPacientes.setItems(lista);
    }

    private void limpiarCampos() {

        txtNombre.clear();
        txtEdad.clear();
        txtDpi.clear();
        txtSintomas.clear();
        cmbPrioridad.setValue(null);
    }
    @FXML
    private void atenderSiguiente() {

    if (pacienteDAO.atenderSiguientePaciente()) {

        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Paciente Atendido");
        alert.setHeaderText(null);
        alert.setContentText("Se atendió al siguiente paciente.");
        alert.showAndWait();

        actualizarLista();

    } else {

        Alert alert = new Alert(Alert.AlertType.WARNING);
        alert.setTitle("Sin pacientes");
        alert.setHeaderText(null);
        alert.setContentText("No hay pacientes pendientes.");
        alert.showAndWait();
    }
}
}