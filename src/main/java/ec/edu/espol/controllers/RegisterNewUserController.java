/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.ventattv.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class RegisterNewUserController implements Initializable {

    @FXML
    private TextField nombres;
    @FXML
    private TextField apellidos;
    @FXML
    private TextField organizacion;
    @FXML
    private PasswordField contraseña;
    @FXML
    private TextField correo;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    

    @FXML
    private void registrarUsuario(MouseEvent event) {
        String nombres = (String) this.nombres.getText();
        String apellidos = (String) this.apellidos.getText();
        String organizacion = (String) this.organizacion.getText();
        String correo = (String) this.correo.getText();
        String contraseña = (String) this.contraseña.getText();
        
        ArrayList<Usuario> usuarios = Usuario.readFileSer();
        if(nombres.equals("") || apellidos.equals("") || organizacion.equals("")|| correo.equals("")||contraseña.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene obligatoriamente todos los campos.");
            mensaje.show();
        }
        else{
            if(Usuario.checkCorreo(usuarios, correo)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "El correo indicado ya existe en nuestra base de datos.");
                mensaje.show();
            }
            else{
                Usuario user = new Usuario(nombres, apellidos, organizacion, correo, contraseña);
                usuarios.add(user);
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "¡Usuario creado con éxito!");
                mensaje.show();
                Usuario.saveListUsuariosSer(usuarios);
            }

        }
    }
    
    @FXML
    public void switchToLogin(MouseEvent event){
        try{
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load(),600,420);
            App.setScene(sc);
        } catch(Exception ex){}
    }
}
