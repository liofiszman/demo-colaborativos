package Controllers;

import Business.TurnoBusinessObject;
import Classes.Opcion;
import Classes.Turno;
import home.HelloApplication;
import javafx.collections.FXCollections;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
        opcion.setPatente(this.PatenteTextField.getText());
        opcion.setCliente(this.ClienteTextField.getText());
        opcion.setEspecialidad(this.selectedEspecialidad.getText());
        opcion.setCompania(this.selectedCompania.getText());
        opcion.setFecha(this.FechaSeleccionada.getValue());

        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        verOpciones(stage);
    }

    /// Lista las opciones de turnos en formato de tabla.
    public void verOpciones(Stage stage) {
        // llamar elemento del DOM para obtener opciones de turnos a partir de los datos en Opcion opcion
        List<Turno> opciones = HelloApplication.turnosBO.obtenerTurnos(opcion);
        TableView<Turno> tableOpciones = new TableView<Turno>();
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
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /// Confirma un turno de la lista previa.
    private void reservarTurno(Turno turno, ActionEvent event) throws IOException, InterruptedException {
        int turnoN = HelloApplication.turnosBO.addTurno(turno, opcion);
        turno.setId(turnoN);

        new BuscadorController().buscarTurno(turno,event);
        //Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        //HelloApplication.buscadorResultado(stage);
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

        companiaCombo.getItems().setAll(HelloApplication.turnosBO.getCompanias());
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
            String formatoCalendario = turno.getFecha().toString();
            String FormatoHora = turno.getHora().toString();
            datosTurnoText.setText("Turno para el "+formatoCalendario+" a las "+FormatoHora+" hs");
            datosTurnoSecondText.setText("Mecánico "+turno.getMecanico().getNombre()+", "+turno.getMecanico().getEspecialidad());
        }
    }

    private void addButtonToTable(TableView table) {
        TableColumn<Turno, Void> colBtn = new TableColumn("Reservar");
        // refactor from https://riptutorial.com/javafx/example/27946/add-button-to-tableview
        Callback<TableColumn<Turno, Void>, TableCell<Turno, Void>> cellFactory = new Callback<TableColumn<Turno, Void>, TableCell<Turno, Void>>() {
            @Override
            public TableCell<Turno, Void> call(final TableColumn<Turno, Void> param) {
                final TableCell<Turno, Void> cell = new TableCell<Turno, Void>() {

                    private final Button btn = new Button("Reservar");
                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Turno turno = getTableView().getItems().get(getIndex());
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
