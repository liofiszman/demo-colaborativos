package Controllers;

import Classes.Turno;
import DTO.FichaMecanica;
import DTO.Mecanico;
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

        DTO.Turno turno = HelloApplication.turnosBO.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            Mecanico mecanico = HelloApplication.turnosBO.obtenerMecanico(turno.get_mecanico_id());
            FichaMecanica fichaMecanica = HelloApplication.turnosBO.obtenerFichaMecanica(turno.get_ficha_mecanica_id());

            String formatoCalendario = turno.get_fecha().toString();
            String FormatoHora = turno.get_hora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+mecanico.get_nombre()+", "+mecanico.get_especialidad());

            actividadesTextField.setText(fichaMecanica.get_actividades());
            insumosTextField.setText(fichaMecanica.get_repuestos());
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
