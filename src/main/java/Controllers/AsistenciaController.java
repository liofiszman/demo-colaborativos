package Controllers;

import Classes.Opcion;
import Classes.Turno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.util.ResourceBundle;

public class AsistenciaController extends BaseController  {
    private String numeroTurno ;

    /// Registra asistencia a un turno
    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Turno turno = new Turno();
        // TODO : get turno from number.
        datosTurnoText.setText("Turno para el 10/10 a las 13:30 hs");
        datosTurnoSecondText.setText("Mecánico SSJ-32, especialidad General");
    }

    // ASISTENCIA
    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
