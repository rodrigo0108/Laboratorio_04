package com.rramos.laboratorio_04;

/**
 * Created by RODRIGO on 11/09/2017.
 */

public class Operacion {

    private double monto;
    private String tipo;
    private String tipo_cuenta;


    public Operacion() {
    }

    public Operacion(double monto, String tipo, String tipo_cuenta) {
        this.monto = monto;
        this.tipo = tipo;
        this.tipo_cuenta = tipo_cuenta;
    }

    public double getMonto() {
        return monto;
    }

    public void setMonto(double monto) {
        this.monto = monto;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public String getTipo_cuenta() {
        return tipo_cuenta;
    }

    public void setTipo_cuenta(String tipo_cuenta) {
        this.tipo_cuenta = tipo_cuenta;
    }

    @Override
    public String toString() {
        return "Operacion{" +
                "monto=" + monto +
                ", tipo='" + tipo + '\'' +
                ", tipo_cuenta='" + tipo_cuenta + '\'' +
                '}';
    }
}
