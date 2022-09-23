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

public class ActividadesController extends BaseController  {
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

            actividadesTextField.setText(turno.getFichaMecanica().getActividades());
            insumosTextField.setText(turno.getFichaMecanica().getRepuestos());
        }
    }

    @FXML
    protected void registrarButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        String actividadesText = this.actividadesTextField.getText();
        String insumosText = this.insumosTextField.getText();
        HelloApplication.turnosBO.registrarActividades(numeroTurno,actividadesText,insumosText);
        backToHome(event);
    }

    // ASISTENCIA
    @FXML private TextField TurnoTextField;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private TextField actividadesTextField;
    @FXML private TextField insumosTextField;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
