package Controllers;

import Business.EstadoTurnoEnum;
import Classes.Turno;
import DTO.FichaMecanica;
import DTO.Mecanico;
import home.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
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

        DTO.Turno turno = HelloApplication.turnosBO.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            Mecanico mecanico = HelloApplication.turnosBO.obtenerMecanico(turno.get_mecanico_id());

            String formatoCalendario = turno.get_fecha().toString();
            String FormatoHora = turno.get_hora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+mecanico.get_nombre()+", "+mecanico.get_especialidad());
        }
    }

    @FXML
    protected void confirmarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        Classes.Turno turno = HelloApplication.turnosBO.obtenerTurnoCompleto(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("No se encontró un el turno ");
        }
        else if(turno != null && turno.getEstadoTurno() != EstadoTurnoEnum.CREADO)
            datosTurnoText.setText("No se puede confirmar un turno con estado " + turno.getEstadoTurno().toString());
        else {
            try{
                HelloApplication.turnosBO.registrarAsistencia(numeroTurno);
                backToHome(event);
            }
            catch(Exception ex){
                datosTurnoText.setText("No se encontró un el turno "+numeroTurno);
            }
        }
    }

    @FXML
    protected void cancelarTurnoButtonClick(ActionEvent event) throws IOException {
        numeroTurno = this.TurnoTextField.getText();
        Classes.Turno turno = HelloApplication.turnosBO.obtenerTurnoCompleto(numeroTurno);

        if(turno != null && turno.getEstadoTurno() != EstadoTurnoEnum.CREADO)
            datosTurnoText.setText("No se puede cancelar un turno con estado " + turno.getEstadoTurno().toString());
        else {
            try{
                HelloApplication.turnosBO.cancelarTurno(numeroTurno);
                backToHome(event);
            }
            catch(Exception ex){
                datosTurnoText.setText("No se encontró un el turno "+numeroTurno+" para cancelar");
            }
        }
    }

    // ASISTENCIA
    @FXML private TextField TurnoTextField;

    @FXML private Label datosTurnoText;
    @FXML private Label datosTurnoSecondText;

    @FXML private Button confirmarTurnoButton;
    @FXML private Button cancelarTurnoButton;

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

    }
}
