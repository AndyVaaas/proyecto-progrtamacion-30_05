/**
 *creacion del paciente.
 * almacenamiento de los datos de los pacientes la ingresar
 */

package com.universidad.model;

import java.time.LocalDateTime;

public class Paciente {

    private int id;
    private String nombre;
    private int edad;
    private String dpi;
    private String sintomas;
    private Prioridad prioridad;
    private LocalDateTime fechaIngreso;

    public Paciente() {
    }

    public Paciente(String nombre, int edad, String dpi,
                    String sintomas, Prioridad prioridad) {

        this.nombre = nombre;
        this.edad = edad;
        this.dpi = dpi;
        this.sintomas = sintomas;
        this.prioridad = prioridad;
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

    public int getEdad() {
        return edad;
    }

    public void setEdad(int edad) {
        this.edad = edad;
    }

    public String getDpi() {
        return dpi;
    }

    public void setDpi(String dpi) {
        this.dpi = dpi;
    }

    public String getSintomas() {
        return sintomas;
    }

    public void setSintomas(String sintomas) {
        this.sintomas = sintomas;
    }

    public Prioridad getPrioridad() {
        return prioridad;
    }

    public void setPrioridad(Prioridad prioridad) {
        this.prioridad = prioridad;
    }

    public LocalDateTime getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDateTime fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }
}