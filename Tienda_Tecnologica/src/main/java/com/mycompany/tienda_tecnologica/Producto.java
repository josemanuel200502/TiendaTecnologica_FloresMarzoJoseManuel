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
class Producto {
    private int id;
    private String nombre;
    private double precio;
    private String descripcion;
    private Caracteristicas caracteristicas;
    private String [] imagenes;
    private int inventario;

    public Producto(int id, String nombre, double precio, String descripcion, Caracteristicas caracteristicas, String[] imagenes, int inventario) {
        this.id = id;
        this.nombre = nombre;
        this.precio = precio;
        this.descripcion = descripcion;
        this.caracteristicas = caracteristicas;
        this.imagenes = imagenes;
        this.inventario = inventario;
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

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Caracteristicas getCaracteristicas() {
        return caracteristicas;
    }

    public void setCaracteristicas(Caracteristicas caracteristicas) {
        this.caracteristicas = caracteristicas;
    }

    public String[] getImagenes() {
        return imagenes;
    }

    public void setImagenes(String[] imagenes) {
        this.imagenes = imagenes;
    }



    public int getInventario() {
        return inventario;
    }

    public void setInventario(int inventario) {
        this.inventario = inventario;
    }
    public void reducirInventario(int cantidad) {
        if (cantidad <= inventario) {
            inventario -= cantidad;
        } else {
            System.out.println("Inventario insuficiente.");
        }
    }

    public void aumentarInventario(int cantidad) {
        inventario += cantidad;
    }

    public void mostrarDetalles() {
        System.out.println("Producto: " + nombre + " - Precio: $" + precio);
        System.out.println("Descripción: " + descripcion);
        caracteristicas.mostrarDetalles();
    }

    }

    

