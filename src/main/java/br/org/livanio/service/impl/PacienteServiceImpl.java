package br.org.livanio.service.impl;

import br.org.livanio.entity.Paciente;
import br.org.livanio.repository.PacienteRepository;
import br.org.livanio.service.PacienteService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@AllArgsConstructor
@Getter
@Setter
@Service
public class PacienteServiceImpl implements PacienteService {
    @Autowired
    PacienteRepository pacienteRepository;

    @Override
    public Paciente save(Paciente paciente) {
        try {
            if (paciente != null) {
                Optional<Paciente> pacienteDuplicado = Optional.ofNullable(this.findById(paciente.getId()));
                if (pacienteDuplicado.isPresent()) {
                    throw new NotFoundException("paciente existe na base" + (paciente.getId()));
                } else {
                    return pacienteRepository.save(paciente);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> listapaciente = new ArrayList<>();
        try {
            pacienteRepository.findAll().forEach(e -> listapaciente.add(e));
            return listapaciente;
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Paciente findById(Long id) {
        try {
            return pacienteRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Long id, Paciente paciente) {
        try {
            Paciente pacienteToUpdate = this.findById(id);
            if (pacienteToUpdate != null) {
                pacienteToUpdate.setNome(paciente.getNome());
                pacienteToUpdate.setNomeMae(paciente.getNomeMae());
                pacienteToUpdate.setSexo(paciente.getSexo());
                pacienteToUpdate.setDataNascimento(paciente.getDataNascimento());
                pacienteToUpdate.setEndereco(paciente.getEndereco());
                pacienteToUpdate.setContato(paciente.getContato());

                pacienteRepository.save(pacienteToUpdate);
                return true;
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public boolean delete(Long id) {
        try {
            Paciente pacienteToDelete = this.findById(id);
            pacienteRepository.delete(pacienteToDelete);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}