package home;

import Business.TurnoBusinessObject;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

public class HelloApplication extends Application {
    public static TurnoBusinessObject turnosBO;

    @Override
    public void start(Stage stage) throws IOException {
        turnosBO = new TurnoBusinessObject();
        home(stage);
    }

    public static void home(Stage stage) throws IOException {
        showStage(stage, "home-view.fxml");
    }

    public static void calendario(Stage stage) throws IOException{
        showStage(stage, "calendario-view.fxml");
    }
    public static void asistencia(Stage stage) throws IOException {
        showStage(stage, "asistencia-view.fxml");
    }
    public static void actividades(Stage stage) throws IOException {
        showStage(stage, "actividades-view.fxml");
    }

    public static void conformidad(Stage stage) throws IOException {
        showStage(stage, "conformidad-view.fxml");
    }

    public static void showStage(Stage stage, String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(viewName));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Sistema de turnos para taller mecánico");
        stage.setScene(scene);
        stage.show();
    }

    public static void diario(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        ObservableList<Arreglo> arreglos = getArreglos();
        table.setItems(arreglos);

        //Creating columns
        TableColumn clienteCol = new TableColumn("Patente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));
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

        //showStage(stage, "reporte-diario-view.fxml");
    }

    public static void messelector(Stage stage) throws IOException {
        showStage(stage, "reporte-messelector-view.fxml");
    }

    public static void buscador(Stage stage) throws IOException {
        showStage(stage, "buscador-view.fxml");
    }

    public static void buscadorResultado(Stage stage) throws IOException {
        showStage(stage, "buscador-view.fxml");
    }


    public static void mensual(Stage stage) throws IOException {
        TableView<Arreglo> table = new TableView<Arreglo>();
        ObservableList<Arreglo> arreglos = getArreglos();
        table.setItems(arreglos);

        //Creating columns
        TableColumn companiaCol = new TableColumn("Aseguradora");
        companiaCol.setCellValueFactory(new PropertyValueFactory<>("compania"));
        TableColumn fechaCol = new TableColumn("Fecha");
        fechaCol.setCellValueFactory(new PropertyValueFactory<>("fecha"));
        TableColumn clienteCol = new TableColumn("Cliente");
        clienteCol.setCellValueFactory(new PropertyValueFactory<>("cliente"));
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

        //showStage(stage, "reporte-mensual-view.fxml");
    }

    private static ObservableList<Arreglo> getArreglos() {
        ObservableList<Arreglo> list = FXCollections.observableArrayList(
                new Arreglo("03/09/2022","USP784", "SSJ-32", "mecánica en general",
                        "Conforme", "Cambio de aceite", "Sancor"),
                new Arreglo("06/09/2022","ASD123", "SSJ-32", "frenos",
                        "NO Conforme", "Alineado y balanceado", "Sancor"),
                new Arreglo("06/09/2022","AR787UX", "SSJ-32","mecánica en general",
                        "Conforme", "Cambio de bateria", "Sancor")
        );

        return list;
    }
        public static class Arreglo {

            public Arreglo(String fecha, String cliente, String mecanico, String especialidad,
                           String conformidad, String servicio, String compania) {
                setCliente(cliente);
                setMecanico(mecanico);
                setConformidad(conformidad);
                setFecha(fecha);
                setServicio(servicio);
                setCompania(compania);
                setEspecialidad(especialidad);
            }

            private String Especialidad;

            public void setEspecialidad(String value) { Especialidad = value; }
            public String getEspecialidad() { return Especialidad; }

            private String Compania;

            public void setCompania(String value) { Compania = value; }
            public String getCompania() { return Compania; }

            private String Servicio;

            public void setServicio(String value) { Servicio = value; }
            public String getServicio() { return Servicio; }

            private String Fecha;

            public void setFecha(String value) { Fecha = value; }
            public String getFecha() { return Fecha; }

            private String Cliente;

            public void setCliente(String value) { Cliente = value; }
            public String getCliente() { return Cliente; }

            private String Mecanico;
            public void setMecanico(String value) { Mecanico = value; }
            public String getMecanico() { return Mecanico; }

            private String Conformidad;
            public void setConformidad(String value) { Conformidad = value; }
            public String getConformidad() { return Conformidad; }
        }

        private String Fecha;

        public void setFecha(String value) { Fecha = value; }
        public String getFecha() { return Fecha; }

        private String Cliente;

        public void setCliente(String value) { Cliente = value; }
        public String getCliente() { return Cliente; }

        private String Mecanico;
        public void setMecanico(String value) { Mecanico = value; }
        public String getMecanico() { return Mecanico; }

        private String Conformidad;
        public void setConformidad(String value) { Conformidad = value; }
        public String getConformidad() { return Conformidad; }

    public static void main(String[] args) {
        launch();
    }
}