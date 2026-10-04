package com.importacion.backend.config;

import com.importacion.backend.models.entities.*;
import com.importacion.backend.repositories.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConditionalOnProperty(name = "app.data.initialize-demo", havingValue = "true", matchIfMissing = true)
public class DataInitializer {

    @Bean
    CommandLineRunner loadData(
            AereoRepository aereoRepository,
            MaritimoRepository maritimoRepository,
            BodegaExtranjeroRepository bodegaExtranjeroRepository,
            GastosPuertoRepository gastosPuertoRepository,
            ImpuestosAduanaRepository impuestosAduanaRepository,
            ServicioBancarioRepository servicioBancarioRepository,
            PagoImpuestosRepository pagoImpuestosRepository,
            TrasladoCargaRepository trasladoCargaRepository,
            OrganismoControlRepository organismoControlRepository,
            TipoEmbarqueRepository tipoEmbarqueRepository) {
        return args -> {
            if (aereoRepository.count() == 0) {
                aereo first = new aereo();
                first.setPeso(1200);
                first.setPrecio(3500);

                aereo second = new aereo();
                second.setPeso(2450);
                second.setPrecio(5200);

                aereoRepository.save(first);
                aereoRepository.save(second);
            }

            if (maritimoRepository.count() == 0) {
                maritimo m = new maritimo();
                m.setCubicaje(500);
                m.setPrecio(6800);
                maritimoRepository.save(m);
            }

            if (bodegaExtranjeroRepository.count() == 0) {
                bodega_extranjero bodega = new bodega_extranjero();
                bodega.setPais("China");
                bodega.setNombre("Bodega Centro");
                bodega.setEntrada(150f);
                bodega.setEtiqueta(90f);
                bodega.setManejo(120f);
                bodega.setSalida(180f);
                bodegaExtranjeroRepository.save(bodega);
            }

            if (gastosPuertoRepository.count() == 0) {
                gastos_puerto puerto = new gastos_puerto();
                puerto.setAlmacenaje(500);
                puerto.setAgente_aduana(1200);
                puerto.setRegimen_especial_agente(400);
                puerto.setCandado_satelital(850);
                gastosPuertoRepository.save(puerto);
            }

            if (impuestosAduanaRepository.count() == 0) {
                inpuestos_aduana imp = new inpuestos_aduana();
                imp.setIva(900);
                imp.setFodinfa(350);
                imp.setAdvaloren(240);
                imp.setIce(110);
                impuestosAduanaRepository.save(imp);
            }

            if (servicioBancarioRepository.count() == 0) {
                servicio_bancario servicio = new servicio_bancario();
                servicio.setISD(300);
                servicio.setComision_banco(450);
                servicioBancarioRepository.save(servicio);
            }

            if (pagoImpuestosRepository.count() == 0) {
                pago_impuestos pago = new pago_impuestos();
                pago.setEfectivo(1800);
                pagoImpuestosRepository.save(pago);
            }

            if (trasladoCargaRepository.count() == 0) {
                traslado_carga traslado = new traslado_carga();
                traslado.setFlete_camion(900);
                traslado.setGuardia_armada(220);
                traslado.setEstibadores(350);
                trasladoCargaRepository.save(traslado);
            }

            if (organismoControlRepository.count() == 0) {
                organismo_control org = new organismo_control();
                org.setTipo_organismo("Sanitaria");
                organismoControlRepository.save(org);
            }

            if (tipoEmbarqueRepository.count() == 0) {
                tipo_embarque tipo = new tipo_embarque();
                tipoEmbarqueRepository.save(tipo);
            }
        };
    }
}
