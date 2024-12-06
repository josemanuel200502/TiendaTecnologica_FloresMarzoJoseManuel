/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.nio.charset.StandardCharsets;
import org.json.JSONArray;
import org.json.JSONObject;

import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Scanner;
import org.json.JSONException;

public class Main {
    public static void main(String[] args) {
        
        //Instanciamos la base de datos
        BBDD basededatos= new BBDD();
        
        
        // Inicializar la base de datos
        basededatos.inicializarBaseDatos();
        
          try {
            // Cargar archivo JSON desde resources
            InputStream inputStream = Main.class.getResourceAsStream("/TiendaFinal.JSON");
            if (inputStream == null) {
                throw new IllegalArgumentException("Archivo Tienda.JSON no encontrado en resources.");
            }

            // Leer el contenido del archivo JSON
            String contenido;
            try (Scanner scanner = new Scanner(inputStream, StandardCharsets.UTF_8.name())) {
                contenido = scanner.useDelimiter("\\A").next();
            }

            // Imprimir contenido para verificar
            System.out.println("Contenido del archivo: " + contenido);

            // Convertir contenido a objeto JSON
            JSONObject jsonObj = new JSONObject(contenido);

              // Verificar si la clave "tienda" existe antes de acceder
            if (jsonObj.has("tienda")) {
                JSONObject tienda = jsonObj.getJSONObject("tienda");

                // Verificar si la clave "categorias" existe antes de acceder
                if (tienda.has("categorias")) {
                    JSONArray categorias = tienda.getJSONArray("categorias");

                    // Procesar categorías y productos
                    for (int i = 0; i < categorias.length(); i++) {
                        JSONObject categoria = categorias.getJSONObject(i);

                        // Verificar si la clave "nombre" existe en la categoría
                        if (categoria.has("nombre")) {
                            String nombreCategoria = categoria.getString("nombre");
                            System.out.println("Categoría: " + nombreCategoria);

                            // Verificar si "productos" existe
                            if (categoria.has("productos")) {
                                JSONArray productos = categoria.getJSONArray("productos");

                                for (int j = 0; j < productos.length(); j++) {
                                    JSONObject producto = productos.getJSONObject(j);

                                    // Verificar si la clave "nombre" existe en el producto
                                    if (producto.has("nombre") && producto.has("precio")) {
                                        String nombreProducto = producto.getString("nombre");
                                        double precio = producto.getDouble("precio");
                                        System.out.println("\tProducto: " + nombreProducto + " - Precio: $" + precio);
                                    } else {
                                        System.out.println("Producto con datos incompletos.");
                                    }
                                }
                            } else {
                                System.out.println("No se encontraron productos para la categoría: " + nombreCategoria);
                            }
                        } else {
                            System.out.println("Categoría sin nombre.");
                        }
                    }
                } else {
                    System.out.println("No se encontraron categorías en 'tienda'.");
                }
            } else {
                System.out.println("No se encontró la sección 'tienda' en el JSON.");
            }

            // Verificar si la clave "usuarios" existe antes de acceder
            if (jsonObj.has("usuarios")) {
                JSONArray usuarios = jsonObj.getJSONArray("usuarios");

                // Procesar usuarios
                for (int i = 0; i < usuarios.length(); i++) {
                    JSONObject usuario = usuarios.getJSONObject(i);

                    // Verificar si la clave "nombre" y "email" existen
                    if (usuario.has("nombre") && usuario.has("email")) {
                        String nombreUsuario = usuario.getString("nombre");
                        String emailUsuario = usuario.getString("email");
                        System.out.println("Usuario: " + nombreUsuario + " - Email: " + emailUsuario);

                        // Procesar historial de compras
                        if (usuario.has("historialCompras")) {
                            JSONArray historial = usuario.getJSONArray("historialCompras");

                            for (int j = 0; j < historial.length(); j++) {
                                JSONObject compra = historial.getJSONObject(j);
                                int productoId = compra.getInt("productoId");
                                int cantidad = compra.getInt("cantidad");
                                String fecha = compra.getString("fecha");
                                System.out.println("\tCompra: Producto ID " + productoId + ", Cantidad: " + cantidad + ", Fecha: " + fecha);
                            }
                        }
                    }
                }
            } else {
                System.out.println("No se encontró la sección 'usuarios' en el JSON.");
            }

            // Instanciamos la interfaz y la hacemos visible
            InicioInt inicio = new InicioInt(basededatos, jsonObj);  // Pasamos los objetos necesarios
            inicio.setVisible(true);  // Mostramos la interfaz gráfica

        } catch (JSONException e) {
            System.err.println("Error al procesar el JSON: " + e.getMessage());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}