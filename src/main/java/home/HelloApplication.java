package home;

import Business.TurnoBusinessObject;
import Controllers.ReportesController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.sql.ResultSet;
import java.sql.Statement;

public class HelloApplication extends Application {
    public static TurnoBusinessObject turnosBO;

    @Override
    public void start(Stage stage) throws IOException {
        turnosBO = new TurnoBusinessObject();
        home(stage);
    }

    public static void home(Stage stage) throws IOException {
        showStage(stage, "/Views/home-view.fxml");
    }

    public static void calendario(Stage stage) throws IOException{
        showStage(stage, "/Views/calendario-view.fxml");
    }
    public static void asistencia(Stage stage) throws IOException {
        showStage(stage, "/Views/asistencia-view.fxml");
    }
    public static void actividades(Stage stage) throws IOException {
        showStage(stage, "/Views/actividades-view.fxml");
    }

    public static void conformidad(Stage stage) throws IOException {
        showStage(stage, "/Views/conformidad-view.fxml");
    }

    public static void messelector(Stage stage) throws IOException {
        showStage(stage, "/Views/reporte-messelector-view.fxml");
    }

    public static void buscador(Stage stage) throws IOException {
        showStage(stage, "/Views/buscador-view.fxml");
    }

    public static void buscadorResultado(Stage stage) throws IOException {
        showStage(stage, "/Views/buscador-view.fxml");
    }

    public static void reporteDiario(Stage stage) throws IOException {
        ReportesController.diario(stage);
    }

    public static void reporteMensual(Stage stage) throws IOException {
        ReportesController.mensual(stage);
    }

    public static void showStage(Stage stage, String viewName) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(viewName));
        Scene scene = new Scene(fxmlLoader.load(), 640, 480);
        stage.setTitle("Sistema de turnos para taller mecánico");
        stage.setScene(scene);
        stage.show();
    }

    public static void main(String[] args) {
        /*Llamada a Singleton y query en base de datos*/
        try {
            Statement st = Utils.DBConnection.getConnection().createStatement();
            ResultSet rs = st.executeQuery("select * from cliente");
        }catch(Exception ex){
            System.out.println("SQLException: " + ex.getMessage());
        }

        launch();
    }
}
