package Controllers;

import Business.TurnoBusinessObject;
import Classes.Turno;
import home.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class AsistenciaController extends BaseController  {
    private String numeroTurno;

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.TurnoTextField.getText();

        Turno turno = HelloApplication.turnosBO.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+turno.getMecanico().getNombre()+", "+turno.getMecanico().getEspecialidad());
        }
    }

    @FXML
    protected void confirmarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        HelloApplication.turnosBO.registrarAsistencia(numeroTurno);
        backToHome(event);
    }

    @FXML
    protected void cancelarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        HelloApplication.turnosBO.cancelarTurno(numeroTurno);
        backToHome(event);
    }

    // ASISTENCIA
    @FXML private TextField TurnoTextField;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
