package br.org.livanio.service;

import br.org.livanio.entity.Medico;
import br.org.livanio.entity.Paciente;

import java.util.List;

public interface PacienteService {
    public Paciente save(Paciente paciente);
    public List<Paciente> findAll();
    public Paciente findById(Long id);
    public boolean update(Long id, Paciente paciente);
    public boolean delete(Long id);
}
