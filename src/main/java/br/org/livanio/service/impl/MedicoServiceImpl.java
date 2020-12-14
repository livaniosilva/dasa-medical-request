package br.org.livanio.service.impl;

import br.org.livanio.entity.Medico;
import br.org.livanio.repository.MedicoRepository;
import br.org.livanio.service.MedicoService;
import javassist.NotFoundException;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.logging.Level;
import java.util.logging.Logger;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Service
public class MedicoServiceImpl implements MedicoService {
    @Autowired
    private MedicoRepository medicoRepository;

    @Override
    public Medico save(Medico medico) {
        try {
            if (medico != null) {
                Optional<Medico> medicoDuplicado = Optional.ofNullable(this.findById(medico.getId()));
                if (medicoDuplicado.isPresent()) {
                    throw new NotFoundException("Medico existe na base" + (medico.getId()));
                } else {
                    return medicoRepository.save(medico);
                }
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public List<Medico> findAll() {
        List<Medico> listaMedico = new ArrayList<>();
        try {
            medicoRepository.findAll().forEach(e -> listaMedico.add(e));
            return listaMedico;
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public Medico findById(Long id) {
        try {
            return medicoRepository.findById(id).orElse(null);
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }

    @Override
    public boolean update(Long id, Medico medico) {
        try {
            Medico medicoToUpdate = this.findById(id);
            if (medicoToUpdate != null) {
                Medico medicoUpdated = new Medico();
                medicoUpdated.setUfConselho(medicoToUpdate.getUfConselho());
                medicoUpdated.setTipoConselho(medicoToUpdate.getTipoConselho());
                medicoUpdated.setConselho(medicoToUpdate.getConselho());
                medicoUpdated.setNome(medicoToUpdate.getNome());

                medicoRepository.save(medico);
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
            Medico medicoToDelete = this.findById(id);
            medicoRepository.delete(medicoToDelete);
            return true;
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return false;
    }

    @Override
    public Optional<Medico> findByConselho(Long conselho) {
        try {
            Optional<Medico> medico = Optional.ofNullable(medicoRepository.findByConselho(conselho));
            if (medico.isPresent()){
                return medico;
            }else {
                throw new NotFoundException("Medico não encontrado na base com este conselho: "+ conselho);
            }
        } catch (Exception ex) {
            Logger.getLogger(MedicoServiceImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        return null;
    }
}