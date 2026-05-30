
package com.universidad.test;

import com.universidad.persistencia.ConexionDB;
import java.sql.Connection;

public class PruebaConexion {

    public static void main(String[] args) {

        Connection c = ConexionDB.conectar();

        if (c != null) {

            System.out.println("Base de datos conectada correctamente");

        } else {

            System.out.println("No se pudo conectar");
        }
    }
}