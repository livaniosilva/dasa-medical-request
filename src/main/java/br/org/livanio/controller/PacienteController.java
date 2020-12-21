package br.org.livanio.controller;

import br.org.livanio.entity.Paciente;
import br.org.livanio.parameter.PacienteParameter;
import br.org.livanio.service.PacienteService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("dasa")
public class PacienteController {
    @Autowired
    PacienteService pacienteService;


    @ApiOperation(value = "Cadastra paciente", tags = "Paciente")
    @ResponseBody
    @PostMapping("/paciente")
    public ResponseEntity<Paciente> save(@Valid @RequestBody PacienteParameter parameter) {
        Paciente paciente = pacienteService.save(parameter.toModel());
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Lista todos os pacientes que existem na base", tags = "Paciente")
    @ResponseBody
    @GetMapping("/pacientes")
    public ResponseEntity<List<Paciente>> findAll() {
        List<Paciente> pacienteList = pacienteService.findAll();
        if (!pacienteList.isEmpty()) {
            return new ResponseEntity<>(pacienteList, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);
        }
    }

    @ApiOperation(value = "Aatualiza dados do paciente", tags = "Paciente")
    @PutMapping("/paciente/{id}")
    public ResponseEntity<Paciente> update(@PathVariable(value = "id") Long id, @Valid @RequestBody PacienteParameter parameter) {
        boolean pacienteToUpdate = pacienteService.update(id, parameter.toModel());
        if (pacienteToUpdate) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }

    @ApiOperation(value = "Exclui paciente", tags = "Paciente")
    @ResponseBody
    @DeleteMapping("/paciente/{id}")
    public ResponseEntity<Paciente> delete(@PathVariable(value = "id") Long id) {
        boolean pacienteToDelete = pacienteService.delete(id);
        if (pacienteToDelete) {
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.UNPROCESSABLE_ENTITY);
        }
    }
    @ApiOperation(value="Recupera Paciente por id", tags = "Paciente")
    @ResponseBody
    @GetMapping("/paciente/{id}")
    public ResponseEntity<Paciente> getById(@PathVariable(value = "id") Long id) {
        Paciente paciente = pacienteService.findById(id);
        if (paciente != null) {
            return new ResponseEntity<>(paciente, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
}