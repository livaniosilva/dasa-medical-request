package br.org.livanio.repository;

import br.org.livanio.entity.Documento;
import br.org.livanio.entity.Medico;
import br.org.livanio.entity.Paciente;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PacienteRepository extends JpaRepository<Paciente, Long> {
    public Paciente findByDocumento(Documento doc);
}
