/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Usuario;
import ec.edu.espol.ventattv.App;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.AnchorPane;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class MenuPrincipalController implements Initializable{

    private Usuario usuario;
    
    @FXML
    private Label uNombres;
    @FXML
    private Label uApellidos;
    @FXML
    private Label uOrganizacion;
    @FXML
    private Label uCorreo;
    @FXML
    private PasswordField uContraseña;
    @FXML
    private AnchorPane tabPerfil;
    @FXML
    private ImageView btnREG;
    @FXML
    private AnchorPane anchorpane3;
    
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }  
    
    public void setUsuario(Usuario u){
        this.usuario = u;
        uNombres.setText(u.getNombres());
        uApellidos.setText(u.getApellidos());
        uOrganizacion.setText(u.getOrganizacion());
        uCorreo.setText(u.getCorreo());
        uContraseña.setText(u.getClave());
    }

    @FXML
    private void cambiarContraseña(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("changePassword");
            Scene sc = new Scene(loader.load(),600,420);
            ChangePasswordController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
            } catch (Exception ex){
                Alert a = new Alert(Alert.AlertType.ERROR,"Ha ocurrido un error.");
                a.show();
            }   
    }

    @FXML
    private void cerrarSesion(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("login");
            Scene sc = new Scene(loader.load(),600,420);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void switchToRegisterNewVehicle(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("registerNewVehicle");
            Scene sc = new Scene(loader.load(),600,420);
            RegisterNewVehicleController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void switchToSearchVehicles(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("searchVehicles");
            Scene sc = new Scene(loader.load(),600,420);
            SearchVehiclesController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void switchToLookOffers(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("lookOffers");
            Scene sc = new Scene(loader.load(),600,420);
            LookOffersController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }
    
}
