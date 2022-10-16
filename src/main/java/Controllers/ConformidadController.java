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

public class ConformidadController extends BaseController  {
    private String numeroTurno;

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.numeroTurnoText.getText();
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

            actividadesText.setText(fichaMecanica.get_actividades());
            insumosText.setText(fichaMecanica.get_repuestos());
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
