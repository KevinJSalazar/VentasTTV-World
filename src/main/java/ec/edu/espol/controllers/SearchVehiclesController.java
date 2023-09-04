/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.NegativeNumberException;
import ec.edu.espol.model.Oferta;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.ventattv.App;
import java.net.URL;
import java.util.ArrayList;
import java.util.Optional;
import java.util.ResourceBundle;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class SearchVehiclesController implements Initializable {
    
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Usuario> usuarios;
    private ArrayList<Oferta> ofertas;
    private String tipo = "Todos";
    private int precioMin;
    private int precioMax;
    private int añoMin;
    private int añoMax;
    private double recorridoMin;
    private double recorridoMax;
    
    @FXML
    private BorderPane borderPane;
    @FXML
    private ComboBox<String> cbTipoVeh;
    @FXML
    private HBox hVehiculo;
    @FXML
    private TextField minPrecio;
    @FXML
    private TextField maxPrecio;
    @FXML
    private TextField minAño;
    @FXML
    private TextField maxAño;
    @FXML
    private TextField minRec;
    @FXML
    private TextField maxRec;
    @FXML
    private TableView<Vehiculo> tableView;
    @FXML
    private TableColumn<Vehiculo, String> tPlaca;
    @FXML
    private TableColumn<Vehiculo, String> tMarca;
    @FXML
    private TableColumn<Vehiculo, String> tModelo;
    @FXML
    private TableColumn<Vehiculo, Integer> tAño;
    @FXML
    private TableColumn<Vehiculo, Double> tRec;
    @FXML
    private TableColumn<Vehiculo, Integer> tPrecio;

    private ObservableList<Vehiculo> vehiculosObs;
    
    @FXML
    private VBox vboferta;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.readFileSer();
        usuarios = Usuario.readFileSer();
        ofertas = Oferta.readFileSer();
        cbTipoVeh.getItems().addAll("Todos","Automóvil", "Camioneta", "Motocicleta");
        vboferta.setSpacing(5);
        hVehiculo.setSpacing(10);

        mostrarImagenesVehiculos(vehiculos);
        
        vehiculosObs = FXCollections.observableArrayList(vehiculos);
        this.tPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
        this.tMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.tModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        this.tAño.setCellValueFactory(new PropertyValueFactory("año"));
        this.tRec.setCellValueFactory(new PropertyValueFactory("recorrido"));
        this.tPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.tableView.setItems(vehiculosObs);
    }

    public void setUsuario(Usuario u){
        this.usuario = u;
    }

    @FXML
    private void switchToMenuPrincipal2(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("menuPrincipal");
            Scene sc = new Scene(loader.load(),600,420);
            MenuPrincipalController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void btnFiltrar(MouseEvent event) {
        try{
            if(((String)this.minPrecio.getText()).equals(""))
                precioMin = 1;
            else precioMin = Integer.parseInt((String)this.minPrecio.getText());

            if(((String)this.maxPrecio.getText()).equals(""))
                precioMax = Integer.MAX_VALUE;
            else precioMax = Integer.parseInt((String)this.maxPrecio.getText());

            if(((String)this.minAño.getText()).equals(""))
                añoMin = 1;
            else añoMin = Integer.parseInt((String)this.minAño.getText());

            if(((String)this.maxAño.getText()).equals(""))
                añoMax = Integer.MAX_VALUE;
            else añoMax = Integer.parseInt((String)this.maxAño.getText());

            if(((String)this.minRec.getText()).equals(""))
                recorridoMin = 0;
            else recorridoMin = Double.parseDouble((String)this.minRec.getText());

            if(((String)this.maxRec.getText()).equals(""))
                recorridoMax = Double.MAX_VALUE;
            else recorridoMax = Double.parseDouble((String)this.maxRec.getText());
            
            if((String)cbTipoVeh.getValue() != null)
                tipo = (String)cbTipoVeh.getValue();
            mostrarVehiculos(Vehiculo.filtrarVehiculos(vehiculos, tipo, recorridoMin, recorridoMax, añoMin, añoMax, precioMin, precioMax)); 
            mostrarImagenesVehiculos(Vehiculo.filtrarVehiculos(vehiculos, tipo, recorridoMin, recorridoMax, añoMin, añoMax, precioMin, precioMax));
        } catch(NegativeNumberException nn){
            Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos.");
            mensaje.show();
        } catch(NumberFormatException n){
            Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos.");
            mensaje.show();
        } catch(Exception ex){
            Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error.");
            mensaje.show();
        }
    }
    
    private void mostrarVehiculos(ArrayList<Vehiculo> filvehiculos){
        ObservableList vehiculosObs2 = FXCollections.observableArrayList(filvehiculos);
        this.tPlaca.setCellValueFactory(new PropertyValueFactory("placa"));
        this.tMarca.setCellValueFactory(new PropertyValueFactory("marca"));
        this.tModelo.setCellValueFactory(new PropertyValueFactory("modelo"));
        this.tAño.setCellValueFactory(new PropertyValueFactory("año"));
        this.tRec.setCellValueFactory(new PropertyValueFactory("recorrido"));
        this.tPrecio.setCellValueFactory(new PropertyValueFactory("precio"));
        this.tableView.setItems(vehiculosObs2);
    }
    
    private void mostrarImagenesVehiculos(ArrayList<Vehiculo> vehiculoss){
        hVehiculo.getChildren().clear();
        vboferta.getChildren().clear();
        for(Vehiculo v : vehiculoss){
            Image im = new Image("imagenesVehiculos/"+v.getPlaca()+".png");
            ImageView img = new ImageView(im);
            img.setFitHeight(130);
            img.setFitWidth(130);
            img.addEventHandler(MouseEvent.MOUSE_CLICKED, (MouseEvent t) -> {
                vboferta.getChildren().clear();
                Text texto = new Text("Placa: "+v.getPlaca());
                TextField t1 = new TextField();
                Button t2 = new Button("Ofertar"); 
                vboferta.getChildren().addAll(texto,t1,t2);
                t2.addEventHandler(MouseEvent.MOUSE_CLICKED, new EventHandler(){
                    @Override
                    public void handle(Event t) {
                        try{
                            int valorOfertado = Integer.parseInt((String) t1.getText());
                            if(valorOfertado <= 0)
                                throw new NegativeNumberException();
                            else{
                                Oferta oferta = new Oferta(valorOfertado, usuario, v);
                                Alert mensaje1 = new Alert(Alert.AlertType.CONFIRMATION, "¿Seguro que quiere realizar esta oferta?");
                                Optional<ButtonType> resultado = mensaje1.showAndWait();
                                if(resultado.isPresent()){
                                    if(resultado.get() == ButtonType.OK){
                                        if(usuario.getCorreo().equals(v.getUsuario().getCorreo())){
                                            Alert mensaje2 = new Alert(Alert.AlertType.ERROR, "El vehículo por el que está ofertando es suyo.");
                                            mensaje2.show();
                                        }                                      
                                        else if(Oferta.checkOferta(ofertas, usuario.getCorreo(), v.getPlaca(), valorOfertado)){
                                            Alert mensaje2 = new Alert(Alert.AlertType.ERROR, "Ya ha realizado una oferta para este vehículo con ese valor.");
                                            mensaje2.show();
                                        }
                                        else{
                                            Alert mensaje2 = new Alert(Alert.AlertType.INFORMATION, "¡Su oferta ha sido realizada con éxito!");
                                            mensaje2.show();
                                            Usuario comprador = Usuario.filtrarCorreo(usuarios, usuario.getCorreo());
                                            Usuario vendedor = Usuario.filtrarCorreo(usuarios, v.getUsuario().getCorreo());
                                            comprador.getOfertas().add(oferta);
                                            Vehiculo vVendedor = Vehiculo.filtrarPlaca(vendedor.getVehiculos(), v.getPlaca());
                                            vVendedor.getOfertas().add(oferta);
                                            Usuario.saveListUsuariosSer(usuarios);
                                            Vehiculo veh = Vehiculo.filtrarPlaca(vehiculos, v.getPlaca());
                                            veh.getOfertas().add(oferta);
                                            Vehiculo.saveListVehiculosSer(vehiculos);
                                            ofertas.add(oferta);
                                            Oferta.saveListOfertaSer(ofertas);
                                        } 
                                    }
                                }
                            }
                        } catch(NegativeNumberException nn){
                            Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese un valor numérico válido.");
                            mensaje.show();
                        } catch(NumberFormatException nf){
                            Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese un valor numérico.");
                            mensaje.show();
                        }
                    }
                });
            });
            hVehiculo.getChildren().add(img);
        }
    }

    @FXML
    private void clear(MouseEvent event) {
        minPrecio.setText("");
        maxPrecio.setText("");
        minAño.setText("");
        maxAño.setText("");
        minRec.setText("");
        maxRec.setText("");
    }
      
}
