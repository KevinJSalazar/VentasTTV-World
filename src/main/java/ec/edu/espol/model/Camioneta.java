/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package ec.edu.espol.model;

/**
 *
 * @author User A1
 */
public class Camioneta extends Auto{
    private String traccion;

    public Camioneta(String placa, String marca, String modelo, String tipodemotor, int año, double recorrido, String color, String tipodecombustible, int precio, Usuario usuario, int vidrios, String trasnmision, String traccion) {
        super(placa, marca, modelo, tipodemotor, año, recorrido, color, tipodecombustible, precio, usuario, vidrios, trasnmision);
        this.traccion=traccion;
    }
    
    @Override
    public String toString(){
        return super.toString() + "|" + this.traccion;
    }
}
