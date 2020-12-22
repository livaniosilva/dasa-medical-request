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
    @Autowired
    Medico medicoNovo;
    @Autowired
    Paciente pacienteNovo;

    @Override
    public Pedido save(Pedido pedidoEntrada) {

        if (pedidoEntrada.getPaciente() != null && pedidoEntrada.getExame() != null && pedidoEntrada.getMedico() != null) {
            try {

                Medico medicoEntrada = pedidoEntrada.getMedico();
                Optional<Medico> medicoDuplicado = Optional.ofNullable(medicoRepository.findByConselho(medicoEntrada.getConselho()));
                Paciente pacienteEntrada = pedidoEntrada.getPaciente();
                Optional<Paciente> pacientDuplicado = Optional.ofNullable(pacienteRepository.findByDocumento(pacienteEntrada.getDocumento()));

                if (!medicoDuplicado.isPresent()) {
                    medicoNovo = medicoRepository.save(medicoEntrada);
                    pedidoEntrada.setMedico(medicoNovo);
                } else {
                    pedidoEntrada.setMedico(medicoDuplicado.orElse(null));
                }
                if (!pacientDuplicado.isPresent()) {
                    pacienteNovo = pacienteRepository.save(pacienteEntrada);
                    pedidoEntrada.setPaciente(pacienteNovo);
                } else {
                    pedidoEntrada.setPaciente(pacientDuplicado.orElse(null));
                }
                return pedidoRepository.save(pedidoEntrada);

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
            listaPedido.addAll(pedidoRepository.findAll());
            return listaPedido;

        } catch (Exception ex) {
            Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaPedido;
    }

    @Override
    public Pedido findById(Long id) {
        Pedido pedido = new Pedido();
        try {
            pedido = pedidoRepository.findById(id).orElse(null);
            return pedido;
        } catch (Exception ex) {
            Logger.getLogger(PedidoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pedido;
    }

    @Override
    public boolean update(Long id, Pedido pedido) {
        try {
            Pedido pedidoToUpdate = this.findById(id);
            if (pedidoToUpdate != null) {
                pedidoToUpdate.setPaciente(pedido.getPaciente());
                pedidoToUpdate.setMedico(pedido.getMedico());
                pedidoToUpdate.setExame(pedido.getExame());
                pedidoToUpdate.setDataValidade(pedido.getDataValidade());

                pedidoRepository.save(pedidoToUpdate);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Pedido pedidoToDelete = this.findById(id);
            pedidoRepository.delete(pedidoToDelete);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}