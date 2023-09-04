/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Utilitaria;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.ventattv.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import javafx.beans.property.SimpleStringProperty;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.event.EventType;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class LookOffersController implements Initializable {
    
    private Usuario usuario;
    private String placa = "Todos";
    private ArrayList<Oferta> ofertas;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Vehiculo> vehiculosArchivo;
    private ArrayList<Usuario> usuariosArchivo;
    
    @FXML
    private ComboBox<String> cbxplacas;
//    private VBox hbofertas;
    @FXML
    private TableView<Oferta> tvoferta;
    @FXML
    private TableColumn<Oferta, String> colPlaca;
    @FXML
    private TableColumn<Oferta, String> colCorreo;
    @FXML
    private TableColumn<Oferta, Integer> colValor;
    @FXML
    private ImageView imv;
    @FXML
    private Button btnAceptar;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculosArchivo = Vehiculo.readFileSer();
        usuariosArchivo = Usuario.readFileSer();
        ofertas = Oferta.readFileSer();
    } 
    
    public void setUsuario(Usuario u){
        this.usuario = u;
        llenarComboBox();
        vehiculos = usuario.getVehiculos();
        cbxplacas.setOnAction(event -> mostrarOfertas());
        mostrarImagen();

    }
    
    private void llenarComboBox() {
        cbxplacas.getItems().addAll(usuario.getPlacas());
        cbxplacas.getItems().add("Todos");
    }
    
    private void llenarOfertas(Oferta oferta){
        this.colPlaca.setCellValueFactory(cellData ->{
            SimpleStringProperty property = new SimpleStringProperty();
            if(cellData.getValue() != null)
                property.setValue(cellData.getValue().getVehiculo().getPlaca());
            return property;
        });
        this.colCorreo.setCellValueFactory(cellData ->{
            SimpleStringProperty property = new SimpleStringProperty();
            if(cellData.getValue() != null)
                property.setValue(cellData.getValue().getUsuario().getCorreo());
            return property;
        });
        this.colValor.setCellValueFactory(new PropertyValueFactory("valor"));
        tvoferta.getItems().add(oferta);
    }
    
    private void mostrarOfertas(){
        tvoferta.getItems().clear();
        imv.setImage(null);
        placa = (String) cbxplacas.getValue();
        for(Vehiculo v : vehiculos){
            if(placa.equals("Todos") || v.getPlaca().equals(placa)){
                for(Oferta f : v.getOfertas())
                    llenarOfertas(f);
            }
        }
    }
    
    private void mostrarImagen(){
        tvoferta.getSelectionModel().selectedItemProperty().addListener(
        (observable, oldValue, newValue) -> {
        if (newValue != null) {
            String placaSeleccionada = newValue.getVehiculo().getPlaca();
            String correoSeleccionado = newValue.getUsuario().getCorreo();
            int valorSeleccionado = newValue.getValor();
            imv.setImage(new Image("imagenesVehiculos/"+placaSeleccionada+".png"));
            btnAceptar.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                @Override
                public void handle(Event t) {
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "¡Se ha enviado con éxito su respuesta al ofertante!");
                    mensaje.show();
                    Vehiculo v = Vehiculo.filtrarPlaca(vehiculosArchivo, placaSeleccionada);
                    vehiculosArchivo.remove(v);
                    Vehiculo v1 = Vehiculo.filtrarPlaca(vehiculos, placaSeleccionada);
                    vehiculos.remove(v1);
                    Usuario vendedor = Usuario.filtrarCorreo(usuariosArchivo, usuario.getCorreo());
                    Vehiculo v2 = Vehiculo.filtrarPlaca(vendedor.getVehiculos(), placaSeleccionada);
                    vendedor.getVehiculos().remove(v2);
                    Oferta f1 = Oferta.filtrarOferta(ofertas, correoSeleccionado, placaSeleccionada, valorSeleccionado);
                    ofertas.remove(f1);
                    Usuario comprador = Usuario.filtrarCorreo(usuariosArchivo, correoSeleccionado);
                    Oferta f2 = Oferta.filtrarOferta(comprador.getOfertas(), correoSeleccionado, placaSeleccionada, valorSeleccionado);
                    comprador.getOfertas().remove(f2);
                    Vehiculo.saveListVehiculosSer(vehiculosArchivo);
                    Oferta.saveListOfertaSer(ofertas);
                    Usuario.saveListUsuariosSer(usuariosArchivo);
                    String cuerpo = "El propietario del vehículo:\n" + v.getMarca() + " " + v.getModelo() + " - Placa: " + v.getPlaca() + " - Recorrido: " + v.getRecorrido() + " - Año: " + v.getAño() + "\nHa aceptado tu oferta de: " + valorSeleccionado;
                    Utilitaria.sendMensaje(usuario.getCorreo(), "¡Una de sus ofertas ha sido aceptada!", cuerpo + "\nCorreo del propietario: " + correoSeleccionado);
                }
                
            });
        }
        });
        
    }
    
    
    @FXML
    private void regresar(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("menuPrincipal");
            Scene sc = new Scene(loader.load(),600,420);
            MenuPrincipalController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    
    
    
}
