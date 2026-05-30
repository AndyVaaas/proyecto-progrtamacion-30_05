
package com.universidad.test;

import com.universidad.dao.PacienteDAO;
import com.universidad.model.Paciente;

public class PruebaListar {

    public static void main(String[] args) {

        PacienteDAO dao = new PacienteDAO();

        for (Paciente p : dao.listarPacientes()) {

            System.out.println(
                    p.getId() + " - "
                    + p.getNombre() + " - "
                    + p.getPrioridad()
            );
        }
    }
}