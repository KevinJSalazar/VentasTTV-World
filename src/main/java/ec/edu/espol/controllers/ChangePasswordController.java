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
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.input.MouseEvent;
import javafx.stage.Stage;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class ChangePasswordController implements Initializable {
    
    private Usuario usuario;
    private ArrayList<Usuario> usuarios;

    @FXML
    private PasswordField nuevaContraseña;
    @FXML
    private PasswordField antiguaContraseña;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        usuarios = Usuario.readFileSer();
    }    
    
    public void setUsuario(Usuario u){
        this.usuario = u;
    }

    @FXML
    private void aceptarCambioContraseña(MouseEvent event) {
        String contraseñaAntigua = (String) this.antiguaContraseña.getText();
        String contraseñaNueva = (String) this.nuevaContraseña.getText();
        if(contraseñaAntigua.equals("") || contraseñaNueva.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene todos los campos solicitados.");
            mensaje.show();
        }
        else if(contraseñaNueva.equals(contraseñaAntigua)){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Las contraseñas ingresadas son iguales.");
            mensaje.show();
        }
        else{
            if(usuario.getClave().equals(contraseñaAntigua)){
                Alert mensaje = new Alert(Alert.AlertType.CONFIRMATION,"¿Está seguro de cambiar su contraseña?");
                Optional<ButtonType> resultado = mensaje.showAndWait();
                if (resultado.isPresent()){
                    if (resultado.get() == ButtonType.OK){
                        for(Usuario usuarioActual : usuarios){
                            if(usuarioActual.getCorreo().equals(usuario.getCorreo())){
                                usuarioActual.setClave(contraseñaNueva);
                                Usuario.saveListUsuariosSer(usuarios);
                                regresar();
                            }
                        }
                    } 
                }
            }
            else{
                Alert mensaje = new Alert(Alert.AlertType.ERROR, "Contraseña incorrecta, inténtelo nuevamente.");
                mensaje.show();
            }
        }      
    }
    
    public void regresar(){
        try{
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load(),600,420);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void cancelar(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("menuPrincipal");
            Scene sc = new Scene(loader.load(),600,420);
            MenuPrincipalController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }
}
