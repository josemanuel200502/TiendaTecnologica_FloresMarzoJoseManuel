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

public class Main {
    public static void main(String[] args) {
        
        //Instanciamos la base de datos
        BBDD basededatos= new BBDD();
        
        
        // Inicializar la base de datos
        basededatos.inicializarBaseDatos();
        
        try {
            String contenido = new String(Files.readAllBytes(Paths.get("src/main/java/com/mycompany/tienda_tecnologica/Tienda.JSON")));

            // Convertir el String en un JSONObject
            JSONObject jsonObj = new JSONObject(contenido);

            // Obtener el array de categorías
            JSONArray categorias = jsonObj.getJSONArray("categorias");

            // Recorrer las categorías y sus productos
            for (int i = 0; i < categorias.length(); i++) {
                JSONObject categoria = categorias.getJSONObject(i);
                String nombreCategoria = categoria.getString("nombre");
                System.out.println("Categoría: " + nombreCategoria);

                JSONArray productos = categoria.getJSONArray("productos");
                for (int j = 0; j < productos.length(); j++) {
                    JSONObject producto = productos.getJSONObject(j);
                    String nombreProducto = producto.getString("nombre");
                    double precio = producto.getDouble("precio");
                    System.out.println("\tProducto: " + nombreProducto + " - Precio: $" + precio);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

