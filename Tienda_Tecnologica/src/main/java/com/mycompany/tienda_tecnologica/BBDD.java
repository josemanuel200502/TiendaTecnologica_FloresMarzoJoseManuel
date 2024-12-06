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
import java.util.ArrayList;
import java.util.List;

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
             Statement stmt = conn.createStatement();

        // Crear tabla de productos
        String createTableProductos = "CREATE TABLE IF NOT EXISTS productos ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT NOT NULL, "
                + "precio REAL NOT NULL)";
        stmt.execute(createTableProductos);
        System.out.println("Tabla 'productos' creada correctamente.");

        // Crear tabla de categorías
        String createTableCategorias = "CREATE TABLE IF NOT EXISTS categorias ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "nombre TEXT NOT NULL)";
        stmt.execute(createTableCategorias);
        System.out.println("Tabla 'categorias' creada correctamente.");

        // Crear tabla de historial de compras
        String createTableHistorialCompras = "CREATE TABLE IF NOT EXISTS historial_compras ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idUsuario INTEGER, "
                + "fechaCompra DATE NOT NULL, "
                + "totalPagado REAL NOT NULL, "
                + "FOREIGN KEY (idUsuario) REFERENCES usuarios(id))";
        stmt.execute(createTableHistorialCompras);
        System.out.println("Tabla 'historial_compras' creada correctamente.");

        // Crear tabla de detalles de compra
        String createTableDetallesCompra = "CREATE TABLE IF NOT EXISTS detalles_compra ("
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                + "idCompra INTEGER, "
                + "idProducto INTEGER, "
                + "cantidad INTEGER, "
                + "FOREIGN KEY (idCompra) REFERENCES historial_compras(id), "
                + "FOREIGN KEY (idProducto) REFERENCES productos(id))";
        stmt.execute(createTableDetallesCompra);
        System.out.println("Tabla 'detalles_compra' creada correctamente.");
        
        
        // Crear tabla de usuarios
        String createTableUsuarios = "CREATE TABLE IF NOT EXISTS usuarios ("
            + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
            +"nombre TEXT NOT NULL, "
            + "correo TEXT NOT NULL, "
            + "direccion TEXT)";
            stmt.execute(createTableUsuarios);
            System.out.println("Tabla 'Usuarios' creada correctamente.");

    } catch (SQLException e) {
        e.printStackTrace();
    }
}

    
    public void registrarCompra(int idUsuario, double totalPagado, List<Integer> productosComprados, List<Integer> cantidades) {
    String insertCompra = "INSERT INTO historial_compras (idUsuario, fechaCompra, totalPagado) VALUES (?, CURRENT_DATE, ?)";
    try (Connection conn = conectar(); PreparedStatement pstmtCompra = conn.prepareStatement(insertCompra, Statement.RETURN_GENERATED_KEYS)) {
        pstmtCompra.setInt(1, idUsuario);
        pstmtCompra.setDouble(2, totalPagado);
        pstmtCompra.executeUpdate();

        ResultSet generatedKeys = pstmtCompra.getGeneratedKeys();
        if (generatedKeys.next()) {
            int idCompra = generatedKeys.getInt(1); // ID de la compra recién insertada

            // Insertamos los detalles de la compra
            String insertDetalle = "INSERT INTO detalles_compra (idCompra, idProducto, cantidad) VALUES (?, ?, ?)";
            try (PreparedStatement pstmtDetalle = conn.prepareStatement(insertDetalle)) {
                for (int i = 0; i < productosComprados.size(); i++) {
                    pstmtDetalle.setInt(1, idCompra);
                    pstmtDetalle.setInt(2, productosComprados.get(i));
                    pstmtDetalle.setInt(3, cantidades.get(i));
                    pstmtDetalle.executeUpdate();
                }
            }
        }

    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    public void mostrarHistorialCompras(int idUsuario) {
    String sql = "SELECT * FROM historial_compras WHERE idUsuario = ?";
    try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idUsuario);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            int idCompra = rs.getInt("id");
            String fechaCompra = rs.getString("fechaCompra");
            double totalPagado = rs.getDouble("totalPagado");
            System.out.println("Compra ID: " + idCompra + " | Fecha: " + fechaCompra + " | Total Pagado: $" + totalPagado);

            // Ahora, mostramos los detalles de la compra
            mostrarDetallesCompra(idCompra);
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}
    
    

public void mostrarDetallesCompra(int idCompra) {
    String sql = "SELECT p.nombre, dc.cantidad, p.precio FROM detalles_compra dc "
               + "JOIN productos p ON dc.idProducto = p.id WHERE dc.idCompra = ?";
    try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idCompra);
        ResultSet rs = pstmt.executeQuery();

        while (rs.next()) {
            String nombreProducto = rs.getString("nombre");
            int cantidad = rs.getInt("cantidad");
            double precio = rs.getDouble("precio");
            System.out.println("Producto: " + nombreProducto + " | Cantidad: " + cantidad + " | Precio: $" + precio);
        }
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
    
    public void mostrarProductosPorCategoria(int idCategoria) {
    String sql = "SELECT * FROM productos WHERE idCategoria = ?";
    try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idCategoria);
        ResultSet rs = pstmt.executeQuery();
        while (rs.next()) {
            System.out.println("ID: " + rs.getInt("id") + " | Nombre: " + rs.getString("nombre") + " | Precio: $" + rs.getDouble("precio"));
        }
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
    
    // Método para mostrar los datos del usuario
public void mostrarDatosUsuario(int idUsuario) {
    String sql = "SELECT * FROM usuarios WHERE id = ?";
    try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
        pstmt.setInt(1, idUsuario);
        ResultSet rs = pstmt.executeQuery();
        if (rs.next()) {
            System.out.println("Nombre: " + rs.getString("nombre"));
            System.out.println("Correo: " + rs.getString("correo"));
            System.out.println("Dirección: " + rs.getString("direccion"));
        }
    } catch (SQLException e) {
        e.printStackTrace();
    }
}


    public List<Categoria> getCategorias() {
        List<Categoria> categorias = new ArrayList<>();
        String sqlCategorias = "SELECT * FROM categorias";
    
        try (Connection conn = conectar(); Statement stmt = conn.createStatement(); ResultSet rsCategorias = stmt.executeQuery(sqlCategorias)) {
            while (rsCategorias.next()) {
                int idCategoria = rsCategorias.getInt("id");
                String nombreCategoria = rsCategorias.getString("nombre");

            List<Producto> productos = getProductosPorCategoria(nombreCategoria);

            Categoria categoria = new Categoria(idCategoria, nombreCategoria, productos);
            categorias.add(categoria);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    
        return categorias;
      }

    // Método para obtener productos por nombre de categoría
    public List<Producto> getProductosPorCategoria(String nombreCategoria) {
        String sql = "SELECT p.id, p.nombre, p.precio, p.descripcion, p.inventario " +
                     "FROM productos p " +
                     "JOIN categorias c ON p.idCategoria = c.id " +
                     "WHERE c.nombre = ?";
        List<Producto> productos = new ArrayList<>();

        try (Connection conn = conectar(); PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, nombreCategoria);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                Producto producto = new Producto(
                    rs.getInt("id"),
                    rs.getString("nombre"),
                    rs.getDouble("precio"),
                    rs.getString("descripcion"),
                    rs.getInt("inventario")
                );
                productos.add(producto);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return productos;
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
