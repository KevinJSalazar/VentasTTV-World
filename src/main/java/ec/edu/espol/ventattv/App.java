package ec.edu.espol.ventattv;

import ec.edu.espol.model.*;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.ArrayList;

/**
 * JavaFX App
 */
public class App extends Application {

    private static Scene scene;
    private static Stage st;

    @Override
    public void start(Stage stage) throws IOException {
        st = stage;
        scene = new Scene(loadFXML("login").load(), 600, 420);
        stage.setTitle("VendemosTTV.exe");
        stage.setScene(scene);
        stage.setResizable(false);
        stage.show();
        
    }

    public static void setRoot(String fxml) throws IOException {
        scene.setRoot(loadFXML(fxml).load());
    }
    
    public static FXMLLoader loadFXML(String fxml) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(App.class.getResource(fxml + ".fxml"));
        return fxmlLoader;
    }
    
    public static void setScene(Scene sc) throws IOException {
        st.setScene(sc);
    }

    public static void main(String[] args) {
        System.out.println(Usuario.readFileSer());
        System.out.println(Vehiculo.readFileSer());
        System.out.println(Oferta.readFileSer());
//        for(Usuario u : Usuario.readFileSer()){
//            System.out.println(u.getVehiculos());
//        }
//        System.out.println(Usuario.readFileSer().get(0).getVehiculos().get(6).getOfertas());
        launch();
    }

}