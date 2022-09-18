package Controllers;

import Classes.Mecanico;
import Classes.Opcion;
import Classes.Turno;
import home.HelloApplication;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
    private Opcion opcion ;

    /// Captura la información del formulario para solicitar turno
    @FXML
    protected void verOpcionesButtonClick(ActionEvent event) throws IOException {
        Stage stage = (Stage)((Node)event.getSource()).getScene().getWindow();
        opcion.setPatente(this.PatenteTextField.getText());
        opcion.setCliente(this.ClienteTextField.getText());
        opcion.setEspecialidad(this.selectedEspecialidad.getText());
        opcion.setCompania(this.selectedCompania.getText());
        opcion.setFecha(this.FechaSeleccionada.getValue());
        verOpciones(stage);
    }

    /// Lista las opciones de turnos en formato de tabla.
    public void verOpciones(Stage stage) {
        List<Turno> opciones = new ArrayList<Turno>();

        opciones.add(new Turno());

        TableView<Turno> tableOpciones = new TableView<Turno>();
        // TODO : llamar elemento del DOM para obtener opciones de turnos a partir de los datos en Opcion opcion
        tableOpciones.setItems(FXCollections.observableArrayList(opciones));

        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory("fecha"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        tableOpciones.getColumns().addAll(fechaCol, mecanicoCol);

        addButtonToTable(tableOpciones);

        VBox vbox = new VBox(tableOpciones);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    /// Confirma un turno de la lista previa.
    private void reservarTurno(Turno turno) {
        // TODO : llamar elemento del DOM para reservar turno
        // TODO : redirigir a buscador de turnos.
    }



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
        especialidadCombo.getItems().setAll(HelloApplication.getEspecialidades());
        selectedEspecialidad.textProperty().bind(especialidadCombo.getSelectionModel().selectedItemProperty());

        companiaCombo.getItems().setAll(HelloApplication.getCompanias());
        selectedCompania.textProperty().bind(companiaCombo.getSelectionModel().selectedItemProperty());

        if (this.opcion == null)
            opcion = new Opcion();
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
                            reservarTurno(turno);
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
