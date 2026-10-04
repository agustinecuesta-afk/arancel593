package com.importacion.backend.services;

import com.importacion.backend.dto.CostoResumenResponse;
import com.importacion.backend.models.entities.*;
import com.importacion.backend.repositories.*;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class CostosImportacionService {

    private final AereoRepository aereoRepository;
    private final MaritimoRepository maritimoRepository;
    private final BodegaExtranjeroRepository bodegaExtranjeroRepository;
    private final GastosPuertoRepository gastosPuertoRepository;
    private final ImpuestosAduanaRepository impuestosAduanaRepository;
    private final ServicioBancarioRepository servicioBancarioRepository;
    private final PagoImpuestosRepository pagoImpuestosRepository;
    private final TrasladoCargaRepository trasladoCargaRepository;

    public CostosImportacionService(
            AereoRepository aereoRepository,
            MaritimoRepository maritimoRepository,
            BodegaExtranjeroRepository bodegaExtranjeroRepository,
            GastosPuertoRepository gastosPuertoRepository,
            ImpuestosAduanaRepository impuestosAduanaRepository,
            ServicioBancarioRepository servicioBancarioRepository,
            PagoImpuestosRepository pagoImpuestosRepository,
            TrasladoCargaRepository trasladoCargaRepository) {
        this.aereoRepository = aereoRepository;
        this.maritimoRepository = maritimoRepository;
        this.bodegaExtranjeroRepository = bodegaExtranjeroRepository;
        this.gastosPuertoRepository = gastosPuertoRepository;
        this.impuestosAduanaRepository = impuestosAduanaRepository;
        this.servicioBancarioRepository = servicioBancarioRepository;
        this.pagoImpuestosRepository = pagoImpuestosRepository;
        this.trasladoCargaRepository = trasladoCargaRepository;
    }

    @Transactional(readOnly = true)
    public CostoResumenResponse calcularResumen() {
        CostoResumenResponse response = new CostoResumenResponse();

        double totalAereo = sumAereo();
        double totalMaritimo = sumMaritimo();
        double totalBodegaExtranjero = sumBodegaExtranjero();
        double totalGastosPuerto = sumGastosPuerto();
        double totalImpuestosAduana = sumImpuestosAduana();
        double totalServicioBancario = sumServicioBancario();
        double totalPagoImpuestos = sumPagoImpuestos();
        double totalTrasladoCarga = sumTrasladoCarga();

        double total = totalAereo + totalMaritimo + totalBodegaExtranjero + totalGastosPuerto
                + totalImpuestosAduana + totalServicioBancario + totalPagoImpuestos + totalTrasladoCarga;

        response.setTotalAereo(totalAereo);
        response.setTotalMaritimo(totalMaritimo);
        response.setTotalBodegaExtranjero(totalBodegaExtranjero);
        response.setTotalGastosPuerto(totalGastosPuerto);
        response.setTotalImpuestosAduana(totalImpuestosAduana);
        response.setTotalServicioBancario(totalServicioBancario);
        response.setTotalPagoImpuestos(totalPagoImpuestos);
        response.setTotalTrasladoCarga(totalTrasladoCarga);
        response.setTotal(total);

        return response;
    }

    private double sumAereo() {
        List<aereo> records = aereoRepository.findAll();
        return records.stream().mapToDouble(r -> toDouble(r.getPrecio())).sum();
    }

    private double sumMaritimo() {
        List<maritimo> records = maritimoRepository.findAll();
        return records.stream().mapToDouble(r -> toDouble(r.getPrecio())).sum();
    }

    private double sumBodegaExtranjero() {
        List<bodega_extranjero> records = bodegaExtranjeroRepository.findAll();
        return records.stream().mapToDouble(r ->
                toDouble(r.getEntrada())
                        + toDouble(r.getEtiqueta())
                        + toDouble(r.getManejo())
                        + toDouble(r.getSalida())).sum();
    }

    private double sumGastosPuerto() {
        List<gastos_puerto> records = gastosPuertoRepository.findAll();
        return records.stream().mapToDouble(r ->
                toDouble(r.getAlmacenaje())
                        + toDouble(r.getAgente_aduana())
                        + toDouble(r.getRegimen_especial_agente())
                        + toDouble(r.getCandado_satelital())).sum();
    }

    private double sumImpuestosAduana() {
        List<inpuestos_aduana> records = impuestosAduanaRepository.findAll();
        return records.stream().mapToDouble(r ->
                toDouble(r.getIva())
                        + toDouble(r.getFodinfa())
                        + toDouble(r.getAdvaloren())
                        + toDouble(r.getIce())).sum();
    }

    private double sumServicioBancario() {
        List<servicio_bancario> records = servicioBancarioRepository.findAll();
        return records.stream().mapToDouble(r ->
                toDouble(r.getISD())
                        + toDouble(r.getComision_banco())).sum();
    }

    private double sumPagoImpuestos() {
        List<pago_impuestos> records = pagoImpuestosRepository.findAll();
        return records.stream().mapToDouble(r -> toDouble(r.getEfectivo())).sum();
    }

    private double sumTrasladoCarga() {
        List<traslado_carga> records = trasladoCargaRepository.findAll();
        return records.stream().mapToDouble(r ->
                toDouble(r.getFlete_camion())
                        + toDouble(r.getGuardia_armada())
                        + toDouble(r.getEstibadores())).sum();
    }

    private double toDouble(Number value) {
        return value == null ? 0d : value.doubleValue();
    }
}
