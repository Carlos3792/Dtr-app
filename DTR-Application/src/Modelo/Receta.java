/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

/**
 *
 * @author carlos
 */
public class Receta {
    int id;
    String nombrereceta;
    String pasospreparacion;
    //productos asociados

    public Receta() {
    }

    public Receta(int id, String nombrereceta, String pasospreparacion) {
        this.id = id;
        this.nombrereceta = nombrereceta;
        this.pasospreparacion = pasospreparacion;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombrereceta() {
        return nombrereceta;
    }

    public void setNombrereceta(String nombrereceta) {
        this.nombrereceta = nombrereceta;
    }

    public String getPasospreparacion() {
        return pasospreparacion;
    }

    public void setPasospreparacion(String pasospreparacion) {
        this.pasospreparacion = pasospreparacion;
    }

    @Override
    public String toString() {
        return "Receta{" + "id=" + id + ", nombrereceta=" + nombrereceta + ", pasospreparacion=" + pasospreparacion + '}';
    }
     
}
