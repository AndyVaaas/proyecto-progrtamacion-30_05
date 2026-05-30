
package com.universidad.test;

import com.universidad.model.Paciente;
import com.universidad.model.Prioridad;
import com.universidad.service.ColaPrioridadService;

public class PruebaColaPrioridad {

    public static void main(String[] args) {

        ColaPrioridadService cola = new ColaPrioridadService();

        cola.agregarPaciente(
                new Paciente("Ana", 20, "1", "Dolor", Prioridad.LOW));

        cola.agregarPaciente(
                new Paciente("Luis", 30, "2", "Fractura", Prioridad.HIGH));

        cola.agregarPaciente(
                new Paciente("Pedro", 40, "3", "Paro cardiaco", Prioridad.CRITICAL));

        cola.agregarPaciente(
                new Paciente("Maria", 25, "4", "Fiebre", Prioridad.MEDIUM));

        while (!cola.estaVacia()) {

            Paciente p = cola.atenderSiguiente();

            System.out.println(
                    p.getNombre() + " - " + p.getPrioridad()
            );
        }
    }
}