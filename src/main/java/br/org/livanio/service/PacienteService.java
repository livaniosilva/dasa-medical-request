package br.org.livanio.service;

import br.org.livanio.entity.Paciente;

import java.util.List;
import java.util.Optional;

public interface PacienteService {
    public Paciente save(Paciente paciente);
    public List<Paciente> findAll();
    public Optional<Paciente> findById(Long id);
    public boolean update(Long id, Paciente paciente);
    public boolean delete(Long id);
}
