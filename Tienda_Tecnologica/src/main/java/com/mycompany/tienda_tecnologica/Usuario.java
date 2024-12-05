/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

import java.util.List;

/**
 *
 * @author Windows
 */
class Usuario {
    private int id;
    private String nombre;
    private String email;
    private Direccion direccion;
    private List<HistorialCompra> historialCompras;

    public Usuario(int id, String nombre, String email, Direccion direccion, List<HistorialCompra> historialCompras) {
        this.id = id;
        this.nombre = nombre;
        this.email = email;
        this.direccion = direccion;
        this.historialCompras = historialCompras;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Direccion getDireccion() {
        return direccion;
    }

    public void setDireccion(Direccion direccion) {
        this.direccion = direccion;
    }

    public List<HistorialCompra> getHistorialCompras() {
        return historialCompras;
    }

    public void setHistorialCompras(List<HistorialCompra> historialCompras) {
        this.historialCompras = historialCompras;
    }
    
    
    
}
