
package com.universidad.test;

import com.universidad.dao.PacienteDAO;
import com.universidad.model.Paciente;
import com.universidad.model.Prioridad;

public class PruebaInsertar {

    public static void main(String[] args) {

        Paciente paciente = new Paciente(
                "Juan Perez",
                30,
                "1234567890101",
                "Dolor abdominal",
                Prioridad.HIGH
        );

        PacienteDAO dao = new PacienteDAO();

        if (dao.guardarPaciente(paciente)) {
            System.out.println("Paciente guardado correctamente");
        } else {
            System.out.println("Error al guardar paciente");
        }
    }
}