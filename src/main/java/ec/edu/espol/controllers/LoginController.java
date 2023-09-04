/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.ventattv.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.Pane;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class LoginController implements Initializable {
    
    private ArrayList<Usuario> usuarios;
    
    @FXML
    private Pane Pane1;
    @FXML
    private TextField correo;    
    @FXML
    private PasswordField contraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        this.usuarios = Usuario.readFileSer();
    }    

    @FXML
    private void login(MouseEvent event) {
        String correo = (String)this.correo.getText();
        String contraseña = (String)this.contraseña.getText();
        
        if(correo.equals("") || contraseña.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Ingrese un usuario y contraseña.");
            mensaje.show();
        }
        else{
            if(Usuario.checkCuenta(this.usuarios, correo, contraseña)){
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION, "Usuario correcto.");
                Optional<ButtonType> resultado = mensaje.showAndWait();
                if (resultado.isPresent()){
                    if (resultado.get() == ButtonType.OK){
                        Usuario user = Usuario.filtrarCorreo(this.usuarios, correo);
                        try{
                            FXMLLoader loader = App.loadFXML("menuPrincipal");
                            Scene sc = new Scene(loader.load(),600,420);
                            MenuPrincipalController controlador = loader.getController();
                            controlador.setUsuario(user);
                            App.setScene(sc);
                        } catch (Exception ex){
                            Alert a = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error.");
                            a.show();
                        }
                    }
                }
            }
            else{
                Alert mensaje = new Alert(Alert.AlertType.ERROR, "Usuario o contraseña inválidos.");
                mensaje.show();
            }
        }
    }
    
    @FXML
    private void switchToRegisterNewUser(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("registerNewUser");
            Scene sc = new Scene(loader.load(),600,420);
            App.setScene(sc);
        } catch(Exception ex){}
    }
}