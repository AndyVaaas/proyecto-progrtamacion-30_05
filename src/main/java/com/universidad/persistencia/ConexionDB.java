/**
 * establece la conexion de a la base de xamp
 */

package com.universidad.persistencia;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConexionDB {

    private static final String URL =
            "jdbc:mysql://localhost:3306/hospital_emergencias";

    private static final String USER = "root";

    private static final String PASSWORD = "";

    public static Connection conectar() {

        try {

            Connection conexion =
                    DriverManager.getConnection(URL, USER, PASSWORD);

            System.out.println("Conexión exitosa");

            return conexion;

        } catch (SQLException e) {

            System.out.println("Error de conexión");
            e.printStackTrace();

            return null;
        }
    }
}