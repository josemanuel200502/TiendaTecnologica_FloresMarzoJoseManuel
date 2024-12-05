/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.tienda_tecnologica;

/**
 *
 * @author Windows
 */
class Caracteristicas {
    private String memoria;
    private String frecuencia;
    private String puertos;

    public Caracteristicas(String memoria, String frecuencia, String puertos) {
        this.memoria = memoria;
        this.frecuencia = frecuencia;
        this.puertos = puertos;
    }

    public String getMemoria() {
        return memoria;
    }

    public void setMemoria(String memoria) {
        this.memoria = memoria;
    }

    public String getFrecuencia() {
        return frecuencia;
    }

    public void setFrecuencia(String frecuencia) {
        this.frecuencia = frecuencia;
    }

    public String getPuertos() {
        return puertos;
    }

    public void setPuertos(String puertos) {
        this.puertos = puertos;
    }

   
     public void mostrarDetalles() {
        System.out.println("Características: ");
        System.out.println("Memoria: " + memoria);
        System.out.println("Frecuencia: " + frecuencia);
        System.out.println("Puertos: " + puertos);
    }
}
    

