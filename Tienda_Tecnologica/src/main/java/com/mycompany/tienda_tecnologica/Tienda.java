/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Windows
 */
public class Tienda {
    private String nombre;
    private List<Categoria> categorias;
    private List<Usuario> usuarios;

    public Tienda(String nombre, List categorias,List usuarios) {
        this.nombre = nombre;
        this.categorias = new ArrayList<>();
        this.usuarios = new ArrayList<>();
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public List<Categoria> getCategorias() {
        return categorias;
    }

    public void setCategorias(List<Categoria> categorias) {
        this.categorias = categorias;
    }

    public List<Usuario> getUsuarios() {
        return usuarios;
    }

    public void setUsuarios(List<Usuario> usuarios) {
        this.usuarios = usuarios;
    }

    
    public void agregarCategoria(Categoria categoria){
        categorias.add(categoria);
    }
    
    public void listarCategorias(){
        categorias.forEach(Categoria::mostrarDetalles);
    }
    
    public void agregarUsuarios(Usuario usuario){
        usuarios.add(usuario);
    }
    
    public void listarUsuarios(){
        usuarios.forEach(Usuario::mostrarDetalles);
    }
    
    public Producto buscarProductoPorId(int id){
        for (Categoria categoria: categorias){
            Producto producto= categoria.buscarProductoPorId(id);
            if(producto!=null){
                return producto;
            }
        }
        return null;
    }
    
    public Usuario buscarUsuarioPorId(int id){
        return usuarios.stream().filter(u-> u.getId()==id).findFirst().orElse(null);
    }
      public void mostrarDetalles() {
        System.out.println("Tienda: " + nombre);
        listarCategorias();
        listarUsuarios();
    }
    
}
