/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

/**
 *
 * @author Windows
 */
class HistorialCompra {
    private int productoId;
    private int cantidad;
    private String fecha;

    public HistorialCompra(int productoId, int cantidad, String fecha) {
        this.productoId = productoId;
        this.cantidad = cantidad;
        this.fecha = fecha;
    }

    public int getProductoId() {
        return productoId;
    }

    public void setProductoId(int productoId) {
        this.productoId = productoId;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
    
      public void mostrarDetalles() {
        System.out.println("Compra - Producto ID: " + productoId + ", Cantidad: " + cantidad + ", Fecha: " + fecha);
    }
}
    

