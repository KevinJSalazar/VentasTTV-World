/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/javafx/FXMLController.java to edit this template
 */
package ec.edu.espol.controllers;

import ec.edu.espol.model.Auto;
import ec.edu.espol.model.Camioneta;
import ec.edu.espol.model.NegativeNumberException;
import ec.edu.espol.model.Usuario;
import ec.edu.espol.model.Vehiculo;
import ec.edu.espol.ventattv.App;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.ResourceBundle;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;

/**
 * FXML Controller class
 *
 * @author User A1
 */
public class RegisterNewVehicleController implements Initializable {
    
    private Usuario usuario;
    private ArrayList<Vehiculo> vehiculos;
    private ArrayList<Usuario> usuarios;
    final FileChooser fc = new FileChooser();
    private File imgFile;
    private File imgFileA;
    private File imgFileC;
    
    @FXML
    private TextField placa;
    @FXML
    private TextField marca;
    @FXML
    private TextField modelo;
    @FXML
    private TextField color;
    @FXML
    private TextField tipodemotor;
    @FXML
    private TextField tipodecombustible;
    @FXML
    private TextField precio;
    @FXML
    private TextField año;
    @FXML
    private TextField recorrido;
    @FXML
    private TextField placaA;
    @FXML
    private TextField marcaA;
    @FXML
    private TextField modeloA;
    @FXML
    private TextField colorA;
    @FXML
    private TextField tipodemotorA;
    @FXML
    private TextField tipodecombustibleA;
    @FXML
    private TextField precioA;
    @FXML
    private TextField añoA;
    @FXML
    private TextField recorridoA;
    @FXML
    private TextField vidriosA;
    @FXML
    private TextField transmisionA;
    @FXML
    private TextField placaC;
    @FXML
    private TextField marcaC;
    @FXML
    private TextField modeloC;
    @FXML
    private TextField colorC;
    @FXML
    private TextField tipodemotorC;
    @FXML
    private TextField tipodecombustibleC;
    @FXML
    private TextField precioC;
    @FXML
    private TextField añoC;
    @FXML
    private TextField recorridoC;
    @FXML
    private TextField vidriosC;
    @FXML
    private TextField transmisionC;
    @FXML
    private TextField tracciónC;
    @FXML
    private ImageView ivFile;
    @FXML
    private ImageView ivFileA;
    @FXML
    private ImageView ivFileC;

    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        vehiculos = Vehiculo.readFileSer();
        usuarios = Usuario.readFileSer();
    }
    
    public void setUsuario(Usuario u){
        this.usuario = u;
    }

    @FXML
    private void switchToMenuPrincipal(MouseEvent event) {
        try{
            FXMLLoader loader = App.loadFXML("menuPrincipal");
            Scene sc = new Scene(loader.load(),600,420);
            MenuPrincipalController controlador = loader.getController();
            controlador.setUsuario(this.usuario);
            App.setScene(sc);
        } catch(Exception ex){}
    }

    @FXML
    private void registrarMoto(MouseEvent event) {
        String pla = (String) this.placa.getText();
        String mar = (String) this.marca.getText();
        String mod = (String) this.modelo.getText();
        String col = (String) this.color.getText();
        String tipmot = (String) this.tipodemotor.getText();
        String tipcom = (String) this.tipodecombustible.getText();
        String pre = (String) this.precio.getText();
        String año = (String) this.año.getText();
        String rec = (String) this.recorrido.getText();

        if(pla.equals("") || mar.equals("") || mod.equals("") || col.equals("") || tipmot.equals("") || tipcom.equals("") || año.equals("") || pre.equals("") || rec.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene obligatoriamente todos los campos.");
            mensaje.show();
        }
        else{
            if(Vehiculo.checkPlaca(this.vehiculos, pla)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya se encuentra un vehículo registrado con esa placa.");
                mensaje.show();
            }
            else{
                try{
                    Vehiculo m = new Vehiculo(pla, mar, mod, tipmot, Integer.parseInt(año), Double.parseDouble(rec), col, tipcom, Integer.parseInt(pre), this.usuario);
                    guardarImagen(imgFile, pla);
                    imgFile = null;
                    this.vehiculos.add(m);
                    Vehiculo.saveListVehiculosSer(this.vehiculos);
                    for(Usuario usuarioActual : usuarios){
                        if(usuarioActual.getCorreo().equals(usuario.getCorreo())){
                            usuarioActual.getVehiculos().add(m);
                            Usuario.saveListUsuariosSer(usuarios);
                        }
                    }
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "¡Vehículo registrado con éxito!");
                    mensaje.show();
                } catch(NegativeNumberException nn){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos en los campos de: \nprecio, año y recorrido.");
                    mensaje.show();
                } catch(NumberFormatException n){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene los campos de precio, año y recorrido con \nvalores numéricos.");
                    mensaje.show();
                } catch(IOException ioe){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Suba una imagen de su vehículo.");
                    mensaje.show();
                } catch(Exception ex){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error.");
                    mensaje.show();
                }
            } 
        }
    }

    @FXML
    private void registrarCarro(MouseEvent event) {
        String pla = (String) this.placaA.getText();
        String mar = (String) this.marcaA.getText();
        String mod = (String) this.modeloA.getText();
        String col = (String) this.colorA.getText();
        String tipmot = (String) this.tipodemotorA.getText();
        String tipcom = (String) this.tipodecombustibleA.getText();
        String pre = (String) this.precioA.getText();
        String año = (String) this.añoA.getText();
        String rec = (String) this.recorridoA.getText();
        String vid =(String) this.vidriosA.getText();
        String trans = (String)this.transmisionA.getText();        
        
        if(pla.equals("") || mar.equals("") || mod.equals("") || col.equals("") || tipmot.equals("") || tipcom.equals("") || año.equals("") || pre.equals("") || rec.equals("") || vid.equals("") || trans.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene obligatoriamente todos los campos.");
            mensaje.show();
        }
        else{
            if(Vehiculo.checkPlaca(this.vehiculos, pla)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya se encuentra un vehículo registrado con esa placa.");
                mensaje.show();
            }
            else{
                try{
                    Auto a = new Auto(pla, mar, mod, tipmot, Integer.parseInt(año), Double.parseDouble(rec), col, tipcom, Integer.parseInt(pre), this.usuario, Integer.parseInt(vid), trans);
                    guardarImagen(imgFileA, pla);
                    imgFileA = null;
                    this.vehiculos.add(a);
                    Vehiculo.saveListVehiculosSer(this.vehiculos);
                    for(Usuario usuarioActual : usuarios){
                        if(usuarioActual.getCorreo().equals(usuario.getCorreo())){
                            usuarioActual.getVehiculos().add(a);
                            Usuario.saveListUsuariosSer(usuarios);
                        }
                    }
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "¡Vehículo registrado con éxito!");
                    mensaje.show();
                } catch(NegativeNumberException nn){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos en los campos de: \nprecio, año, vidrios y recorrido.");
                    mensaje.show();
                } catch(NumberFormatException n){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene los campos de precio, año, vidrios y recorrido \ncon valores numéricos.");
                    mensaje.show();
                } catch(IOException ioe){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Suba una imagen de su vehículo.");
                    mensaje.show();
                } catch(Exception ex){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error.");
                    mensaje.show();
                }
            } 
        }
    }    

    @FXML
    private void registrarCamioneta(MouseEvent event) {
        String pla = (String) this.placaC.getText();
        String mar = (String) this.marcaC.getText();
        String mod = (String) this.modeloC.getText();
        String col = (String) this.colorC.getText();
        String tipmot = (String) this.tipodemotorC.getText();
        String tipcom = (String) this.tipodecombustibleC.getText();
        String pre = (String) this.precioC.getText();
        String año = (String) this.añoC.getText();
        String rec = (String) this.recorridoC.getText();
        String vid =(String) this.vidriosC.getText();
        String trans = (String)this.transmisionC.getText();        
        String trac = (String)this.tracciónC.getText();
        
        if(pla.equals("") || mar.equals("") || mod.equals("") || col.equals("") || tipmot.equals("") || tipcom.equals("") || año.equals("") || pre.equals("") || rec.equals("") || vid.equals("") || trans.equals("") || trac.equals("")){
            Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene obligatoriamente todos los campos.");
            mensaje.show();
        }
        else{
            if(Vehiculo.checkPlaca(this.vehiculos, pla)){
                Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Ya se encuentra un vehículo registrado con esa placa.");
                mensaje.show();
            }
            else{
                try{
                    Camioneta c = new Camioneta(pla, mar, mod, tipmot, Integer.parseInt(año), Double.parseDouble(rec), col, tipcom, Integer.parseInt(pre), this.usuario, Integer.parseInt(vid), trans, trac);                   
                    guardarImagen(imgFileC, pla);
                    imgFileC = null;
                    this.vehiculos.add(c);
                    Vehiculo.saveListVehiculosSer(this.vehiculos);
                    for(Usuario usuarioActual : usuarios){
                        if(usuarioActual.getCorreo().equals(usuario.getCorreo())){
                            usuarioActual.getVehiculos().add(c);
                            Usuario.saveListUsuariosSer(usuarios);
                        }
                    }
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "¡Vehículo registrado con éxito!");
                    mensaje.show();
                } catch(NegativeNumberException nn){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ingrese valores numéricos válidos en los campos de: \nprecio, año, vidrios y recorrido.");
                    mensaje.show();
                } catch(NumberFormatException n){
                    Alert mensaje = new Alert(Alert.AlertType.INFORMATION, "Rellene los campos de precio, año, vidrios y recorrido \ncon valores numéricos.");
                    mensaje.show();
                } catch(IOException ioe){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Suba una imagen de su vehículo.");
                    mensaje.show();
                } catch(Exception ex){
                    Alert mensaje = new Alert(Alert.AlertType.ERROR, "Ha ocurrido un error.");
                    mensaje.show();
                }
            }
        }
    }

    @FXML
    private void handleImageFile(ActionEvent event) {
        fc.setTitle("Buscar Imagen");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All Images","*.png", "*.jpg"));
        
        imgFile = fc.showOpenDialog(null);
        
        if(imgFile != null)
            ivFile.setImage(new Image(imgFile.toURI().toString()));
    }   

    @FXML
    private void handleImageFileA(MouseEvent event) {
        fc.setTitle("Buscar Imagen");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All Images","*.png", "*.jpg"));
        
        imgFileA = fc.showOpenDialog(null);
        
        if(imgFileA != null)
            ivFileA.setImage(new Image(imgFileA.toURI().toString()));
    }

    @FXML
    private void handleImageFileC(MouseEvent event) {
        fc.setTitle("Buscar Imagen");
        fc.setInitialDirectory(new File(System.getProperty("user.home")));
        fc.getExtensionFilters().add(
                new FileChooser.ExtensionFilter("All Images","*.png", "*.jpg"));
        
        imgFileC = fc.showOpenDialog(null);
        
        if(imgFileC != null)
            ivFileC.setImage(new Image(imgFileC.toURI().toString()));
    }
    
    private void guardarImagen(File imagen, String n) throws IOException{
        String nuevoNombre = n+".png";
        if(imagen != null){
            String rutaProyecto = System.getProperty("user.dir")+"\\src\\main\\resources";
            String rutaCarpetaDestino = rutaProyecto + File.separator + "imagenesVehiculos";
            File destinoCarpeta = new File(rutaCarpetaDestino);
            if(!destinoCarpeta.exists())
                destinoCarpeta.mkdir();

            File destinoArchivo = new File(rutaCarpetaDestino + File.separator + nuevoNombre);
            if(destinoArchivo.exists())
                destinoArchivo.delete();

            Files.copy(imagen.toPath(), destinoArchivo.toPath(), StandardCopyOption.REPLACE_EXISTING);
        } 
        else 
            throw new IOException();
    }
}
   
