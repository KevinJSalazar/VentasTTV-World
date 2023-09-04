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
public class Auto extends Vehiculo{
    private  int vidrios;
    private String trasnmision;
    
    public Auto(String placa, String marca, String modelo, String tipodemotor, int año, double recorrido, String color, String tipodecombustible, int precio, Usuario usuario, int vidrios, String trasnmision) {
        super(placa, marca, modelo, tipodemotor, año, recorrido, color, tipodecombustible, precio, usuario);
        this.trasnmision=trasnmision;
        if(vidrios < 0)
            throw new NegativeNumberException();
        else
            this.vidrios = vidrios;
    }
    @Override
    public String toString(){
        return super.toString() + "|" + this.vidrios + "|" + this.trasnmision;
    }
}