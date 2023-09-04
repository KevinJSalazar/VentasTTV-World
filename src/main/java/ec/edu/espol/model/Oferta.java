/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;

/**
 *
 * @author User A1
 */
public class Oferta implements Serializable{
    private int valor;
    private Usuario usuario;
    private Vehiculo vehiculo;

    public Oferta(int valor, Usuario usuario, Vehiculo vehiculo) {
        this.valor = valor;
        this.usuario = usuario;
        this.vehiculo = vehiculo;
    }
    
    @Override
    public String toString(){
        return this.valor + "|" + this.usuario.getCorreo() + "|" + this.vehiculo.getPlaca();
    }
    
    public static void saveListOfertaSer(ArrayList<Oferta> ofertas){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("ofertas.ser"))){
            out.writeObject(ofertas);
        } catch(IOException e){}
    }
    
    public static ArrayList<Oferta> readFileSer(){
        ArrayList<Oferta> ofertas = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("ofertas.ser"))){
            ofertas = (ArrayList<Oferta>)in.readObject();
        } catch(ClassNotFoundException c){
        } catch(IOException e){}
        return ofertas;
    }
    
    public static boolean checkOferta(ArrayList<Oferta> ofertas, String correo, String placa, int valor){
        for(Oferta o : ofertas){
            if(o.getUsuario().getCorreo().equals(correo) && o.getValor()== valor && o.getVehiculo().getPlaca().equals(placa)){
                return true;
            }
        }
        return false;
    }
    
    public static Oferta filtrarOferta(ArrayList<Oferta> ofertas, String correo, String placa, int valor){
        for(Oferta o : ofertas){
            if(o.getUsuario().getCorreo().equals(correo) && o.getValor()== valor && o.getVehiculo().getPlaca().equals(placa)){
                return o;
            }
        }
        return null;
    }
    
    public static boolean checkOfertante(ArrayList<Oferta> ofertas, String correo){
        for(Oferta o : ofertas){
            if(o.getUsuario().getCorreo().equals(correo)){
                return true;
            }
        }
        return false;
    }

    public int getValor() {
        return valor;
    }

    public void setValor(int valor) {
        this.valor = valor;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Vehiculo getVehiculo() {
        return vehiculo;
    }

    public void setVehiculo(Vehiculo vehiculo) {
        this.vehiculo = vehiculo;
    }
}
