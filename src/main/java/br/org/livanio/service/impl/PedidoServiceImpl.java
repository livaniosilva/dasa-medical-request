package br.org.livanio.service.impl;

import br.org.livanio.entity.Medico;
import br.org.livanio.entity.Paciente;
import br.org.livanio.entity.Pedido;
import br.org.livanio.repository.MedicoRepository;
import br.org.livanio.repository.PacienteRepository;
import br.org.livanio.repository.PedidoRepository;
import br.org.livanio.service.PedidoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Service
public class PedidoServiceImpl implements PedidoService {
    @Autowired
    PedidoRepository pedidoRepository;
    @Autowired
    MedicoRepository medicoRepository;
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Pedido save(Pedido pedidoEntrada) {
        Medico medicoNovo = null;
        Paciente pacienteNovo = null;

        if (pedidoEntrada.getPaciente() != null && pedidoEntrada.getExame() != null && pedidoEntrada.getMedico() != null) {

            Medico medicoEntrada = pedidoEntrada.getMedico();
            Optional<Medico> medicoDuplicado = Optional.ofNullable(medicoRepository.findByConselho(medicoEntrada.getConselho()));
            Paciente pacienteEntrada = pedidoEntrada.getPaciente();
            Optional<Paciente> pacientDuplicado = Optional.ofNullable(pacienteRepository.findByDocumento(pacienteEntrada.getDocumento()));

            try {
                if (medicoDuplicado.isPresent()) {
                    Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, "This CRM already  exist", medicoEntrada.getConselho());
                } else {
                    medicoNovo = medicoRepository.save(medicoEntrada);
                }
                if (pacientDuplicado.isPresent()) {
                    Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, "This id already  exist", pedidoEntrada.getId());
                } else {
                    pacienteNovo = pacienteRepository.save(pacienteEntrada);
                }
            } catch (Exception ex) {
                Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
            try {
                if (medicoNovo != null) {
                    pedidoEntrada.setMedico(medicoNovo);
                } else {
                    pedidoEntrada.setMedico(medicoDuplicado.orElse(null));
                }
                if (pacienteNovo != null) {
                    pedidoEntrada.setPaciente(pacienteNovo);
                } else {
                    pedidoEntrada.setPaciente(pacientDuplicado.orElse(null));
                }
                Pedido pedido = pedidoRepository.save(pedidoEntrada);
                return pedido;


            } catch (Exception ex) {
                Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return null;
    }

    @Override
    public List<Pedido> findAll() {
        List<Pedido> listaPedido = new ArrayList<>();
        try {
            pedidoRepository.findAll().forEach(listaPedido::add);
            return listaPedido;

        } catch (Exception ex) {
            Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Pedido findById(Long id) {
        try {
            return pedidoRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}