package Controllers;

import Classes.Turno;
import home.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

public class ConformidadController extends BaseController  {
    private String numeroTurno;

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.numeroTurnoText.getText();
        Turno turno = HelloApplication.turnosBO.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();

            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+turno.getMecanico().getNombre()+", "+turno.getMecanico().getEspecialidad());
            actividadesText.setText(turno.getFichaMecanica().getActividades());
            insumosText.setText(turno.getFichaMecanica().getRepuestos());
        }
    }

    @FXML protected void conformeButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.numeroTurnoText.getText();
        HelloApplication.turnosBO.firmaConforme(numeroTurno);
        backToHome(event);
    }

    @FXML protected void inconformeButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.numeroTurnoText.getText();
        HelloApplication.turnosBO.firmaInconforme(numeroTurno);
        backToHome(event);
    }

    // ASISTENCIA
    @FXML private TextField numeroTurnoText;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private Label actividadesText;
    @FXML private Label insumosText;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
