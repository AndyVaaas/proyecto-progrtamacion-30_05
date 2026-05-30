/**
 * codigo para la consulta y actualizacion de los pacientes
 */

package com.universidad.dao;

import com.universidad.model.Paciente;
import com.universidad.model.Prioridad;
import com.universidad.persistencia.ConexionDB;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;
import java.util.List;

public class PacienteDAO {

    public boolean guardarPaciente(Paciente paciente) {

        String sql = """
                INSERT INTO pacientes
                (nombre, edad, dpi, sintomas, prioridad)
                VALUES (?, ?, ?, ?, ?)
                """;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql)) {

            ps.setString(1, paciente.getNombre());
            ps.setInt(2, paciente.getEdad());
            ps.setString(3, paciente.getDpi());
            ps.setString(4, paciente.getSintomas());
            ps.setString(5, paciente.getPrioridad().name());

            ps.executeUpdate();

            return true;

        } catch (SQLException e) {

            e.printStackTrace();
            return false;
        }
    }

    public List<Paciente> listarPacientes() {

        List<Paciente> pacientes = new ArrayList<>();

        String sql = """
                SELECT *
                FROM pacientes
                WHERE atendido = FALSE
                ORDER BY
                CASE prioridad
                    WHEN 'CRITICAL' THEN 4
                    WHEN 'HIGH' THEN 3
                    WHEN 'MEDIUM' THEN 2
                    WHEN 'LOW' THEN 1
                END DESC,
                fecha_ingreso ASC
                """;

        try (Connection conn = ConexionDB.conectar();
             PreparedStatement ps = conn.prepareStatement(sql);
             ResultSet rs = ps.executeQuery()) {

            while (rs.next()) {

                Paciente paciente = new Paciente();

                paciente.setId(rs.getInt("id"));
                paciente.setNombre(rs.getString("nombre"));
                paciente.setEdad(rs.getInt("edad"));
                paciente.setDpi(rs.getString("dpi"));
                paciente.setSintomas(rs.getString("sintomas"));

                paciente.setPrioridad(
                        Prioridad.valueOf(rs.getString("prioridad"))
                );

                pacientes.add(paciente);
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return pacientes;
    }

    public boolean atenderSiguientePaciente() {

        String buscar = """
                SELECT id
                FROM pacientes
                WHERE atendido = FALSE
                ORDER BY
                CASE prioridad
                    WHEN 'CRITICAL' THEN 4
                    WHEN 'HIGH' THEN 3
                    WHEN 'MEDIUM' THEN 2
                    WHEN 'LOW' THEN 1
                END DESC,
                fecha_ingreso ASC
                LIMIT 1
                """;

        String actualizar = """
                UPDATE pacientes
                SET atendido = TRUE
                WHERE id = ?
                """;

        try (Connection conn = ConexionDB.conectar()) {

            PreparedStatement psBuscar =
                    conn.prepareStatement(buscar);

            ResultSet rs = psBuscar.executeQuery();

            if (rs.next()) {

                int idPaciente = rs.getInt("id");

                PreparedStatement psActualizar =
                        conn.prepareStatement(actualizar);

                psActualizar.setInt(1, idPaciente);

                psActualizar.executeUpdate();

                return true;
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }
}