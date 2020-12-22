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
import java.util.Objects;
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
    @Autowired
    Paciente paciente;

    @Override
    public Paciente save(Paciente paciente) {
        Paciente pacienteNovo = new Paciente();
        try {
            if (paciente != null) {
                Optional<Optional<Paciente>> pacienteDuplicado = Optional.ofNullable(this.findById(paciente.getId()));
                if (pacienteDuplicado.isPresent()) {
                    throw new NotFoundException("paciente existe na base" + (paciente.getId()));
                } else {
                    pacienteNovo =  pacienteRepository.save(paciente);
                    return pacienteNovo;
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return pacienteNovo;
    }

    @Override
    public List<Paciente> findAll() {
        List<Paciente> listapaciente = new ArrayList<>();
        try {
            listapaciente.addAll(pacienteRepository.findAll());
            return listapaciente;
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listapaciente;
    }

    @Override
    public Optional<Paciente> findById(Long id) {
        try {
            paciente =  pacienteRepository.findById(id).orElse(null);
            return Optional.of(Objects.requireNonNull(paciente));
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return Optional.empty();
    }

    @Override
    public boolean update(Long id, Paciente paciente) {
        try {
            Optional<Paciente> pacienteOptional = this.findById(id);
            if (pacienteOptional.isPresent()) {
                Paciente pacienteToUpdate = pacienteOptional.get();
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
            Optional<Paciente> pacienteOptional = this.findById(id);
            if (pacienteOptional.isPresent()){
                Paciente pacienteToDelete = pacienteOptional.get();
                pacienteRepository.delete(pacienteToDelete);
            }
            return true;
        } catch (Exception ex) {
            Logger.getLogger(PacienteServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }
}