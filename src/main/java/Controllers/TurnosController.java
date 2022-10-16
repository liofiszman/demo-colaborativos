package Controllers;

import Business.TurnoBusinessObject;
import Classes.Opcion;
import DTO.*;
import home.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.util.Callback;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

public class TurnosController extends BaseController {
    private Opcion opcion;
    private String numeroTurno;

    /// Captura la información del formulario para solicitar turno
    @FXML
    protected void verOpcionesButtonClick(ActionEvent event) throws IOException {
        if(this.PatenteTextField.getText().trim().isEmpty()
                || this.ClienteTextField.getText().trim().isEmpty()
                || this.especialidadCombo.getSelectionModel().selectedItemProperty().toString().trim().isEmpty()
                || this.companiaCombo.getSelectionModel().selectedItemProperty().toString().trim().isEmpty()
                || this.FechaSeleccionada.getValue() == null
        ){
          Dialog dialog = new Dialog();
          dialog.setTitle("Error");
          DialogPane dialogPane = new DialogPane();
          dialogPane.setContentText("Complete todos los campos");
          dialogPane.getButtonTypes().addAll(ButtonType.OK);
          dialog.setDialogPane(dialogPane);

          dialog.show();
        }else {
            opcion.setPatente(this.PatenteTextField.getText());
            opcion.setCliente(this.ClienteTextField.getText());
            opcion.setEspecialidad(this.selectedEspecialidad.getText());
            opcion.setCompania(this.selectedCompania.getText());
            opcion.setFecha(this.FechaSeleccionada.getValue());

            Stage stage = (Stage) ((Node) event.getSource()).getScene().getWindow();
            verOpciones(stage);
        }
    }

    /// Lista las opciones de turnos en formato de tabla.
    public void verOpciones(Stage stage) {
        // llamar elemento del DOM para obtener opciones de turnos a partir de los datos en Opcion opcion
        List<Classes.Turno> opciones;

        try {opciones = HelloApplication.turnosBO.obtenerTurnos(opcion);}
        catch (Exception ex) { opciones = new ArrayList<Classes.Turno>(); }

        TableView<Classes.Turno> tableOpciones = new TableView<Classes.Turno>();
        tableOpciones.setItems(FXCollections.observableArrayList(opciones));

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory("fecha"));
        TableColumn horaCol = new TableColumn("Hora");
        horaCol.setCellValueFactory(new PropertyValueFactory("hora"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanicoNombre"));
        tableOpciones.getColumns().addAll(fechaCol, horaCol, mecanicoCol);

        addButtonToTable(tableOpciones);

        VBox vbox = new VBox(tableOpciones);
        vbox.getChildren().add(getBackButton());
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private static Button getBackButton() {
        var backButton = new Button();
        backButton.setAlignment(Pos.BOTTOM_CENTER);

        backButton.setText("X");
        backButton.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent event) {
                try {
                    backToHomeStatic(event);
                } catch (IOException e) {
                    throw new RuntimeException(e);
                }
            }
        });

        return backButton;
    }

    /// Confirma un turno de la lista previa.
    private void reservarTurno(Classes.Turno turno, ActionEvent event) throws IOException, InterruptedException {


        int turnoN = HelloApplication.turnosBO.addTurno(turno, opcion);

        if(turnoN > 0)
            new BuscadorController().buscarTurno(event);
    }

    @FXML private TextField BuscadorTurnoTextField;
    @FXML private TextField BuscadorPatenteTextField;

    @FXML private TextField TurnoTextField;
    @FXML private TextField datosTurnoText;
    @FXML private TextField datosTurnoSecondText;
    //region Turnos XML Generation
    @FXML private ComboBox<String> especialidadCombo;
    @FXML private Label selectedEspecialidad;

    @FXML private ComboBox<String> companiaCombo;
    @FXML private Label selectedCompania;

    @FXML private TextField PatenteTextField;
    @FXML private TextField ClienteTextField;

    @FXML private DatePicker FechaSeleccionada;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        especialidadCombo.getItems().setAll(HelloApplication.turnosBO.obtenerEspecialidades());
        selectedEspecialidad.textProperty().bind(especialidadCombo.getSelectionModel().selectedItemProperty());
        try {
            companiaCombo.getItems().setAll(HelloApplication.turnosBO.getCompanias());
        }
        catch (Exception ex){}

        selectedCompania.textProperty().bind(companiaCombo.getSelectionModel().selectedItemProperty());

        if (this.opcion == null)
            opcion = new Opcion();
    }

    @FXML
    protected void datosTurnoButtonClick(ActionEvent event) {
        numeroTurno = this.TurnoTextField.getText();

        Turno turno = HelloApplication.turnosBO.obtenerTurno(numeroTurno);

        if(turno == null) {
            datosTurnoText.setText("Turno "+numeroTurno+" no encontrado.");
        }
        else {
            Mecanico mecanico = HelloApplication.turnosBO.obtenerMecanico(turno.get_mecanico_id());
            String formatoCalendario = turno.get_fecha().toString();
            String FormatoHora = turno.get_hora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+ mecanico.get_nombre()+", "+mecanico.get_especialidad());
        }
    }

    private void addButtonToTable(TableView table) {
        TableColumn<Classes.Turno, Void> colBtn = new TableColumn("Reservar");
        // refactor from https://riptutorial.com/javafx/example/27946/add-button-to-tableview
        Callback<TableColumn<Classes.Turno, Void>, TableCell<Classes.Turno, Void>> cellFactory
                = new Callback<TableColumn<Classes.Turno, Void>, TableCell<Classes.Turno, Void>>() {
            @Override
            public TableCell<Classes.Turno, Void> call(final TableColumn<Classes.Turno, Void> param) {
                final TableCell<Classes.Turno, Void> cell = new TableCell<Classes.Turno, Void>() {

                    private final Button btn = new Button("Reservar");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Classes.Turno turno = getTableView().getItems().get(getIndex());
                            try {
                                reservarTurno(turno, event);
                            } catch (IOException | InterruptedException e) {
                                throw new RuntimeException(e);
                            }
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };
        colBtn.setCellFactory(cellFactory);
        table.getColumns().add(colBtn);
    }
    //endregion
}
