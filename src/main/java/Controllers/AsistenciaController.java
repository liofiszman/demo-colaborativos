package Controllers;

import Classes.Opcion;
import Classes.Turno;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.stage.Stage;

import java.net.URL;
import java.time.format.DateTimeFormatter;
import java.util.ResourceBundle;

public class AsistenciaController extends BaseController  {
    private String numeroTurno ;

    /// Registra asistencia a un turno
    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();

        Opcion turno = new Opcion();
        String formatoCalendario = turno.getFecha().toString();
        String FormatoHora = turno.getFecha().format(DateTimeFormatter.ofPattern("HH:mm"));
        // TODO : get turno from number.
        datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
        datosTurnoSecondText.setText("Mecánico SSJ-32, especialidad General");
    }

    // ASISTENCIA
    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
