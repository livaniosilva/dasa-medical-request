package br.org.livanio.service;

import br.org.livanio.entity.Medico;

import java.util.List;
import java.util.Optional;

public interface MedicoService {
    public Medico save(Medico medico);
    public List<Medico> findAll();
    public Medico findById(Long id);
    public boolean update(Long id, Medico medico);
    public boolean delete(Long id);
    public Optional<Medico> findByConselho(Long conselho);
}
