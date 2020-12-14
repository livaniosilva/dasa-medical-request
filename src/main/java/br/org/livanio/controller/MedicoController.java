package br.org.livanio.controller;

import br.org.livanio.entity.Medico;
import br.org.livanio.parameter.MedicoParameter;
import br.org.livanio.service.MedicoService;
import io.swagger.annotations.ApiOperation;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@AllArgsConstructor
@RestController
@RequestMapping("dasa")
public class MedicoController {
    @Autowired
    MedicoService medicoService;

    @ApiOperation(value = "Cadastra médicos", tags = "Médico")
    @ResponseBody
    @PostMapping("/medico")
    public ResponseEntity<Medico> save(@Valid @RequestBody MedicoParameter parameter) {
        Medico medico = medicoService.save(parameter.toModel());
        if (medico != null) {
            return new ResponseEntity<>(medico, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Lista todos os médicos que existem na base", tags = "Médico")
    @ResponseBody
    @GetMapping("/medicos")
    public ResponseEntity<List<Medico>> findAll() {
        List<Medico> medicoList = medicoService.findAll();
        if (!medicoList.isEmpty()) {
            return new ResponseEntity<>(medicoList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Aatualiza dados do médico", tags = "Médico")
    @PutMapping("/medico/{id}")
    public ResponseEntity update(@PathVariable(value = "id") Long id, @Valid @RequestBody MedicoParameter parameter) {
        boolean medicoToUpdate = medicoService.update(id, parameter.toModel());
        if (medicoToUpdate) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Exclui médico", tags = "Médico")
    @DeleteMapping("/medico/{id}")
    public ResponseEntity delete(@PathVariable Long id) {
        boolean medicoToDelete = medicoService.delete(id);
        if (medicoToDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Recupera Médico por id", tags = "Médico")
    @ResponseBody
    @GetMapping("/medico/{id}")
    public ResponseEntity<Medico> getById(@PathVariable(value = "id") Long id) throws Exception {
        Medico medico = medicoService.findById(id);
        if (medico != null) {
            return new ResponseEntity<>(medico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @ApiOperation(value = "Recupera Médico por CRM", tags = "Médico")
    @ResponseBody
    @GetMapping("/medico/{crm}")
    public ResponseEntity<Medico> getByCRM(@PathVariable(value = "crm") Long crm) throws Exception {
        Medico medico = medicoService.findByConselho(crm).orElse(null);
        if (medico != null) {
            return new ResponseEntity<>(medico, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}