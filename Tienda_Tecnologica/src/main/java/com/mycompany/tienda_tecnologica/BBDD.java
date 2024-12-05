/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

/**
 *
 * @author Windows
 */
public class BBDD {
     private static final String URL = "jdbc:sqlite:tienda.db"; // Ruta a la base de datos

    // Método para conectar a la base de datos
    public Connection conectar() throws SQLException {
        return DriverManager.getConnection(URL);
    }

    // Método para crear las tablas
    public void inicializarBaseDatos() {
        try (Connection conn = conectar()) {
            // Crear tabla de productos
            String createTableProductos = "CREATE TABLE IF NOT EXISTS productos ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre TEXT NOT NULL, "
                    + "precio REAL NOT NULL)";
            Statement stmt = conn.createStatement();
            stmt.execute(createTableProductos);
            System.out.println("Tabla 'productos' creada correctamente.");

            // Crear tabla de categorías
            String createTableCategorias = "CREATE TABLE IF NOT EXISTS categorias ("
                    + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                    + "nombre TEXT NOT NULL)";
            stmt.execute(createTableCategorias);
            System.out.println("Tabla 'categorias' creada correctamente.");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un producto
    public void insertarProducto(String nombre, double precio) {
        String sql = "INSERT INTO productos (nombre, precio) VALUES (?, ?)";

        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.setDouble(2, precio);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar una categoría
    public void insertarCategoria(String nombre) {
        String sql = "INSERT INTO categorias (nombre) VALUES (?)";

        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombre);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar los productos
    public void mostrarProductos() {
        String sql = "SELECT * FROM productos";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre") + " | Precio: $" + rs.getDouble("precio"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar las categorías
    public void mostrarCategorias() {
        String sql = "SELECT * FROM categorias";

        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rs = stmt.executeQuery(sql)) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
}
