package Controllers;

import Classes.Opcion;
import Classes.Turno;
import home.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import static home.HelloApplication.showStage;

public class BuscadorController extends BaseController {
    private Opcion opcion;
    private String numeroTurno;

    /// Confirma un turno de la lista previa.
    public void buscarTurno(Turno turno, ActionEvent event) throws IOException, InterruptedException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        showStage(stage, "buscador-view.fxml");
    }

    @FXML
    protected void onBuscarTurnoClick(ActionEvent event) throws IOException {
        Turno turno = null;
        if (BuscadorTurnoTextField != null && !BuscadorTurnoTextField.getText().isEmpty()) {
            turno = HelloApplication.turnosBO.obtenerTurno(BuscadorTurnoTextField.getText());
        }
        else {
            if(BuscadorPatenteTextField != null && !BuscadorPatenteTextField.getText().isEmpty()) {
                try {
                    turno = HelloApplication.turnosBO.obtenerTurnos(BuscadorPatenteTextField.getText())
                            .stream().findFirst().get();
                }
                catch (Exception ex) { }
            }
        }

        if(turno == null) {
            datosTurnoText.setText("Turno no encontrado.");
            datosTurnoSecondText.setText("");
        }
        else {
            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+turno.getMecanico().getNombre()+", "+turno.getMecanico().getEspecialidad());
        }
    }

    @FXML private TextField BuscadorTurnoTextField;
    @FXML private TextField BuscadorPatenteTextField;

    @FXML private Label TurnoTextField;
    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        BuscadorTurnoTextField.setText(HelloApplication.turnosBO.obtenerTurnoID());
    }
}
