/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Modelo;

import java.util.Date;

/**
 *
 * @author carlos
 */
public class EnvioDomicilio {
    String direccionentrega; 
    String nombredestinatario;
    Date fecharegistroenvio; 
    String teléfonocontacto; 
    String estado;

    public EnvioDomicilio() {
    }

    public EnvioDomicilio(String direccionentrega, String nombredestinatario, Date fecharegistroenvio, String teléfonocontacto, String estado) {
        this.direccionentrega = direccionentrega;
        this.nombredestinatario = nombredestinatario;
        this.fecharegistroenvio = fecharegistroenvio;
        this.teléfonocontacto = teléfonocontacto;
        this.estado = estado;
    }

    public String getDireccionentrega() {
        return direccionentrega;
    }

    public void setDireccionentrega(String direccionentrega) {
        this.direccionentrega = direccionentrega;
    }

    public String getNombredestinatario() {
        return nombredestinatario;
    }

    public void setNombredestinatario(String nombredestinatario) {
        this.nombredestinatario = nombredestinatario;
    }

    public Date getFecharegistroenvio() {
        return fecharegistroenvio;
    }

    public void setFecharegistroenvio(Date fecharegistroenvio) {
        this.fecharegistroenvio = fecharegistroenvio;
    }

    public String getTeléfonocontacto() {
        return teléfonocontacto;
    }

    public void setTeléfonocontacto(String teléfonocontacto) {
        this.teléfonocontacto = teléfonocontacto;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    @Override
    public String toString() {
        return "EnvioDomicilio{" + "direccionentrega=" + direccionentrega + ", nombredestinatario=" + nombredestinatario + ", fecharegistroenvio=" + fecharegistroenvio + ", tel\u00e9fonocontacto=" + teléfonocontacto + ", estado=" + estado + '}';
    }
   
}
