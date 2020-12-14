package br.org.livanio.repository;

import br.org.livanio.entity.Medico;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MedicoRepository  extends JpaRepository<Medico, Long> {
    public Medico findByConselho(Long crm);
}
