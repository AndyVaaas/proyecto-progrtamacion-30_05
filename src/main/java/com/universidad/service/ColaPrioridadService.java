
package com.universidad.service;

import com.universidad.model.Paciente;

import java.util.Comparator;
import java.util.PriorityQueue;

public class ColaPrioridadService {

    private final PriorityQueue<Paciente> cola;

    public ColaPrioridadService() {

        cola = new PriorityQueue<>(
                Comparator.comparingInt(
                        (Paciente p) -> p.getPrioridad().getValor()
                ).reversed()
        );
    }

    public void agregarPaciente(Paciente paciente) {
        cola.add(paciente);
    }

    public Paciente atenderSiguiente() {
        return cola.poll();
    }

    public boolean estaVacia() {
        return cola.isEmpty();
    }

    public int cantidadPacientes() {
        return cola.size();
    }
}