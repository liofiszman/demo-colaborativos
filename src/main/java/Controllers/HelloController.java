package Controllers;

import home.HelloApplication;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

import java.io.IOException;


public class HelloController {
    @FXML
    private Label welcomeText;

    @FXML
    private AnchorPane showPane;

    @FXML
    protected void onRegistrarAsistenciaButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.asistencia(stage);
    }
    @FXML
    protected void onRegistrarActividadesButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.actividades(stage);
    }
    @FXML
    protected void onRegistrarCalendarioButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.calendario(stage);
    }
    @FXML
    protected void onRegistrarConformidadButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.conformidad(stage);
    }
    @FXML
    protected void onReporteDiarioButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.diario(stage);
    }
    @FXML
    protected void onReporteMensualButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.messelector(stage);
    }

    @FXML
    protected void onObtenerReporteMensualButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.mensual(stage);
    }

    @FXML
    protected void backToHome(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        HelloApplication.home(stage);
    }

    // ASISTENCIA
    @FXML
    private Label datosTurnoText;
    @FXML
    private Label datosTurnoSecondText;
    @FXML
    private Label actividadesText;
    @FXML
    private Label insumosText;

    @FXML
    protected void datosTurnoButtonClick() {
        datosTurnoText.setText("Turno para el 10/10 a las 13:30 hs");
        datosTurnoSecondText.setText("Mecánico SSJ-32, especialidad General");

        actividadesText.setText("Alineado y balanceado.");
        insumosText.setText("Sin repuestos utilizados.");
    }

    //CALENDARIO
    @FXML
    private Label patenteText;
    @FXML
    private Label clienteText;
    @FXML
    private Label especialidadText;
    @FXML
    private Label fechaTurnoText;

    @FXML
    private DatePicker datePicker;

    public void confirmarTurnoButtonClick(ActionEvent actionEvent) {
        patenteText.setText("OSJ-321");
        clienteText.setText("Juan Carlos Castro");
        especialidadText.setText("Frenos");
        fechaTurnoText.setText("Turno para el 10/10 a las 13:30 hs");
    }
}