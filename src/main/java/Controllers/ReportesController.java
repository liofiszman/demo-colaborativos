package Controllers;

import Business.ReporteStrategy.TipoReporteEnum;
import Classes.Arreglo;
import Classes.Turno;
import home.HelloApplication;
import javafx.collections.ObservableList;
import javafx.collections.ObservableListBase;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import static javafx.collections.FXCollections.observableList;

public class ReportesController extends BaseController {

    public static void diario(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        var turnos = HelloApplication.turnosBO.GetTurnos(TipoReporteEnum.Diario);
        ObservableList<Arreglo> arreglos = getArreglos(turnos);
        table.setItems(arreglos);

        //Creating columns
        TableColumn clienteCol = new TableColumn("Patente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("patente"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn especialidadCol = new TableColumn("Especialidad");
        especialidadCol.setCellValueFactory(new PropertyValueFactory("especialidad"));
        TableColumn conformidadCol = new TableColumn("Conformidad");
        conformidadCol.setCellValueFactory(new PropertyValueFactory("conformidad"));
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn servicioCol = new TableColumn("Servicio");
        servicioCol.setCellValueFactory(new PropertyValueFactory("servicio"));
        table.getColumns().addAll(clienteCol, mecanicoCol, especialidadCol, conformidadCol, servicioCol);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    private static ObservableList<Arreglo> getArreglos(List<Turno> turnos) {
        List<Arreglo> arreglos = new ArrayList<>();
        for(Turno turno :turnos) {
            arreglos.add(new Arreglo(turno));
        }

        return observableList(arreglos);
    }

    public static void mensual(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        var turnos = HelloApplication.turnosBO.GetTurnos(TipoReporteEnum.Mensual);
        ObservableList<Arreglo> arreglos = getArreglos(turnos);
        table.setItems(arreglos);

        //Creating columns
        TableColumn companiaCol = new TableColumn("Aseguradora");
        companiaCol.setCellValueFactory(new PropertyValueFactory<>("compania"));
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn clienteCol = new TableColumn("Patente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("patente"));
        TableColumn mecanicoCol = new TableColumn("Mecanico");
        mecanicoCol.setCellValueFactory(new PropertyValueFactory("mecanico"));
        TableColumn servicioCol = new TableColumn("Servicio");
        servicioCol.setCellValueFactory(new PropertyValueFactory("servicio"));
        TableColumn conformidadCol = new TableColumn("Conformidad");
        conformidadCol.setCellValueFactory(new PropertyValueFactory("conformidad"));
        table.getColumns().addAll(companiaCol, fechaCol, clienteCol, mecanicoCol, servicioCol, conformidadCol);

        VBox vbox = new VBox(table);
        Scene scene = new Scene(vbox, 800, 600);
        stage.setScene(scene);
        stage.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) { }
}
