/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

/**
 *
 * @author evin
 */
 public class Usuario implements Serializable{
    private String nombres;
    private String apellidos;
    private String organizacion;
    private String correo;
    private String clave;
    private ArrayList<Oferta> ofertas;
    private ArrayList<Vehiculo> vehiculos;
    
    public Usuario(String nombres, String apellidos, String organizacion, String correo, String clave) {
        this.nombres = nombres;
        this.apellidos = apellidos;
        this.organizacion = organizacion;
        this.correo = correo;
        this.clave = clave;
        this.ofertas = new ArrayList<>();
        this.vehiculos = new ArrayList<>();
    }
    
    @Override
    public String toString(){
        return this.correo + "|" + this.clave;
    }
    
    public static void saveListUsuariosSer(ArrayList<Usuario> usuarios){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("usuarios.ser"))){
            out.writeObject(usuarios);
        } catch(IOException e){}
    }
    
    public static ArrayList<Usuario> readFileSer(){
        ArrayList<Usuario> usuarios = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("usuarios.ser"))){
            usuarios = (ArrayList<Usuario>)in.readObject();
        } catch(ClassNotFoundException c){
        } catch(IOException e){}
        return usuarios;
    }
    
    public static boolean checkCorreo(ArrayList<Usuario> usuarios, String correo){
        for(Usuario u : usuarios){
            if(u.correo.equals(correo)){
                return true;
            }
        }
        return false;
    }
    
    public static boolean checkCuenta(ArrayList<Usuario> usuarios, String correo, String clave){
        for(Usuario u : usuarios){
            if(u.correo.equals(correo) && u.clave.equals(clave)){
                return true;
            }
        }
        return false;
    }
    
    public static Usuario filtrarCorreo(ArrayList<Usuario> usuarios, String correo){
        for(Usuario u : usuarios){
            if(u.correo.equals(correo))
                return u;
        }
        return null;
    }
    
    public ArrayList<String> getPlacas(){
        ArrayList<String> placas = new ArrayList<>();
        for(Vehiculo v : this.getVehiculos())
            placas.add(v.getPlaca());
        return placas;
    }
//    
//    public ArrayList<String> getPlacas(Usuario usuario){
//    ArrayList<String> placas = new ArrayList<>();
//    for(Vehiculo v : Vehiculo.readFileSer()){
//        if(v.getUsuario().correo.equals(usuario.getCorreo()))
//            
//      
//    }
//    return placas;
//    }

    public String getNombres() {
        return nombres;
    }

    public void setNombres(String nombres) {
        this.nombres = nombres;
    }

    public String getApellidos() {
        return apellidos;
    }

    public void setApellidos(String apellidos) {
        this.apellidos = apellidos;
    }

    public String getOrganizacion() {
        return organizacion;
    }

    public void setOrganizacion(String organizacion) {
        this.organizacion = organizacion;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getClave() {
        return clave;
    }

    public void setClave(String clave) {
        this.clave = clave;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }

    public ArrayList<Vehiculo> getVehiculos() {
        return vehiculos;
    }

    public void setVehiculos(ArrayList<Vehiculo> vehiculos) {
        this.vehiculos = vehiculos;
    }
    
}
