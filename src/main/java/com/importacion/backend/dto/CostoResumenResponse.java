package com.importacion.backend.dto;

public class CostoResumenResponse {

    private double total;
    private double totalAereo;
    private double totalMaritimo;
    private double totalBodegaExtranjero;
    private double totalGastosPuerto;
    private double totalImpuestosAduana;
    private double totalServicioBancario;
    private double totalPagoImpuestos;
    private double totalTrasladoCarga;

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public double getTotalAereo() {
        return totalAereo;
    }

    public void setTotalAereo(double totalAereo) {
        this.totalAereo = totalAereo;
    }

    public double getTotalMaritimo() {
        return totalMaritimo;
    }

    public void setTotalMaritimo(double totalMaritimo) {
        this.totalMaritimo = totalMaritimo;
    }

    public double getTotalBodegaExtranjero() {
        return totalBodegaExtranjero;
    }

    public void setTotalBodegaExtranjero(double totalBodegaExtranjero) {
        this.totalBodegaExtranjero = totalBodegaExtranjero;
    }

    public double getTotalGastosPuerto() {
        return totalGastosPuerto;
    }

    public void setTotalGastosPuerto(double totalGastosPuerto) {
        this.totalGastosPuerto = totalGastosPuerto;
    }

    public double getTotalImpuestosAduana() {
        return totalImpuestosAduana;
    }

    public void setTotalImpuestosAduana(double totalImpuestosAduana) {
        this.totalImpuestosAduana = totalImpuestosAduana;
    }

    public double getTotalServicioBancario() {
        return totalServicioBancario;
    }

    public void setTotalServicioBancario(double totalServicioBancario) {
        this.totalServicioBancario = totalServicioBancario;
    }

    public double getTotalPagoImpuestos() {
        return totalPagoImpuestos;
    }

    public void setTotalPagoImpuestos(double totalPagoImpuestos) {
        this.totalPagoImpuestos = totalPagoImpuestos;
    }

    public double getTotalTrasladoCarga() {
        return totalTrasladoCarga;
    }

    public void setTotalTrasladoCarga(double totalTrasladoCarga) {
        this.totalTrasladoCarga = totalTrasladoCarga;
    }
}
