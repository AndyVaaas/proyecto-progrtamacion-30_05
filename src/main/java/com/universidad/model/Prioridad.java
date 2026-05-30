
package com.universidad.model;

public enum Prioridad {

    CRITICAL(4),
    HIGH(3),
    MEDIUM(2),
    LOW(1);

    private final int valor;

    Prioridad(int valor) {
        this.valor = valor;
    }

    public int getValor() {
        return valor;
    }
}