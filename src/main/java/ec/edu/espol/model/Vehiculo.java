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
public class Vehiculo implements Serializable{
    protected String placa;
    protected String marca;
    protected String modelo;
    protected String tipodemotor;
    protected int año;
    protected double recorrido;
    protected String color;
    protected String tipodecombustible;
    protected int precio;
    protected Usuario usuario;
    protected ArrayList<Oferta> ofertas;

    public Vehiculo(String placa, String marca, String modelo, String tipodemotor, int año, double recorrido, String color, String tipodecombustible, int precio, Usuario usuario) {
        this.placa = placa;
        this.marca = marca;
        this.modelo = modelo;
        this.tipodemotor = tipodemotor;
        this.color = color;
        this.tipodecombustible = tipodecombustible;
        this.usuario = usuario;
        this.ofertas = new ArrayList<>();
        if(año <= 0 || precio <= 0 || recorrido < 0)
            throw new NegativeNumberException();
        else{
            this.año = año;
            this.precio = precio;
            this.recorrido = recorrido;
        }
    }
    
    @Override
    public String toString(){
        return this.placa + "|" + this.marca + "|" + this.modelo + "|" + this.tipodemotor + "|" + this.año + "|" + this.recorrido + "|" + this.color + "|"+ this.tipodecombustible + "|" + this.precio;
    }
    
    public static void saveListVehiculosSer(ArrayList<Vehiculo> vehiculos){
        try(ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream("vehiculos.ser"))){
            out.writeObject(vehiculos);
        } catch(IOException e){}
    }
    
    public static ArrayList<Vehiculo> readFileSer(){
        ArrayList<Vehiculo> vehiculos = new ArrayList<>();
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream("vehiculos.ser"))){
            vehiculos = (ArrayList<Vehiculo>)in.readObject();
        } catch(ClassNotFoundException c){
        } catch(IOException e){}
        return vehiculos;
    }
    
    public static boolean checkPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo v : vehiculos){
            if(v.placa.equals(placa)){
                return true;
            }       
        }
        return false;
    }
    
    public static Vehiculo filtrarPlaca(ArrayList<Vehiculo> vehiculos, String placa){
        for(Vehiculo v : vehiculos){
            if(v.getPlaca().equals(placa))
                return v;
        }
        return null;
    }
    
    public static ArrayList<Vehiculo> filtrarVehiculos(ArrayList<Vehiculo> vehiculos, String tipo, double recMin, double recMax, int añoMin, int añoMax, int preMin, int preMax){
        if(recMin < 0 || recMax < 0 || añoMin < 1 || añoMax < 0 || preMin < 1 || preMax < 0){
            throw new NegativeNumberException();
        }
        else{
            ArrayList<Vehiculo> vehiculosfil = new ArrayList<>();
            String tipoVeh;
            for(Vehiculo v : vehiculos){
                tipoVeh = "Motocicleta";
                if(v instanceof Camioneta)
                    tipoVeh = "Camioneta";
                else if(v instanceof Auto)
                    tipoVeh = "Automóvil";
                if(tipo.equals("") || tipo.equals("Todos") || tipoVeh.equals(tipo)){
                    if(recMin <= v.recorrido && v.recorrido <= recMax){
                        if(añoMin <= v.año && v.año <= añoMax){
                            if(preMin <= v.precio && v.precio <= preMax)
                                vehiculosfil.add(v);
                        }
                    }
                }
            }
            return vehiculosfil;
        }
    }

    public String getPlaca() {
        return placa;
    }

    public void setPlaca(String placa) {
        this.placa = placa;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getTipodemotor() {
        return tipodemotor;
    }

    public void setTipodemotor(String tipodemotor) {
        this.tipodemotor = tipodemotor;
    }

    public int getAño() {
        return año;
    }

    public void setAño(int año) {
        this.año = año;
    }

    public double getRecorrido() {
        return recorrido;
    }

    public void setRecorrido(double recorrido) {
        this.recorrido = recorrido;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getTipodecombustible() {
        return tipodecombustible;
    }

    public void setTipodecombustible(String tipodecombustible) {
        this.tipodecombustible = tipodecombustible;
    }

    public int getPrecio() {
        return precio;
    }

    public void setPrecio(int precio) {
        this.precio = precio;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public ArrayList<Oferta> getOfertas() {
        return ofertas;
    }

    public void setOfertas(ArrayList<Oferta> ofertas) {
        this.ofertas = ofertas;
    }
    
    
}
