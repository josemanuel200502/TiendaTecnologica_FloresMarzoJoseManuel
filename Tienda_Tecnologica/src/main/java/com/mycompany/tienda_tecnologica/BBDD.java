/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Windows
 */
public class BBDD {
    private static final String DB_URL = "jdbc:sqlite:tienda.db";

    // Método para inicializar la base de datos y crear tablas
    public void inicializarBaseDatos() {
        try (Connection conn = DriverManager.getConnection(DB_URL);
             Statement stmt = conn.createStatement()) {
            // Crear tabla de productos
            String crearTablaProductos = "CREATE TABLE IF NOT EXISTS productos (" +
                                         "id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                                         "nombre TEXT NOT NULL, " +
                                         "precio REAL NOT NULL)";
            stmt.execute(crearTablaProductos);
            System.out.println("Tabla 'productos' creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar productos
    public void insertarProducto(String nombre, double precio) {
        String insertarSQL = "INSERT INTO productos(nombre, precio) VALUES(?, ?)";
        try (Connection conn = DriverManager.getConnection(DB_URL);
             PreparedStatement pstmt = conn.prepareStatement(insertarSQL)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.executeUpdate();
            System.out.println("Producto insertado: " + nombre);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}

